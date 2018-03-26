package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CHECKLISTITEM extends SQLiteOpenHelper {
    public static final String TABLE = "CHECKLISTITEM";
    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE ;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE + "/delete";

    private Object IDCHECKLIST = null;
    private Object CDTIPOCOMPONENTE = null;
    private Object FGSITUACAO = null;
    private Object PCTAVALIACAO = null;
    private Object OBSERVACAO = null;
    private Object SGUSER = null;
    private Context context;
    private String IMAGEMCOMPONENTE = null;
    private String DESCRICAOCOMPONENTE = null;
    private Object IDSINCRINIZA = null;

    public Object getIDSINCRINIZA() {
        return IDSINCRINIZA;
    }

    public void setIDSINCRINIZA(Object IDSINCRINIZA) {
        this.IDSINCRINIZA = IDSINCRINIZA;
    }

    public CHECKLISTITEM(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
        this.context = context;
    }

    public Object getIDCHECKLIST() {
        return IDCHECKLIST;
    }

    public void setIDCHECKLIST(Object IDCHECKLIST) {
        this.IDCHECKLIST = IDCHECKLIST;
    }

    public Object getCDTIPOCOMPONENTE() {
        return CDTIPOCOMPONENTE;
    }

    public void setCDTIPOCOMPONENTE(Object CDTIPOCOMPONENTE) {
        this.CDTIPOCOMPONENTE = CDTIPOCOMPONENTE;
    }

    public Object getFGSITUACAO() {
        return FGSITUACAO;
    }

    public void setFGSITUACAO(Object FGSITUACAO) {
        this.FGSITUACAO = FGSITUACAO;
    }

    public Object getPCTAVALIACAO() {
        return PCTAVALIACAO;
    }

    public void setPCTAVALIACAO(Object PCTAVALIACAO) {
        this.PCTAVALIACAO = PCTAVALIACAO;
    }

    public Object getOBSERVACAO() {
        return OBSERVACAO;
    }

    public void setOBSERVACAO(Object OBSERVACAO) {
        this.OBSERVACAO = OBSERVACAO;
    }

    public Object getSGUSER() {
        return SGUSER;
    }

    public void setSGUSER(Object SGUSER) {
        this.SGUSER = SGUSER;
    }

    public String getIMAGEMCOMPONENTE() {
        return IMAGEMCOMPONENTE;
    }

    public void setIMAGEMCOMPONENTE(String IMAGEMCOMPONENTE) {
        this.IMAGEMCOMPONENTE = IMAGEMCOMPONENTE;
    }

    public String getDESCRICAOCOMPONENTE() {
        return DESCRICAOCOMPONENTE;
    }

    public void setDESCRICAOCOMPONENTE(String DESCRICAOCOMPONENTE) {
        this.DESCRICAOCOMPONENTE = DESCRICAOCOMPONENTE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String CREATE_TABLE = "CREATE TABLE" + TABLE + "("
                    + "CHECKLISTITEM" + " integer primary key autoincrement,"
                    + "IDCHECKLIST" + " text,"
                    + "CDTIPOCOMPONENTE" + " text,"
                    + "FGSITUACAO" + " text,"
                    + "PCTAVALIACAO" + " text,"
                    + "OBSERVACAO" + " text,"
                    + "SGUSER" + " text,"
                    + "OBSERVACAO" + " text,"
                    + "IDSINCRINIZA" + " text,"
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
