package mtmsistemas.gestorm;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import mtmsistemas.gestorm.Model.EMFSESSION;
import mtmsistemas.gestorm.Model.WEBAPI;
import mtmsistemas.gestorm.View.Activity.ACT_Configuracao;
import mtmsistemas.gestorm.View.Activity.ACT_Login;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog load;
    TextView TV_NMUsuario;
    public String STR_Usuario;
    Timer timerObj = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Obtém a referência do layout de navegação
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Obtém a referência da view de cabeçalho
        View headerView = navigationView.getHeaderView(0);

        // Obtém a referência do nome do usuário e altera seu nome
        TV_NMUsuario = (TextView) headerView.findViewById(R.id.TV_Usuario_Nome);

        if (EMFSESSION.LOCAL_IDSESSION == 0) {
            Intent intent = new Intent(getApplicationContext(), ACT_Login.class);
            startActivity(intent);

            //timerObj.schedule(timerTaskObj, 48000, 120000);

        } else {
            TV_NMUsuario.setText(EMFSESSION.LOCAL_NMUSUARIO.toUpperCase());
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick_CheckList(View View) {

    }

    public void onClick_Equipamento(View View) {

    }

    public void onClick_Parametro(View View) {
        Intent intent = new Intent(getApplicationContext(), ACT_Configuracao.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        // put your code here...

        //timerObj.schedule(timerTaskObj, 120000, 120000);
    }

    @Override
    public void onPause() {
        super.onPause();
        // put your code here...
        //timerObj.schedule(timerTaskObj, 120000, 120000);
//        }
    }

    public void existeapp() {
        super.onDestroy();
    }
    TimerTask timerTaskObj = new TimerTask() {
        public void run() {
            if (EMFSESSION.LOCAL_IDSESSION != 0) {
                TV_NMUsuario.setText(EMFSESSION.LOCAL_NMUSUARIO.toUpperCase());
                timerObj.cancel();
                timerObj.purge();
            } else {
                existeapp();
            }
        }
    };

}
