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
import mtmsistemas.gestorm.Fragment.FGM_ItensEntrada;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.Util.ClsItensEntrada;

public class ACT_CheckList extends AppCompatActivity {
    public ClsItensEntrada getClsItensEntrada() {
        return clsItensEntrada;
    }

    static ClsItensEntrada clsItensEntrada = new ClsItensEntrada();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_check_list);

        FGM_CheckList_Detalhes fgmCheckListDetalhes = new FGM_CheckList_Detalhes();
        FGM_ItensEntrada fgmItensEntrada = new FGM_ItensEntrada();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fgmCheckListDetalhes, "fgmCheckListDetalhes");
        ft.add(fgmItensEntrada, "fgmItensEntrada");
        ft.commit();

        FragmentTabHost mTabHost = (FragmentTabHost)findViewById(R.id.tabHostCheckList);
        mTabHost.setup(ACT_CheckList.this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("TabDetalhesCheckList").setIndicator("Detalhes"), fgmCheckListDetalhes.getClass(), null);
        mTabHost.addTab(mTabHost.newTabSpec("TabItensEntrada").setIndicator("Itens Entrada"), fgmItensEntrada.getClass(), null);
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
