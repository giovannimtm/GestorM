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

import java.util.Arrays;
import java.util.Calendar;

import mtmsistemas.gestorm.Model.CHECKLISTMESTRE;
import mtmsistemas.gestorm.R;
import mtmsistemas.gestorm.View.Activity.ACT_CheckList;

public class FGM_CheckList_Detalhes extends Fragment {
    String[] listaTipoCheckList = {"teste", "1", "2"};
    String[] arraySituacoesBem = {"Otimo","Bom", "Normal", "Regular", "Pessimo"};
    String[] arraySituacoesCheckList = {"Aprovado","Rejeitado", "Manutenção"};
    static View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fgm_check_list_detalhes, container, false);

//        EditText ET_idCheckList = (EditText)v.findViewById(R.id.ET_IdCheckList);
//        ET_idCheckList.setEnabled(false);
//        ET_idCheckList.setBackgroundColor(Color.LTGRAY);
//
//        EditText ET_OrdemServico = (EditText)v.findViewById(R.id.ET_OrdemServico);
//        ET_OrdemServico.setEnabled(false);
//        ET_OrdemServico.setBackgroundColor(Color.LTGRAY);
//
//        EditText ET_Equipamento = (EditText)v.findViewById(R.id.ET_Equipamento);
//        ET_Equipamento.setEnabled(false);
//        ET_Equipamento.setBackgroundColor(Color.LTGRAY);

        Spinner sp_TipoCheckList, sp_SituacaoBem, sp_SituacaoCheckList;

        sp_TipoCheckList = (Spinner)v.findViewById(R.id.Sp_TipoCheckList);
        ArrayAdapter<String> adapterTipoCheckList = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, listaTipoCheckList);
        sp_TipoCheckList.setAdapter(adapterTipoCheckList);

//        sp_SituacaoBem = (Spinner)v.findViewById(R.id.Sp_SituacaoBem);
//        ArrayAdapter<String> adapterSituacaoBem = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, arraySituacoesBem);
//        sp_SituacaoBem.setAdapter(adapterSituacaoBem);

        sp_SituacaoCheckList = (Spinner)v.findViewById(R.id.Sp_SituacaoCheckList);
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

        final EditText dataSaida = (EditText)v.findViewById(R.id.ET_DataSaida);
        dataSaida.setInputType(InputType.TYPE_NULL);
        dataSaida.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int ano, int mes, int dia){
                        Calendar data = Calendar.getInstance();
                        data.set(ano, mes, dia);
                        dataSaida.setText(DateFormat.format("dd/MM/yyyy", data));
                    }
                }, mDataAtual.get(Calendar.YEAR), mDataAtual.get(Calendar.MONTH), mDataAtual.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        return v;
    }

    public void SU_PopulaCampos(){
        try {
            CHECKLISTMESTRE checklist = ((ACT_CheckList)getActivity()).getCheckList();

            if (checklist.getIDCHECKLIST() != null) {
                EditText ET_idCheckList = (EditText) v.findViewById(R.id.ET_IdCheckList);
                ET_idCheckList.setEnabled(false);
                ET_idCheckList.setBackgroundColor(Color.LTGRAY);
                ET_idCheckList.setText(checklist.getIDCHECKLIST().toString());
            }

            if (checklist.getIDORDEMSERVICO() != null) {
                EditText ET_OrdemServico = (EditText) v.findViewById(R.id.ET_OrdemServico);
                ET_OrdemServico.setEnabled(false);
                ET_OrdemServico.setBackgroundColor(Color.LTGRAY);
                ET_OrdemServico.setText(checklist.getIDORDEMSERVICO().toString());
            }

            if (checklist.getIDEQUIPAMENTO() != null) {
                EditText ET_IdEquipamento = (EditText) v.findViewById(R.id.ET_IdEquipamento);
                ET_IdEquipamento.setEnabled(false);
                ET_IdEquipamento.setBackgroundColor(Color.LTGRAY);
                ET_IdEquipamento.setText(checklist.getIDEQUIPAMENTO().toString());
            }

            //falta pegar referencia e descrição equipamento

            if (checklist.getCDTIPOCHECKLIST() != null) {
                Spinner sp_TipoCheckList = (Spinner) v.findViewById(R.id.Sp_TipoCheckList);
                sp_TipoCheckList.setSelection(Arrays.asList(listaTipoCheckList).indexOf(checklist.getCDTIPOCHECKLIST()));
            }

            if (checklist.getCDCADASTRORESP() != null) {
                EditText ET_IdResponsavel = (EditText) v.findViewById(R.id.ET_IdResponsavel);
                ET_IdResponsavel.setEnabled(false);
                ET_IdResponsavel.setBackgroundColor(Color.LTGRAY);
                ET_IdResponsavel.setText(checklist.getCDCADASTRORESP().toString());
            }

            //falta pegar nome responsavel

            if (checklist.getCDCENTRORESULTADO() != null) {
                EditText ET_IdCentroResultado = (EditText) v.findViewById(R.id.ET_IdCentroResultado);
                ET_IdCentroResultado.setEnabled(false);
                ET_IdCentroResultado.setBackgroundColor(Color.LTGRAY);
                ET_IdCentroResultado.setText(checklist.getCDCENTRORESULTADO().toString());
            }

            //falta pegar descrição centro resultado

            if (checklist.getCONTAGEMUSO() != null) {
                EditText ET_ContadorUso = (EditText) v.findViewById(R.id.ET_ContadorUso);
                ET_ContadorUso.setEnabled(false);
                ET_ContadorUso.setBackgroundColor(Color.LTGRAY);
                ET_ContadorUso.setText(checklist.getCONTAGEMUSO().toString());
            }

            //data entrada e data saida vem da ordem de serviço

            if (checklist.getFGSITUACAO() != null) {
                Spinner sp_SituacaoCheckList = (Spinner) v.findViewById(R.id.Sp_SituacaoCheckList);
                sp_SituacaoCheckList.setSelection(Arrays.asList(arraySituacoesCheckList).indexOf(checklist.getFGSITUACAO()));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}