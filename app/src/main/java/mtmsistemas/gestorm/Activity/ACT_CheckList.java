package mtmsistemas.gestorm.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

import mtmsistemas.gestorm.Fragment.FGM_AlteraEquipamento;
import mtmsistemas.gestorm.Fragment.FGM_CheckList_Detalhes;
import mtmsistemas.gestorm.R;

public class ACT_CheckList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_check_list);

        FGM_CheckList_Detalhes fgmCheckListDetalhes;

        fgmCheckListDetalhes = new FGM_CheckList_Detalhes();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fgmCheckListDetalhes, "fgmCheckListDetalhes");
        ft.commit();

        FragmentTabHost mTabHost = (FragmentTabHost)findViewById(R.id.tabHostCheckList);
        mTabHost.setup(ACT_CheckList.this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("TabDetalhesCheckList").setIndicator("Detalhes"), fgmCheckListDetalhes.getClass(), null);
        mTabHost.addTab(mTabHost.newTabSpec("Second Tab").setIndicator("Second Tab"), new FGM_AlteraEquipamento().getClass(), null);
        mTabHost.addTab(mTabHost.newTabSpec("Third Tab").setIndicator("Third Tab"), fgmCheckListDetalhes.getClass(), null);


//        Spinner sp_TipoCheckList;
//        String[] array = {"teste", "1", "2"};
//
//        sp_TipoCheckList = (Spinner)fgmCheckListDetalhes.getView().findViewById(R.id.Sp_TipoCheckList);
//        ArrayAdapter<String> adapterTipoCheckList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, array);
//
//        sp_TipoCheckList.setAdapter(adapterTipoCheckList);
//
////        fragment = (FGM_CheckList_Detalhes)getSupportFragmentManager().findFragmentByTag("fgmCheckListDetalhes");
    }
}
