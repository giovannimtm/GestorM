package mtmsistemas.gestorm.View.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mtmsistemas.gestorm.Controller.CHECKLISTITEMController;
import mtmsistemas.gestorm.Controller.CHECKLISTMESTREController;
import mtmsistemas.gestorm.Controller.ClsAutenticacao;
import mtmsistemas.gestorm.Controller.ClsUtil;
import mtmsistemas.gestorm.Model.CHECKLISTITEM;
import mtmsistemas.gestorm.Model.CHECKLISTMESTRE;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Fragment.FGM_CheckList_Detalhes;
import mtmsistemas.gestorm.View.Fragment.FGM_Itens;

public class ACT_CheckList extends AppCompatActivity {

    private ProgressDialog load;
    String LSTR_MENSAGEM;

    public static List<CHECKLISTMESTRE> checkList;
    public static List<CHECKLISTITEM> checkListItems = new ArrayList<CHECKLISTITEM>();
    public static CHECKLISTMESTREController controllerCheckList = new CHECKLISTMESTREController(null);
    public static CHECKLISTITEMController controllerCheckListItem = new CHECKLISTITEMController(null);
    static ClsUtil clsUtil = new ClsUtil();

    FGM_CheckList_Detalhes fgmCheckListDetalhes = new FGM_CheckList_Detalhes();
    FGM_Itens fgmItens = new FGM_Itens();

    public CHECKLISTMESTRE getCheckList(int posicao){ return checkList.get(posicao); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clsUtil.FU_permissoes(this.getApplicationContext(), this);

        Webservice webservice = new Webservice();
        webservice.execute();

        setContentView(R.layout.act_check_list);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fgmCheckListDetalhes, "fgmCheckListDetalhes");
        ft.add(fgmItens, "fgmItens");
        ft.commit();

        FragmentTabHost mTabHost = (FragmentTabHost)findViewById(R.id.tabHostCheckList);
        mTabHost.setup(ACT_CheckList.this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("TabDetalhesCheckList").setIndicator("Detalhes"), fgmCheckListDetalhes.getClass(), null);
        mTabHost.addTab(mTabHost.newTabSpec("TabItensEntrada").setIndicator("Itens"), fgmItens.getClass(), null);

    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class Webservice extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_CheckList.this, "Por favor Aguarde ...", LSTR_MENSAGEM);
        }

        @Override
        protected String doInBackground(Void... params) {
            ClsAutenticacao.FU_AutenticaUsuario(null);
            CHECKLISTMESTRE checkListVazio = new CHECKLISTMESTRE(null);
            CHECKLISTITEM checkListItemVazio = new CHECKLISTITEM(null);
            checkList = controllerCheckList.FU_Read_WB(checkListVazio, 24);
            checkListItems = controllerCheckListItem.FU_Read_WB(checkListItemVazio, 24);
            return "";
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();
            @SuppressLint("ResourceType") View detalhesCheckList = findViewById(R.layout.fgm_check_list_detalhes);
            fgmCheckListDetalhes.SU_PopulaCampos();
        }
    }
}