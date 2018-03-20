package mtmsistemas.gestorm.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import mtmsistemas.gestorm.Controller.CHECKLISTMESTREController;
import mtmsistemas.gestorm.Controller.ClsAutenticacao;
import mtmsistemas.gestorm.Controller.ClsUtil;
import mtmsistemas.gestorm.Model.CHECKLISTMESTRE;
import mtmsistemas.gestorm.R;

public class ACT_ListaCheckList extends AppCompatActivity {

    private ProgressDialog load;
    public static List<CHECKLISTMESTRE> checkLists;
    private static CHECKLISTMESTREController controllerCheckList = new CHECKLISTMESTREController(null);
    private static ListView listView;
    static ImageButton botaoClicado;
    static ClsUtil clsUtil = new ClsUtil();
    static int posicaoAlterada;
    static String caminhoFoto;
    private static final int CAMERA_PIC_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Webservice webservice = new Webservice();
        webservice.execute();

        setContentView(R.layout.act_lista_check_list);

        listView = (ListView) findViewById(R.id.LST_CheckList);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class Webservice extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_ListaCheckList.this, "Por favor Aguarde ...", "Carregando lista de Check Lists");
        }

        @Override
        protected String doInBackground(Void... params) {
            ClsAutenticacao.FU_AutenticaUsuario(null);
            CHECKLISTMESTRE checkListVazio = new CHECKLISTMESTRE(null);
            checkLists = controllerCheckList.FU_Read_WB(checkListVazio, 0, "A");
            return "";
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();
            CustomAdapter customAdapter = new CustomAdapter();
            listView.setAdapter(customAdapter);
        }
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return checkLists.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout, null);

            final CHECKLISTMESTRE itemSelecionado = checkLists.get(position);

            final ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.IMGBTN_FotoGeral);
            TextView LTV_Nome = (TextView) convertView.findViewById(R.id.CheckList_Numero);
            TextView LTV_Descricao = (TextView) convertView.findViewById(R.id.CheckList_Descricao);
            TextView LTV_Contador = (TextView) convertView.findViewById(R.id.Equipamento_Contador);

            LTV_Nome.setText(Double.toString((Double) itemSelecionado.getIDCHECKLIST()).replace(".0", ""));
            LTV_Nome.setTag(position);

            final View finalConvertView = convertView;
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    botaoClicado = (ImageButton) v.findViewById(R.id.IMGBTN_FotoGeral);
                    posicaoAlterada = FU_retornaPosicao(finalConvertView);
                    TextView textView = (TextView) finalConvertView.findViewById(R.id.CheckList_Numero);

                    if (botaoClicado.getTag() == null) {
                        File foto = clsUtil.FU_criaArquivoImagemNaPAsta(textView.getText().toString());
                        caminhoFoto = foto.getAbsolutePath();

                        // save image here
                        Intent intent = clsUtil.FU_salvaFotoPastaEspecifica(foto);
                        startActivityForResult(intent, CAMERA_PIC_REQUEST);
                    } else {
                        Intent intent = clsUtil.FU_visualizarFotosDaPasta(getApplicationContext(), textView.getText().toString());
                        startActivity(intent);
                    }
                }
            });

            File pastaItem = new File(
                    Environment.getExternalStorageDirectory() + "/DCIM/Gestor/" + LTV_Nome.getText().toString()
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
            } else
                imageButton.setTag(null);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ACT_ListaCheckList.this, ACT_CheckList.class);
                    ACT_CheckList.checkList = checkLists.get(position);
                    startActivity(intent);
                }
            });

            return convertView;
        }
    }

    private int FU_retornaPosicao(View v) {
        int posicao;
        TextView tv_nomeItem = (TextView) v.findViewById(R.id.CheckList_Numero);
        posicao = (int) tv_nomeItem.getTag();
        return posicao;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int tries;
        boolean loop;
        String file_path = "";
        try {
            loop = true;
            tries = 1;
            while (loop == true) {
                Thread.sleep(500);
                if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {

                    file_path = caminhoFoto;

                    Intent mediaScannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File f = new File(file_path);
                    Uri fileContentUri = Uri.fromFile(f);
                    mediaScannerIntent.setData(fileContentUri);
                    this.sendBroadcast(mediaScannerIntent);

                    if (botaoClicado != null) {
                        checkLists.get(posicaoAlterada).setIMAGEMCOMPONENTE(file_path);

                        botaoClicado.setTag("Foto");
                        try {
                            clsUtil.FU_redimensionaImagemEColocaNaView(
                                    file_path,
                                    botaoClicado,
                                    botaoClicado.getHeight(),
                                    botaoClicado.getWidth());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    loop = false;
                }
                tries++;
                if (tries == 21 || resultCode != RESULT_OK)
                    loop = false;
            }
            botaoClicado = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkLists != null) {
            CustomAdapter customAdapter = new CustomAdapter();
            listView.setAdapter(customAdapter);
        }
    }
}
