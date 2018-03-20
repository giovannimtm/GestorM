package mtmsistemas.gestorm.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import mtmsistemas.gestorm.R;

public class ACT_ListaEquipamento extends AppCompatActivity {

    int[] IMAGEM = {R.drawable.ic_menu_gallery, R.drawable.ic_menu_camera, R.drawable.ic_menu_send, R.drawable.ic_menu_share, R.drawable.ic_menu_slideshow};

    String[] NOME = {"Empilhadeira", "Caminhão", "Trem", "Avião", "Navio"};

    String [] DESCRICAO = {"Empilhadeira Amarela", "Caminhão Preto", "Locomotiva", "747", "Titanic Azul"};

    String [] CONTADOR = {"124", "129.023", "...", "1201", "..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            setContentView(R.layout.act_lista_equipamento);

            ListView listView=(ListView)findViewById(R.id.LST_Equipamento);

            CustomAdapter customAdapter=new CustomAdapter();

            listView.setAdapter(customAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGEM.length;
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
            convertView = getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageButton imageButton=(ImageButton)convertView.findViewById(R.id.IMGBTN_FotoGeral);
            TextView LTV_Nome = (TextView)convertView.findViewById(R.id.Id);
            TextView LTV_Descricao=(TextView)convertView.findViewById(R.id.Descricao);
            TextView LTV_Contador=(TextView)convertView.findViewById(R.id.Equipamento_Contador);

            imageButton.setImageResource(IMAGEM[position]);
            LTV_Nome.setText(NOME[position].toString());
            LTV_Descricao.setText(DESCRICAO[position]);
            LTV_Contador.setText(CONTADOR[position]);

            return convertView;
        }
    }
}
