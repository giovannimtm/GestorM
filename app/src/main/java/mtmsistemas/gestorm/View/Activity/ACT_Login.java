package mtmsistemas.gestorm.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import mtmsistemas.gestorm.BuildConfig;
import mtmsistemas.gestorm.Controller.ClsAutenticacao;
import mtmsistemas.gestorm.Controller.ClsUtil;
import mtmsistemas.gestorm.Controller.ConexaoWebAPI;
import mtmsistemas.gestorm.Controller.PARAMETROSController;
import mtmsistemas.gestorm.Model.EMFSESSION;
import mtmsistemas.gestorm.Model.PARAMETROS;
import mtmsistemas.gestorm.R;

public class ACT_Login extends AppCompatActivity {

    Button BT_Login;
    EditText ET_Usuario;
    EditText ET_Senha;
    TextView TV_Webservice;
    Switch SW_Salvar;
    Cursor CU_Cursor;
    String LSTR_MENSAGEM;
    ProgressBar progressBar;
    private ProgressDialog load;
    String LSTR_STATUS;
    PARAMETROSController LCLS_PARAMETROSController;
    EMFSESSION LCLS_EMFSESSION = null;
    ClsUtil LCLS_UTIL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        BT_Login = (Button) findViewById(R.id.BT_Login);
        ET_Usuario = (EditText) findViewById(R.id.ET_Usuario);
        ET_Senha = (EditText) findViewById(R.id.ET_Senha);
        TV_Webservice = (TextView) findViewById(R.id.TV_WebService);
        SW_Salvar = (Switch) findViewById(R.id.SW_Salvar);

        LCLS_EMFSESSION = new EMFSESSION(null);
        LCLS_UTIL = new ClsUtil();
        PARAMETROSController.contexts = getBaseContext();
        LCLS_PARAMETROSController = new PARAMETROSController(getBaseContext());

        //Buscar parametros já registrados
        CU_Cursor = LCLS_PARAMETROSController.FU_Read_BD();
        SW_Salvar.setChecked(false);

        if (CU_Cursor.getCount() > 0) {
            PARAMETROS.setPstrEnderecowebapi(CU_Cursor.getString(CU_Cursor.getColumnIndex("ENDERECOWEBAPI")).trim());
            EMFSESSION.LOCAL_NMUSUARIO = CU_Cursor.getString(CU_Cursor.getColumnIndex("NMUSUARIO")).trim();
            ET_Usuario.setText(EMFSESSION.LOCAL_NMUSUARIO.toUpperCase() );
            if (!EMFSESSION.LOCAL_NMUSUARIO.equals(""))
                SW_Salvar.setChecked(true);
        } else {
            Intent intent = new Intent(getApplicationContext(), ACT_Configuracao.class);
            startActivity(intent);

        }

        if (BuildConfig.DEBUG) {
            //ET_Usuario.setText("MASTER");
            ET_Senha.setText("MTM");
            LCLS_EMFSESSION.setSGENVIRONMENT("GESTOR");
            LCLS_EMFSESSION.setSGLANGUAGE("PT-BR");
            try {
                LCLS_EMFSESSION.setDHSESSION(LCLS_UTIL.FU_DataAtual());
            } catch (Exception e) {
                e.printStackTrace();
            }
            LCLS_EMFSESSION.setEQUIPMENT(Build.MODEL);
        }

        BT_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (ET_Usuario.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Falta preencher Login", Toast.LENGTH_LONG).show();
                    return;
                }
                if (ET_Senha.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Falta preencher Senha", Toast.LENGTH_LONG).show();
                    return;
                }

                LCLS_EMFSESSION.setSGUSER(ET_Usuario.getText().toString());
                LCLS_EMFSESSION.setPASSWORD(ET_Senha.getText().toString());
                LCLS_EMFSESSION.setSGENVIRONMENT("GESTOR");
                LCLS_EMFSESSION.setSGLANGUAGE("PT-BR");
                LCLS_EMFSESSION.setDHSESSION("0");
                LCLS_EMFSESSION.setEQUIPMENT("2");

                EMFSESSION.LOCAL_NMUSUARIO = ET_Usuario.getText().toString();
                FU_chamarWebservice();
            }
        });

        TV_Webservice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // aqui voce faz alguma coisa ou pode chamar uma funcao
                Intent intent = new Intent(getApplicationContext(), ACT_Configuracao.class);
                startActivity(intent);
            }
        });

        SW_Salvar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    EMFSESSION.LOCAL_NMUSUARIO = ET_Usuario.getText().toString();
                    LCLS_PARAMETROSController.FU_Update_BD(0);
                } else {
                    //do something when unchecked
                    EMFSESSION.LOCAL_NMUSUARIO = "";
                    LCLS_PARAMETROSController.FU_Update_BD(0);
                }
            }
        });
    }

    public String FU_chamarWebservice() {
        Autenticacao autentica = new Autenticacao();
        //Chama Async Task
        autentica.execute();
        return LSTR_STATUS;
    }

    public void onClick_Sair(View view) {
        System.exit(0);
    }

    public void onClick_WebService(View view) {
        Intent intent = new Intent(ACT_Login.this, ACT_ConfigWebService.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class Autenticacao extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_Login.this, "Por favor Aguarde ...", LSTR_MENSAGEM);
        }

        @Override
        protected String doInBackground(Void... params) {
            LSTR_MENSAGEM = "Verificando Conexão a internet";
            runOnUiThread(changeText);

            ClsUtil util = new ClsUtil();
            if (util.verificaConexao(ACT_Login.this)) {
                LSTR_MENSAGEM = "Conectado!";
            } else {
                LSTR_MENSAGEM = "Não conectado, Trabalhando Offiline";
                runOnUiThread(changeText);
                //return LSTR_STATUS = "Não conectado";
            }
            runOnUiThread(changeText);
            LSTR_MENSAGEM = "Verificando tipo Conexão";
            runOnUiThread(changeText);

            LSTR_MENSAGEM = util.FU_verificaTipoConexao(ACT_Login.this);
            if (LSTR_MENSAGEM == "?") {
                LSTR_MENSAGEM = "WIFI";
            }
            runOnUiThread(changeText);

            try {
                ConexaoWebAPI.FU_WB_TestaConexao();
            } catch (IOException e) {
                e.printStackTrace();
            }


            LSTR_MENSAGEM = "Autenticando";
            runOnUiThread(changeText);
            LSTR_STATUS = ClsAutenticacao.FU_AutenticaUsuario(LCLS_EMFSESSION);
            return LSTR_STATUS;
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();

            if (LSTR_STATUS.equals("true")) {
                Toast.makeText(getApplicationContext(), "Autenticado", Toast.LENGTH_LONG).show();
                //
                // ((MainActivity) getApplicationContext()).FU_Atualiza_nome();
                finish();
            } else if (LSTR_STATUS.toUpperCase().contains("EXCEPTION"))
                Toast.makeText(getApplication(), LSTR_STATUS, Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getApplication(), LSTR_STATUS, Toast.LENGTH_LONG).show();
            }
        }

        private Runnable changeText = new Runnable() {
            @Override
            public void run() {
                load.setMessage(LSTR_MENSAGEM);
            }
        };

    }

}

