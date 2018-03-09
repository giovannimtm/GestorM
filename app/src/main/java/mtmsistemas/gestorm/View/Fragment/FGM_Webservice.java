package mtmsistemas.gestorm.View.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mtmsistemas.gestorm.Controller.PARAMETROSController;
import mtmsistemas.gestorm.Model.PARAMETROS;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Activity.ACT_Configuracao;

public class FGM_Webservice extends Fragment {


    public FGM_Webservice() {
        // Required empty public constructor
    }

    String LSTR_STATUS;
    View rootview;
    EditText ET_WService;
    Button BTN_Conectar;
    Button BTN_Cancelar;
    PARAMETROSController LCLS_PARAMETROSController;
    Boolean LBOL_Conexao = false;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fgm_webservice, container, false);
        //progressBar = (ProgressBar)container.findViewById(R.id.progressBar);
        ET_WService = (EditText) rootview.findViewById(R.id.ET_WSendereco);
        BTN_Conectar = (Button) rootview.findViewById(R.id.BTN_Conectar);
        BTN_Cancelar = (Button) rootview.findViewById(R.id.BTN_Cancelar);
        ET_WService.setText("192.168.0.103");
        BTN_Conectar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LCLS_PARAMETROSController = new PARAMETROSController(getActivity());
                if (!ET_WService.getText().toString().toUpperCase().contains("HTTP://"))
                    ET_WService.setText("http://" + ET_WService.getText().toString());

                PARAMETROS.setPstrEnderecowebapi(ET_WService.getText().toString() + ":8021/GiclPLibWebAPI/api/gestoricl/status/8021");


                ((ACT_Configuracao) getActivity()).FU_chamarWebservice();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                LBOL_Conexao = PARAMETROS.PBOL_Conectado;
                if (LBOL_Conexao) {
                    LCLS_PARAMETROSController.FU_Delete_BD(0);
                    LCLS_PARAMETROSController.FU_Insert_BD(null);
                }
            }
        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fgm_webservice, container, false);
        return rootview;
    }

    public void onResume() {
        super.onResume();
        // put your code here...
    }

    @Override
    public void onPause() {
        super.onPause();
        // put your code here...
    }
}
