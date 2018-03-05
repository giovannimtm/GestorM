package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CHECKLISTMESTRE extends SQLiteOpenHelper {

    private Object IDCHECKLIST = null;
    private Object IDEQUIPAMENTO = null;
    private Object CDTIPOCHECKLIST = null;
    private Object CDCADASTRORESP = null;
    private Object CONTAGEMUSO = null;
    private Object DTREALIZADO = null;
    private Object LOCALREALIZADO = null;
    private Object FGSITUACAO = null;
    private Object OBSERVACAO = null;
    private Object IDORDEMSERVICO = null;
    private Object CDCENTRORESULTADO = null;
    private String IMAGEMCOMPONENTE = null;
    private Context context;
    private static final String TABLE = "CHECKLISTMESTRE";

    public CHECKLISTMESTRE(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
        this.context = context;
    }

    public Object getIDCHECKLIST() {
        return IDCHECKLIST;
    }

    public void setIDCHECKLIST(Object IDCHECKLIST) {
        this.IDCHECKLIST = IDCHECKLIST;
    }

    public Object getIDEQUIPAMENTO() {
        return IDEQUIPAMENTO;
    }

    public void setIDEQUIPAMENTO(Object IDEQUIPAMENTO) {
        this.IDEQUIPAMENTO = IDEQUIPAMENTO;
    }

    public Object getCDTIPOCHECKLIST() {
        return CDTIPOCHECKLIST;
    }

    public void setCDTIPOCHECKLIST(Object CDTIPOCHECKLIST) {
        this.CDTIPOCHECKLIST = CDTIPOCHECKLIST;
    }

    public Object getCDCADASTRORESP() {
        return CDCADASTRORESP;
    }

    public void setCDCADASTRORESP(Object CDCADASTRORESP) {
        this.CDCADASTRORESP = CDCADASTRORESP;
    }

    public Object getCONTAGEMUSO() {
        return CONTAGEMUSO;
    }

    public void setCONTAGEMUSO(Object CONTAGEMUSO) {
        this.CONTAGEMUSO = CONTAGEMUSO;
    }

    public Object getDTREALIZADO() {
        return DTREALIZADO;
    }

    public void setDTREALIZADO(Object DTREALIZADO) {
        this.DTREALIZADO = DTREALIZADO;
    }

    public Object getLOCALREALIZADO() {
        return LOCALREALIZADO;
    }

    public void setLOCALREALIZADO(Object LOCALREALIZADO) {
        this.LOCALREALIZADO = LOCALREALIZADO;
    }

    public Object getFGSITUACAO() {
        return FGSITUACAO;
    }

    public void setFGSITUACAO(Object FGSITUACAO) {
        this.FGSITUACAO = FGSITUACAO;
    }

    public Object getOBSERVACAO() {
        return OBSERVACAO;
    }

    public void setOBSERVACAO(Object OBSERVACAO) {
        this.OBSERVACAO = OBSERVACAO;
    }

    public Object getIDORDEMSERVICO() {
        return IDORDEMSERVICO;
    }

    public void setIDORDEMSERVICO(Object IDORDEMSERVICO) {
        this.IDORDEMSERVICO = IDORDEMSERVICO;
    }

    public Object getCDCENTRORESULTADO() {
        return CDCENTRORESULTADO;
    }

    public void setCDCENTRORESULTADO(Object CDCENTRORESULTADO) {
        this.CDCENTRORESULTADO = CDCENTRORESULTADO;
    }

    public String getIMAGEMCOMPONENTE() {
        return IMAGEMCOMPONENTE;
    }

    public void setIMAGEMCOMPONENTE(String IMAGEMCOMPONENTE) {
        this.IMAGEMCOMPONENTE = IMAGEMCOMPONENTE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
