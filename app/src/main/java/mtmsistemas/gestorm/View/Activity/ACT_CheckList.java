package mtmsistemas.gestorm.View.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import mtmsistemas.gestorm.Controller.CHECKLISTITEMController;
import mtmsistemas.gestorm.Controller.ClsUtil;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Fragment.FGM_CheckList_Detalhes;
import mtmsistemas.gestorm.View.Fragment.FGM_Itens;

public class ACT_CheckList extends AppCompatActivity {

    public static CHECKLISTITEMController clsItens = new CHECKLISTITEMController();
    static ClsUtil clsUtil = new ClsUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clsUtil.FU_permissoes(this.getApplicationContext(), this);

        setContentView(R.layout.act_check_list);

        FGM_CheckList_Detalhes fgmCheckListDetalhes = new FGM_CheckList_Detalhes();
        FGM_Itens fgmItens = new FGM_Itens();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fgmCheckListDetalhes, "fgmCheckListDetalhes");
        ft.add(fgmItens, "fgmItens");
        ft.commit();

        FragmentTabHost mTabHost = (FragmentTabHost)findViewById(R.id.tabHostCheckList);
        mTabHost.setup(ACT_CheckList.this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("TabDetalhesCheckList").setIndicator("Detalhes"), fgmCheckListDetalhes.getClass(), null);
        mTabHost.addTab(mTabHost.newTabSpec("TabItensEntrada").setIndicator("Itens"), fgmItens.getClass(), null);

    }
}