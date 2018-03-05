package mtmsistemas.gestorm.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import mtmsistemas.gestorm.R;

/**
 * Created by Giovanni on 29/01/2018.
 */

public class ClsListViewAdapter extends BaseAdapter {


    // Declare Variables
    Context context;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;
    LayoutInflater inflater;

    public ClsListViewAdapter(Context context, String[] rank, String[] country,
                              String[] population, int[] flag) {
        this.context = context;
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    public int getCount() {
        return rank.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

        public View getView(int position, View convertView, ViewGroup parent) {
//            // Declare Variables
//            TextView txtrank;
//            TextView txtcountry;
//            TextView txtpopulation;
//            ImageView imgflag;
//
//            inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
////            View itemView = inflater.inflate(R.layout.fgm_webservice_list, parent, false);
                        View itemView = inflater.inflate(R.layout.fragment_blank, parent, false);
////
////            // Locate the TextViews in listview_item.xml
////            txtrank = (TextView) itemView.findViewById(R.id.rank);
////            txtcountry = (TextView) itemView.findViewById(R.id.country);
////            txtpopulation = (TextView) itemView.findViewById(R.id.population);
////            // Locate the ImageView in listview_item.xml
////            imgflag = (ImageView) itemView.findViewById(R.id.flag);
////
////            // Capture position and set to the TextViews
//            txtrank.setText(rank[position]);
//            txtcountry.setText(country[position]);
//            txtpopulation.setText(population[position]);
//
//            // Capture position and set to the ImageView
//            imgflag.setImageResource(flag[position]);
//
            return itemView;
        }
}
