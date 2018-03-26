package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vinicius on 25/10/2017.
 */

public class EQUIPAMENTOCOMPONENTE extends SQLiteOpenHelper {
    public static String TABLE = "EQUIPAMENTOCOMPONENTE";
    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE + "/delete";


    private Object IDEQUIPAMENTO = null;
    private Object SEQUENCIA = null;
    private Object IDCOMPONENTE = null;
    private Object CDTIPOCOMPONENTE = null;
    private Object QUANTIDADE = null;
    private Object DHCOLOCACAO = null;
    private Object CONTAGEMUSO = null;
    private Object DHRETIRADA = null;
    private Object OBSERVACAO = null;
    private Object CDMATERIALSERVICO = null;
    private Object CDCADASTROMATERIAL = null;
    private Object SGUSER = null;
    private Object OBJECTVERSION = null;
    private Object SERVERORIG = null;
    private Object DHCTRLREPLIC = null;
    private Object IDSINCRINIZA = null;

    public EQUIPAMENTOCOMPONENTE(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
    }

    public Object getIDSINCRINIZA() {
        return IDSINCRINIZA;
    }

    public void setIDSINCRINIZA(Object IDSINCRINIZA) {
        this.IDSINCRINIZA = IDSINCRINIZA;
    }

    public Object getIDEQUIPAMENTO() {
        return IDEQUIPAMENTO;
    }

    public void setIDEQUIPAMENTO(Object IDEQUIPAMENTO) {
        this.IDEQUIPAMENTO = IDEQUIPAMENTO;
    }

    public Object getSEQUENCIA() {
        return SEQUENCIA;
    }

    public void setSEQUENCIA(Object SEQUENCIA) {
        this.SEQUENCIA = SEQUENCIA;
    }

    public Object getIDCOMPONENTE() {
        return IDCOMPONENTE;
    }

    public void setIDCOMPONENTE(Object IDCOMPONENTE) {
        this.IDCOMPONENTE = IDCOMPONENTE;
    }

    public Object getCDTIPOCOMPONENTE() {
        return CDTIPOCOMPONENTE;
    }

    public void setCDTIPOCOMPONENTE(Object CDTIPOCOMPONENTE) {
        this.CDTIPOCOMPONENTE = CDTIPOCOMPONENTE;
    }

    public Object getQUANTIDADE() {
        return QUANTIDADE;
    }

    public void setQUANTIDADE(Object QUANTIDADE) {
        this.QUANTIDADE = QUANTIDADE;
    }

    public Object getDHCOLOCACAO() {
        return DHCOLOCACAO;
    }

    public void setDHCOLOCACAO(Object DHCOLOCACAO) {
        this.DHCOLOCACAO = DHCOLOCACAO;
    }

    public Object getCONTAGEMUSO() {
        return CONTAGEMUSO;
    }

    public void setCONTAGEMUSO(Object CONTAGEMUSO) {
        this.CONTAGEMUSO = CONTAGEMUSO;
    }

    public Object getDHRETIRADA() {
        return DHRETIRADA;
    }

    public void setDHRETIRADA(Object DHRETIRADA) {
        this.DHRETIRADA = DHRETIRADA;
    }

    public Object getOBSERVACAO() {
        return OBSERVACAO;
    }

    public void setOBSERVACAO(Object OBSERVACAO) {
        this.OBSERVACAO = OBSERVACAO;
    }

    public Object getCDMATERIALSERVICO() {
        return CDMATERIALSERVICO;
    }

    public void setCDMATERIALSERVICO(Object CDMATERIALSERVICO) {
        this.CDMATERIALSERVICO = CDMATERIALSERVICO;
    }

    public Object getCDCADASTROMATERIAL() {
        return CDCADASTROMATERIAL;
    }

    public void setCDCADASTROMATERIAL(Object CDCADASTROMATERIAL) {
        this.CDCADASTROMATERIAL = CDCADASTROMATERIAL;
    }

    public Object getSGUSER() {
        return SGUSER;
    }

    public void setSGUSER(Object SGUSER) {
        this.SGUSER = SGUSER;
    }

    public Object getOBJECTVERSION() {
        return OBJECTVERSION;
    }

    public void setOBJECTVERSION(Object OBJECTVERSION) {
        this.OBJECTVERSION = OBJECTVERSION;
    }

    public Object getSERVERORIG() {
        return SERVERORIG;
    }

    public void setSERVERORIG(Object SERVERORIG) {
        this.SERVERORIG = SERVERORIG;
    }

    public Object getDHCTRLREPLIC() {
        return DHCTRLREPLIC;
    }

    public void setDHCTRLREPLIC(Object DHCTRLREPLIC) {
        this.DHCTRLREPLIC = DHCTRLREPLIC;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = " CREATE TABLE " + TABLE + " ( "
                    + "ID" + " integer primary key autoincrement, "
                    + "IDEQUIPAMENTO" + " text, "
                    + "SEQUENCIA" + " text, "
                    + "IDCOMPONENTE" + " text, "
                    + "CDTIPOCOMPONENTE" + " text, "
                    + "QUANTIDADE" + " text, "
                    + "DHCOLOCACAO" + " text, "
                    + "CONTAGEMUSO" + " text, "
                    + "DHRETIRADA" + " text, "
                    + "OBSERVACAO" + " text, "
                    + "CDMATERIALSERVICO" + " text, "
                    + "CDCADASTROMATERIAL" + " text, "
                    + "SGUSER" + " text, "
                    + "DHCTRLREPLIC" + " text, "
                    + "IDSINCRINIZA" + " text "
                    + " ) ";

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
