package mtmsistemas.gestorm.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import java.util.Arrays;

import mtmsistemas.gestorm.Activity.ACT_CheckList;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.Util.ClsCustomListAdapter;
import mtmsistemas.gestorm.Util.ClsUtil;

import static android.app.Activity.RESULT_OK;

public class FGM_ItensEntrada extends Fragment {

    private static final int CAMERA_PIC_REQUEST = 1337;
    static ImageButton botaoClicado;
    static Uri foto;
    static ClsUtil clsUtil = new ClsUtil();
    static int posicaoAlterada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }

                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
        }

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(null).inflate(R.layout.itens_checkllist_custom_layout, null);

            ImageButton imageButton =(ImageButton)convertView.findViewById(R.id.IMGBTN_ItemFoto);

            TextView tv_nomeItem = (TextView)convertView.findViewById(R.id.TV_Item);
            Spinner sp_SituacaoItem;

            tv_nomeItem.setText(((ACT_CheckList)getActivity()).getClsItensEntrada().getItem(position));
            tv_nomeItem.setTag(position);

            //se vier foto do banco preenche com informação padrão para não abrir a camera ao clicar
            if(((ACT_CheckList)getActivity()).getClsItensEntrada().getCaminhoFotoItem(position) != null){
                imageButton.setTag("Foto");
                imageButton.setImageBitmap(BitmapFactory.decodeFile(((ACT_CheckList)getActivity()).getClsItensEntrada().getCaminhoFotoItem(position)));
            }

            final View finalConvertView = convertView;
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    botaoClicado = (ImageButton) v.findViewById(R.id.IMGBTN_ItemFoto);
                    posicaoAlterada = FU_retornaPosicao(finalConvertView);

                    if(botaoClicado.getTag()== null) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                    }
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
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            sp_SituacaoItem.setAdapter(adapterSituacaoItem);

            if(((ACT_CheckList)getActivity()).getClsItensEntrada().getStatusEquipItem(position) != null) {
                sp_SituacaoItem.setSelection(Arrays.asList(arraySituacoesBem).indexOf(((ACT_CheckList)getActivity()).getClsItensEntrada().getStatusEquipItem(position)));
            }

            return convertView;
        }

        private File getTempFile()
        {
            //it will return /sdcard/image.tmp
            return new File(Environment.getExternalStorageDirectory(),  "image.tmp");
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

                    Uri selectedImageUri = data.getData();
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContext().getContentResolver().query(selectedImageUri, proj, null, null, null);
                    if (cursor.moveToFirst()) {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        file_path = cursor.getString(column_index);
                    }
                    cursor.close();

                    ((ACT_CheckList)getActivity()).getClsItensEntrada().setCaminhoFotoItem(file_path, posicaoAlterada);

                    if(botaoClicado != null) {
                        botaoClicado.setTag("Foto");
                        clsUtil.FU_redimensionaImagemEColocaNaView(file_path, botaoClicado);
                    }

                    loop = false;
                }
                tries++;
                if(tries == 21)
                    loop = false;
            }
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
