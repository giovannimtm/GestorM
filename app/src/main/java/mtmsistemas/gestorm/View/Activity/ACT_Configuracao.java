package mtmsistemas.gestorm.View.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import mtmsistemas.gestorm.Controller.ClsAutenticacao;
import mtmsistemas.gestorm.Controller.EMFSESSIONConroller;
import mtmsistemas.gestorm.Controller.EquipamentoRestClient;
import mtmsistemas.gestorm.Controller.NetworkUtils;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Fragment.FGM_Webservice;

public class ACT_Configuracao extends AppCompatActivity {
    private FragmentTabHost mTabHost;

    int LINT_PERMISSION = 21;
    ProgressBar progressBar;
    private ProgressDialog load;
    String LSTR_STATUS;
    Button BTN_TestarConexao;

    EditText ET_IDEQUIP;
    EditText ET_DSEQUIP;

    EditText ET_WsService;
    String LSTR_MENSAGEM = "AUTENTICANDO NO SERVIDOR";


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_configuracao);        // Set the view from main_fragment.xml
        EMFSESSIONConroller.contexts = getBaseContext();

//     setContentView(R.layout.main_fragment);
//
//        // Locate android.R.id.tabhost in main_fragment.xml
//        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//
//        // Create the tabs in main_fragment.xml
//        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);
//
//        // Create Tab1 with a custom image in res folder
//        mTabHost.addTab(mTabHost.newTabSpec("Webservice").setIndicator("", getResources().getDrawable(R.drawable.ic_menu_camera)),
//                FGM_Webservice.class, null);
//
//        // Create Tab2
//        mTabHost.addTab(mTabHost.newTabSpec("WebserviceLis").setIndicator("Tab2"),
//                FGM_WebserviceList.class, null);
        FragmentManager fm = getFragmentManager();

        // Abre uma transação e adiciona
        FragmentTransaction ft = fm.beginTransaction();
        //ft.add(R.id.FGM_ContentList, new FGM_Webservice());
        ft.replace(R.id.FGM_ContentList, new FGM_Webservice());
        ft.commit();
//        ft = fm.beginTransaction();
//        ft.add(R.id.FGM_ContentLista, new BlankFragment() );
//        ft.commit();

//        ClsAutenticacao.FU_AutenticaUsuario();

    }

    public String FU_chamarWebservice() {
        GetJson download = new GetJson();
        //Chama Async Task
        download.execute();
        return LSTR_STATUS;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class GetJson extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_Configuracao.this, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected String doInBackground(Void... params) {
            String resposta = NetworkUtils.get("http://192.168.0.104:8021/GiclPLibWebAPI/api/gestoricl/status/8021");

            LSTR_STATUS = resposta;

            return LSTR_STATUS;
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();
            if (LSTR_STATUS.equals("true"))
                Toast.makeText(getApplicationContext(), "Conexão Satisfatória",
                        Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public String FU_chamarAutenticacao() {

        Autenticacao download = new Autenticacao();
        //Chama Async Task
        download.execute();

        return LSTR_STATUS;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class Autenticacao extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_Configuracao.this, "Por favor Aguarde ...", LSTR_MENSAGEM);
        }

        @Override
        protected String doInBackground(Void... params) {
            LSTR_STATUS = ClsAutenticacao.FU_AutenticaUsuario();
            LSTR_MENSAGEM = "Processo encerrado";
            LSTR_MENSAGEM = "Procurando equipamentos";
            try {
                EquipamentoRestClient.FU_LIST_EQUIPAMENTO();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LSTR_MENSAGEM = "processado";
            return LSTR_STATUS;
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();

            if (LSTR_STATUS.equals("true"))
                Toast.makeText(getApplicationContext(), "Autenticado", Toast.LENGTH_LONG).show();
            else if (LSTR_STATUS.toUpperCase().contains("EXCEPTION"))
                Toast.makeText(getApplication(), LSTR_STATUS, Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getApplication(), LSTR_STATUS, Toast.LENGTH_LONG).show();
            }
        }

//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            fragmentA.onActivityResult(requestCode,resultCode,data);
//            //É necessário fazer isso para que o fragmentA tenha seu método onActivityResult chamado
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, data);
    }
}
