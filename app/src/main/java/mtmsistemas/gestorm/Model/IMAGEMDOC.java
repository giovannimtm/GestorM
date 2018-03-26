package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 19/03/2018.
 */

public class IMAGEMDOC extends SQLiteOpenHelper {
    public static String TABLE = "IMAGEMDOC";
    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE + "/delete";

    private Object SGDOCORIGEM = null;
    private Object IDDOCORIGEM = null;
    private Object IDITEMDOCORIGEM = null;
    private Object SEQUENCIA = null;
    private Object TIPOARQUIVO = null;
    private Object IMAGEM = null;
    private Object DSIMAGEM = null;
    private Object EXTENSAOARQUIVO = null;
    private Object IDSINCRINIZA = null;

    public Object getIDSINCRINIZA() {
        return IDSINCRINIZA;
    }

    public void setIDSINCRINIZA(Object IDSINCRINIZA) {
        this.IDSINCRINIZA = IDSINCRINIZA;
    }

    private Context PCON_context;

    public Object getSGDOCORIGEM() {
        return SGDOCORIGEM;
    }

    public void setSGDOCORIGEM(Object SGDOCORIGEM) {
        this.SGDOCORIGEM = SGDOCORIGEM;
    }

    public Object getIDDOCORIGEM() {
        return IDDOCORIGEM;
    }

    public void setIDDOCORIGEM(Object IDDOCORIGEM) {
        this.IDDOCORIGEM = IDDOCORIGEM;
    }

    public Object getIDITEMDOCORIGEM() {
        return IDITEMDOCORIGEM;
    }

    public void setIDITEMDOCORIGEM(Object IDITEMDOCORIGEM) {
        this.IDITEMDOCORIGEM = IDITEMDOCORIGEM;
    }

    public Object getSEQUENCIA() {
        return SEQUENCIA;
    }

    public void setSEQUENCIA(Object SEQUENCIA) {
        this.SEQUENCIA = SEQUENCIA;
    }

    public Object getTIPOARQUIVO() {
        return TIPOARQUIVO;
    }

    public void setTIPOARQUIVO(Object TIPOARQUIVO) {
        this.TIPOARQUIVO = TIPOARQUIVO;
    }

    public Object getIMAGEM() {
        return IMAGEM;
    }

    public void setIMAGEM(Object IMAGEM) {
        this.IMAGEM = IMAGEM;
    }

    public Object getDSIMAGEM() {
        return DSIMAGEM;
    }

    public void setDSIMAGEM(Object DSIMAGEM) {
        this.DSIMAGEM = DSIMAGEM;
    }

    public Object getEXTENSAOARQUIVO() {
        return EXTENSAOARQUIVO;
    }

    public void setEXTENSAOARQUIVO(Object EXTENSAOARQUIVO) {
        this.EXTENSAOARQUIVO = EXTENSAOARQUIVO;
    }


    public IMAGEMDOC(Context LCON_Context) {
        super(LCON_Context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
        this.PCON_context = LCON_Context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = " CREATE TABLE " + TABLE + " ( "
                    + "ID" + " integer primary key autoincrement, "
                    + "IDDOCORIGEM" + " text, "
                    + "SGDOCORIGEM" + " text, "
                    + "IDITEMDOCORIGEM" + " text, "
                    + "SEQUENCIA" + " text, "
                    + "TIPOARQUIVO" + " text, "
                    + "IMAGEM" + " text, "
                    + "DSIMAGEM" + " text, "
                    + "EXTENSAOARQUIVO" + " text, "
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
