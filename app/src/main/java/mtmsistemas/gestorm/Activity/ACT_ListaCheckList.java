package mtmsistemas.gestorm.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import mtmsistemas.gestorm.R;

public class ACT_ListaCheckList extends AppCompatActivity {

    String[] CheckListID = {"1", "2", "3", "4", "5"};

    String[] EQUIPAMENTO = {"Empilhadeira", "Caminhão", "Trem", "Avião", "Navio"};

    String[] CONTADOR = {"124", "129.023", "...", "1201", "..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            setContentView(R.layout.act_lista_check_list);

            ListView listView=(ListView)findViewById(R.id.lLST_CheckList);

            CustomAdapter customAdapter=new CustomAdapter();

            listView.setAdapter(customAdapter);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return CheckListID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.checklistcustomlayout, null);

            TextView LTV_Numero = (TextView)convertView.findViewById(R.id.CheckList_Numero);
            TextView LTV_Descricao=(TextView)convertView.findViewById(R.id.CheckList_Descricao);
            TextView LTV_Contador=(TextView)convertView.findViewById(R.id.CheckList_Contador);

            LTV_Numero.setText(CheckListID[position]);
            LTV_Descricao.setText(EQUIPAMENTO[position]);
            LTV_Contador.setText(CONTADOR[position]);

            return convertView;
        }
    }

}
