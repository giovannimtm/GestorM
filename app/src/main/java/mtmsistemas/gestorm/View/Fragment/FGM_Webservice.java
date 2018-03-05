package mtmsistemas.gestorm.View.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Activity.ACT_Configuracao;

public class FGM_Webservice extends Fragment {


    public FGM_Webservice() {
        // Required empty public constructor
    }
   String  LSTR_STATUS;
    View rootview;
    EditText ET_IDEQUIP;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fgm_webservice, container, false);
        //progressBar = (ProgressBar)container.findViewById(R.id.progressBar);
        ET_IDEQUIP = (EditText) rootview.findViewById(R.id.ET_WSendereco);
        //BTN_TestarConexao = (Button) container.findViewById(R.id.BT_Login);

        FU_(container);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fgm_webservice, container, false);
        return rootview;
    }


    public void FU_(View view )
    {
        //((ACT_Configuracao)getActivity()).FU_chamarWebservice();

        ((ACT_Configuracao)getActivity()).FU_chamarAutenticacao();

    }


}
