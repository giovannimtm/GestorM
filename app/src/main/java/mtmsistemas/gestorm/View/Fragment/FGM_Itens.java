package mtmsistemas.gestorm.View.Fragment;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Controller.ClsCustomListAdapter;
import mtmsistemas.gestorm.Controller.ClsUtil;
import mtmsistemas.gestorm.Model.CHECKLISTITEM;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Activity.ACT_CheckList;

import static android.app.Activity.RESULT_OK;
import static mtmsistemas.gestorm.View.Activity.ACT_CheckList.escondeViews;

public class FGM_Itens extends Fragment {

    private static final int CAMERA_PIC_REQUEST = 1337;
    static ImageButton botaoClicado;
    static ClsUtil clsUtil = new ClsUtil();
    static int posicaoAlterada;
    static String caminhoFoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fgm_itens, container, false);

        ListView listView = (ListView) v.findViewById(R.id.LST_Itens);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        return v;
    }

    class CustomAdapter extends BaseAdapter {

        List<View> viewsExpansiveis = new ArrayList<View>();

        @Override
        public int getCount() {
            return ((ACT_CheckList) getActivity()).checkListItems.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(null).inflate(R.layout.itens_checkllist_custom_layout, null);

            final CHECKLISTITEM itemSelecionado = ((ACT_CheckList)getActivity()).checkListItems.get(position);

            final ImageButton imageButton =(ImageButton)convertView.findViewById(R.id.IMGBTN_ItemFoto);

            final TextView labelObservacoesItem = (TextView)convertView.findViewById(R.id.TV_ObservacaoItem);
            final EditText observacoesItem = (EditText)convertView.findViewById(R.id.ET_ObservacaoItem);
            final TextView labelAvaliacaoItem = (TextView)convertView.findViewById(R.id.TV_AvaliacaoItem);
            final TextView percentualAvaliacao = (TextView)convertView.findViewById(R.id.TV_PercentualAvaliacaoItem);
            final SeekBar avaliacaoItem = (SeekBar)convertView.findViewById(R.id.SB_AvaliacaoItem);

            TextView tv_nomeItem = (TextView)convertView.findViewById(R.id.TV_Item);
            Spinner sp_SituacaoItem;

            tv_nomeItem.setText(itemSelecionado.getDESCRICAOCOMPONENTE());
            tv_nomeItem.setTag(position);
            tv_nomeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (labelObservacoesItem.isShown())
                        escondeViews[position] = true;
                    else
                        escondeViews[position] = false;
                    viewsExpansiveis.add(labelObservacoesItem);
                    viewsExpansiveis.add(observacoesItem);
                    viewsExpansiveis.add(labelAvaliacaoItem);
                    viewsExpansiveis.add(percentualAvaliacao);
                    viewsExpansiveis.add(avaliacaoItem);
                    clsUtil.SU_ExpandeOuRetraiViews(viewsExpansiveis);
                    viewsExpansiveis.clear();
                }
            });

            File pastaItem = new File(
                    Environment.getExternalStorageDirectory() + "/DCIM/Gestor/CheckList/" + tv_nomeItem.getText().toString()
            );
            if (pastaItem.exists() && pastaItem.listFiles().length > 0) {
                if (itemSelecionado.getIMAGEMCOMPONENTE() != null && new File(itemSelecionado.getIMAGEMCOMPONENTE()).exists()) {
                    imageButton.setTag("Foto");
                    try {
                        clsUtil.FU_redimensionaImagemEColocaNaView(
                                itemSelecionado.getIMAGEMCOMPONENTE(),
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
                    itemSelecionado.setIMAGEMCOMPONENTE(fotos[0].getAbsolutePath());
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
            String[] arraySituacoesBem = {"Status Item", "Otimo","Bom", "Normal", "Regular", "Pessimo"};
            ClsCustomListAdapter adapterSituacaoItem = new ClsCustomListAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item, arraySituacoesBem, 0);

            sp_SituacaoItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    posicaoAlterada = FU_retornaPosicao(finalConvertView);
                    itemSelecionado.setFGSITUACAO(parent.getSelectedItem().toString().substring(0,1));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            sp_SituacaoItem.setAdapter(adapterSituacaoItem);

            if(itemSelecionado.getFGSITUACAO() != null) {
                sp_SituacaoItem.setSelection(Arrays.asList(arraySituacoesBem).indexOf(
                        clsUtil.FU_RetornaStringArrayComencandoCom(itemSelecionado.getFGSITUACAO().toString(),arraySituacoesBem)
                ));
            }

            observacoesItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        itemSelecionado.setOBSERVACAO(observacoesItem.getText());
                    }
                }
            });

            if(itemSelecionado.getOBSERVACAO() != null)
                observacoesItem.setText(itemSelecionado.getOBSERVACAO().toString());

            avaliacaoItem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    percentualAvaliacao.setText(progress + "%");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    percentualAvaliacao.setText(seekBar.getProgress() + "%");
                    itemSelecionado.setPCTAVALIACAO(seekBar.getProgress());
                }
            });

            try{
                Double d = (Double)itemSelecionado.getPCTAVALIACAO();
                avaliacaoItem.setProgress(Integer.valueOf(d.intValue()));
                percentualAvaliacao.setText(avaliacaoItem.getProgress() + "%");
            }catch (Exception e){
                e.printStackTrace();
            }

            if(escondeViews[position]){
                labelObservacoesItem.setVisibility(View.GONE);
                observacoesItem.setVisibility(View.GONE);
                labelAvaliacaoItem.setVisibility(View.GONE);
                percentualAvaliacao.setVisibility(View.GONE);
                avaliacaoItem.setVisibility(View.GONE);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (labelObservacoesItem.isShown())
                        escondeViews[position] = true;
                    else
                        escondeViews[position] = false;
                    viewsExpansiveis.add(labelObservacoesItem);
                    viewsExpansiveis.add(observacoesItem);
                    viewsExpansiveis.add(labelAvaliacaoItem);
                    viewsExpansiveis.add(percentualAvaliacao);
                    viewsExpansiveis.add(avaliacaoItem);
                    clsUtil.SU_ExpandeOuRetraiViews(viewsExpansiveis);
                    viewsExpansiveis.clear();
                }
            });
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
                        ((ACT_CheckList)getActivity()).checkListItems.get(posicaoAlterada).setIMAGEMCOMPONENTE(file_path);

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
        ListView listView = (ListView) view.findViewById(R.id.LST_Itens);

//        Arrays.fill(escondeViews, true);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);
    }
}