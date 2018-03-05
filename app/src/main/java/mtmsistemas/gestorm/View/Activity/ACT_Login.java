package mtmsistemas.gestorm.View.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import mtmsistemas.gestorm.R;

public class ACT_Login extends AppCompatActivity  {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        btnLogin = (Button) findViewById(R.id.BT_Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // aqui voce faz alguma coisa ou pode chamar uma funcao
                Intent intent = new Intent(ACT_Login.this, ACT_ListaEquipamento.class);
                startActivity(intent);
            }
        });
    }

    public void onClick_Sair(View view){
        System.exit(0);
    }

    public void onClick_WebService(View view){
        Intent intent = new Intent(ACT_Login.this, ACT_ConfigWebService.class);
        startActivity(intent);
    }
}
