package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CHECKLISTITEM extends SQLiteOpenHelper {
    private Object IDCHECKLIST = null;
    private Object CDTIPOCOMPONENTE = null;
    private Object FGSITUACAO = null;
    private Object PCTAVALIACAO = null;
    private Object OBSERVACAO = null;
    private Object SGUSER = null;
    private Context context;
    private String IMAGEMCOMPONENTE = null;
    public static final String TABLE = "CHECKLISTITEM";

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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
