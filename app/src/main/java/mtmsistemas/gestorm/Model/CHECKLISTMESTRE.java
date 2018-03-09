package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CHECKLISTMESTRE extends SQLiteOpenHelper {
    public static final String TABLE = "CHECKLISTMESTRE";

    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE + "/read";
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE + "/delete";

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
        try{
        String CREATE_TABLE = "CREATE TABLE" + TABLE + "("
                + "IDCHECKLIST" + " integer primary key autoincrement,"
                + "IDEQUIPAMENTO" + " text,"
                + "CDTIPOCHECKLIST" + " text,"
                + "CDCADASTRORESP" + " text,"
                + "CONTAGEMUSO" + " text,"
                + "DTREALIZADO" + " text,"
                + "LOCALREALIZADO" + " text,"
                + "FGSITUACAO" + " text,"
                + "OBSERVACAO" + " text,"
                + "IDORDEMSERVICO" + " text,"
                + "CDCENTRORESULTADO" + " text,"
                + "IMAGEMCOMPONENTE" + " text"
                + ")";
        db.execSQL(CREATE_TABLE);
    } catch (Exception e) {
        e.printStackTrace();
        //NotificationCompat.MessagingStyle.Message.message(context,""+e);
    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE);
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
            //NotificationCompat.MessagingStyle.Message.message(context,""+e);
        }
    }
}
