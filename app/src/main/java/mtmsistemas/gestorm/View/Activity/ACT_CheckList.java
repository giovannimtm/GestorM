package mtmsistemas.gestorm.View.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Controller.CHECKLISTITEMController;
import mtmsistemas.gestorm.Controller.ClsAutenticacao;
import mtmsistemas.gestorm.Controller.ClsUtil;
import mtmsistemas.gestorm.Model.CHECKLISTITEM;
import mtmsistemas.gestorm.Model.CHECKLISTMESTRE;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Fragment.FGM_CheckList_Detalhes;
import mtmsistemas.gestorm.View.Fragment.FGM_Itens;

public class ACT_CheckList extends AppCompatActivity {

    private ProgressDialog load;

    public static CHECKLISTMESTRE checkList;
    public static List<CHECKLISTITEM> checkListItems = new ArrayList<>();
    public static CHECKLISTITEMController controllerCheckListItem = new CHECKLISTITEMController(null);
    public static boolean[] escondeViews;
    static ClsUtil clsUtil = new ClsUtil();

    FGM_CheckList_Detalhes fgmCheckListDetalhes = new FGM_CheckList_Detalhes();
    FGM_Itens fgmItens = new FGM_Itens();

    public CHECKLISTMESTRE getCheckList(){ return checkList; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clsUtil.FU_permissoes(this.getApplicationContext(), this);

        if (checkListItems == null)
            checkListItems = new ArrayList<CHECKLISTITEM>();
        if (checkListItems.size() == 0) {
            Webservice webservice = new Webservice();
            webservice.execute();
        }

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
            load = ProgressDialog.show(ACT_CheckList.this, "Por favor Aguarde ...", "Carregando informações do Check List");
        }

        @Override
        protected String doInBackground(Void... params) {
            ClsAutenticacao.FU_AutenticaUsuario(null);
            CHECKLISTITEM checkListItemVazio = new CHECKLISTITEM(null);
            checkListItems = controllerCheckListItem.FU_Read_WB(checkListItemVazio,
                    Integer.parseInt(Double.toString((Double) checkList.getIDCHECKLIST()).replace(".0", "")));
            return "";
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();
            fgmCheckListDetalhes.SU_PopulaCampos();
            escondeViews = new boolean[checkListItems.size()];
            Arrays.fill(escondeViews, true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        checkListItems = null;
    }
}