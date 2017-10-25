package mtmsistemas.gestorm.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mtmsistemas.gestorm.R;

public class ACT_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
    }

    public void onClick_Login(View view){

    }

    public void onClick_Sair(View view){
        System.exit(0);
    }
}
