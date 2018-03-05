package mtmsistemas.gestorm.View.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import mtmsistemas.gestorm.R;

public class FGM_CheckList_Detalhes extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fgm_check_list_detalhes, container, false);

        EditText ET_idCheckList = (EditText)v.findViewById(R.id.ET_IdCheckList);
        ET_idCheckList.setEnabled(false);
        ET_idCheckList.setBackgroundColor(Color.LTGRAY);

        EditText ET_OrdemServico = (EditText)v.findViewById(R.id.ET_OrdemServico);
        ET_OrdemServico.setEnabled(false);
        ET_OrdemServico.setBackgroundColor(Color.LTGRAY);

        EditText ET_Equipamento = (EditText)v.findViewById(R.id.ET_Equipamento);
        ET_Equipamento.setEnabled(false);
        ET_Equipamento.setBackgroundColor(Color.LTGRAY);

        Spinner sp_TipoCheckList, sp_SituacaoBem, sp_SituacaoCheckList;

        String[] array = {"teste", "1", "2"};
        sp_TipoCheckList = (Spinner)v.findViewById(R.id.Sp_TipoCheckList);
        ArrayAdapter<String> adapterTipoCheckList = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, array);
        sp_TipoCheckList.setAdapter(adapterTipoCheckList);

        sp_SituacaoBem = (Spinner)v.findViewById(R.id.Sp_SituacaoBem);
        String[] arraySituacoesBem = {"Otimo","Bom", "Normal", "Regular", "Pessimo"};
        ArrayAdapter<String> adapterSituacaoBem = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, arraySituacoesBem);
        sp_SituacaoBem.setAdapter(adapterSituacaoBem);

        sp_SituacaoCheckList = (Spinner)v.findViewById(R.id.Sp_SituacaoCheckList);
        String[] arraySituacoesCheckList = {"Aprovado","Rejeitado", "Manutenção"};
        ArrayAdapter<String> adapterSituacaoCheckList = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, arraySituacoesCheckList);
        sp_SituacaoCheckList.setAdapter(adapterSituacaoCheckList);

        final Calendar mDataAtual = Calendar.getInstance();

        final EditText dataEntrada = (EditText)v.findViewById(R.id.ET_DataEntrada);
        dataEntrada.setInputType(InputType.TYPE_NULL);
        dataEntrada.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int ano, int mes, int dia){
                        Calendar data = Calendar.getInstance();
                        data.set(ano, mes, dia);
                        dataEntrada.setText(DateFormat.format("dd/MM/yyyy", data));
                    }
                }, mDataAtual.get(Calendar.YEAR), mDataAtual.get(Calendar.MONTH), mDataAtual.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        return v;
    }
}