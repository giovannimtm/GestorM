package mtmsistemas.gestorm.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import mtmsistemas.gestorm.Activity.ACT_CheckList;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.Util.ClsCustomListAdapter;
import mtmsistemas.gestorm.Util.ClsUtil;

import static android.app.Activity.RESULT_OK;

public class FGM_ItensEntrada extends Fragment {

    private static final int CAMERA_PIC_REQUEST = 1337;
    static ImageButton botaoClicado;
    static ClsUtil clsUtil = new ClsUtil();
    static int posicaoAlterada;
    static String caminhoFoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fgm_itens_entrada, container, false);

        ListView listView = (ListView) v.findViewById(R.id.LST_ItensEntrada);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        return v;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ((ACT_CheckList)getActivity()).getClsItensEntrada().getItens().length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("RestrictedApi")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(null).inflate(R.layout.itens_checkllist_custom_layout, null);

            final ImageButton imageButton =(ImageButton)convertView.findViewById(R.id.IMGBTN_ItemFoto);

            TextView tv_nomeItem = (TextView)convertView.findViewById(R.id.TV_Item);
            Spinner sp_SituacaoItem;

            tv_nomeItem.setText(((ACT_CheckList)getActivity()).getClsItensEntrada().getItem(position));
            tv_nomeItem.setTag(position);

            File pastaItem = new File(
                    Environment.getExternalStorageDirectory() + "/DCIM/Gestor/CheckList/" + tv_nomeItem.getText().toString()
            );
            if (pastaItem.exists() && pastaItem.listFiles().length > 0) {
                if (((ACT_CheckList) getActivity()).getClsItensEntrada().getCaminhoFotoItem(position) != null && new File(((ACT_CheckList) getActivity()).getClsItensEntrada().getCaminhoFotoItem(position)).exists()) {
                    imageButton.setTag("Foto");
                    try {
                        clsUtil.FU_redimensionaImagemEColocaNaView(
                                ((ACT_CheckList) getActivity()).getClsItensEntrada().getCaminhoFotoItem(position),
                                imageButton,
                                imageButton.getLayoutParams().height,
                                imageButton.getLayoutParams().width);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    File[] fotos = pastaItem.listFiles();
                    ((ACT_CheckList) getActivity()).getClsItensEntrada().setCaminhoFotoItem(fotos[0].getAbsolutePath(),position);
                    imageButton.setTag("Foto");
                    try {
                        clsUtil.FU_redimensionaImagemEColocaNaView(
                                fotos[0].getAbsolutePath(),
                                imageButton,
                                imageButton.getLayoutParams().height,
                                imageButton.getLayoutParams().width);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else
                imageButton.setTag(null);

            final View finalConvertView = convertView;
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    botaoClicado = (ImageButton) v.findViewById(R.id.IMGBTN_ItemFoto);
                    posicaoAlterada = FU_retornaPosicao(finalConvertView);
                    TextView textView = (TextView)finalConvertView.findViewById(R.id.TV_Item);

                    if(botaoClicado.getTag()== null) {
                        File foto = clsUtil.FU_criaArquivoImagemNaPAsta(textView.getText().toString());
                        caminhoFoto = foto.getAbsolutePath();

                        // save image here
                        Intent intent = clsUtil.FU_salvaFotoPastaEspecifica(foto);
                        startActivityForResult(intent, CAMERA_PIC_REQUEST);
                    }
                    else{
//                        Dialog nagDialog = clsUtil.FU_imageDialog(getContext(), ((ACT_CheckList)getActivity()).getClsItensEntrada().getCaminhoFotoItem(posicaoAlterada));
//                        nagDialog.show();

                        Intent intent = clsUtil.FU_visualizarFotosDaPasta(getContext(), textView.getText().toString());
                        startActivity(intent);
                    }
                }
            });

            ImageButton adicionarFoto = (ImageButton) convertView.findViewById(R.id.IMGBTN_ItemAdicionarFoto);
            adicionarFoto.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    posicaoAlterada = FU_retornaPosicao(finalConvertView);
                    botaoClicado = (ImageButton) finalConvertView.findViewById(R.id.IMGBTN_ItemFoto);
                    TextView textView = (TextView)finalConvertView.findViewById(R.id.TV_Item);

                    File foto = clsUtil.FU_criaArquivoImagemNaPAsta(textView.getText().toString());
                    caminhoFoto = foto.getAbsolutePath();

                    Intent intent = clsUtil.FU_salvaFotoPastaEspecifica(foto);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);
                }
            });

            sp_SituacaoItem = (Spinner)convertView.findViewById(R.id.Sp_SituacaoItem);
            String[] arraySituacoesBem = {"Status Item", "Ok","Avariado", "Faltando"};
            ClsCustomListAdapter adapterSituacaoItem = new ClsCustomListAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item, arraySituacoesBem, 0);

            sp_SituacaoItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    posicaoAlterada = FU_retornaPosicao(finalConvertView);
                    ((ACT_CheckList)getActivity()).getClsItensEntrada().setSatatusEquipItem(parent.getSelectedItem().toString(), posicaoAlterada);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            sp_SituacaoItem.setAdapter(adapterSituacaoItem);

            if(((ACT_CheckList)getActivity()).getClsItensEntrada().getStatusEquipItem(position) != null) {
                sp_SituacaoItem.setSelection(Arrays.asList(arraySituacoesBem).indexOf(((ACT_CheckList)getActivity()).getClsItensEntrada().getStatusEquipItem(position)));
            }

            return convertView;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int tries;
        boolean loop;
        String file_path = "";
        try {
            loop = true; tries = 1;
            while (loop == true) {
                Thread.sleep(500);
                if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {

//                    Uri selectedImageUri = data.getData();
//                    String[] proj = {MediaStore.Images.Media.DATA};
//                    Cursor cursor = getContext().getContentResolver().query(selectedImageUri, proj, null, null, null);
//                    if (cursor.moveToFirst()) {
//                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                        file_path = cursor.getString(column_index);
//                    }
//                    cursor.close();

                    file_path = caminhoFoto;

                    Intent mediaScannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File f = new File(file_path);
                    Uri fileContentUri = Uri.fromFile(f);
                    mediaScannerIntent.setData(fileContentUri);
                    getActivity().sendBroadcast(mediaScannerIntent);

                    if(botaoClicado != null) {
                        ((ACT_CheckList)getActivity()).getClsItensEntrada().setCaminhoFotoItem(file_path, posicaoAlterada);

                        botaoClicado.setTag("Foto");
                        try {
                            clsUtil.FU_redimensionaImagemEColocaNaView(
                                    file_path,
                                    botaoClicado,
                                    botaoClicado.getHeight(),
                                    botaoClicado.getWidth() );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    loop = false;
                }
                tries++;
                if(tries == 21 || resultCode != RESULT_OK)
                    loop = false;
            }
            botaoClicado = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int FU_retornaPosicao(View v){
        int posicao;
        TextView tv_nomeItem = (TextView)v.findViewById(R.id.TV_Item);
        posicao = (int)tv_nomeItem.getTag();
        return posicao;
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        ListView listView = (ListView) view.findViewById(R.id.LST_ItensEntrada);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);
    }

}
