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

import java.io.IOException;

import mtmsistemas.gestorm.Controller.ConexaoWebAPI;
import mtmsistemas.gestorm.Controller.PARAMETROSController;
import mtmsistemas.gestorm.Model.WEBAPI;
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
    Boolean LBOL_Conexao = false;
    PARAMETROSController LCLS_PARAMETROSController;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_configuracao);        // Set the view from main_fragment.xml

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
        PARAMETROSController.contexts = getBaseContext();
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
        ConectaWebApi download = new ConectaWebApi();
        //Chama Async Task
        download.execute();
        return LSTR_STATUS;
    }


    public String FU_testeWebservice(String str_endereco) {
        SU_testaCONEXAO verificaCONEXAO = new SU_testaCONEXAO();
        //Chama Async Task
        verificaCONEXAO.execute(str_endereco);
        return LSTR_STATUS;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class ConectaWebApi extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_Configuracao.this, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected String doInBackground(Void... params) {
            String resposta = null;
            try {
                resposta = ConexaoWebAPI.FU_WB_TestaConexao().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            LSTR_STATUS = resposta;
            return LSTR_STATUS;
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();
            if (LSTR_STATUS.equals("true"))
                Toast.makeText(getApplicationContext(), "Conexão Satisfatória",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Não encontrado!",
                        Toast.LENGTH_LONG).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class SU_testaCONEXAO extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_Configuracao.this, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            LBOL_Conexao = WEBAPI.PBOL_Conectado;
            if (LBOL_Conexao) {
                WEBAPI.setPstrEnderecowebapi(params[0].toString().trim() + ":8021/GiclPLibWebAPI/api");
                LCLS_PARAMETROSController = new PARAMETROSController(getApplicationContext());
                LCLS_PARAMETROSController.FU_Delete_BD(0);
                LCLS_PARAMETROSController.FU_Insert_BD(null);

            }
            return "";
        }

        @Override
        protected void onPostExecute(String Status) {
            load.dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, data);
    }
}
