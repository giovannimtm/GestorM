package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 28/02/2018.
 */

public class TIPOCOMPONENTE extends SQLiteOpenHelper {
    public static final String TABLE = "TIPOCOMPONENTE";

    private Object CDTIPOCOMPONENTE = null;
    private Object DSTIPOCOMPONENTE = null;
    private Object FGACTIVE = null;
    private Object FGEXIGEID = null;
    private Object FGEXIGEMATERIAL = null;
    private Object CDGRUPOCOMPONENTE = null;
    public String IMAGEMCOMPONENTE = null;
    private Context context;


    public TIPOCOMPONENTE(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
        this.context = context;
    }


    public Object getCDTIPOCOMPONENTE() {
        return CDTIPOCOMPONENTE;
    }

    public void setCDTIPOCOMPONENTE(Object CDTIPOCOMPONENTE) {
        this.CDTIPOCOMPONENTE = CDTIPOCOMPONENTE;
    }

    public Object getDSTIPOCOMPONENTE() {
        return DSTIPOCOMPONENTE;
    }

    public void setDSTIPOCOMPONENTE(Object DSTIPOCOMPONENTE) {
        this.DSTIPOCOMPONENTE = DSTIPOCOMPONENTE;
    }

    public Object getFGACTIVE() {
        return FGACTIVE;
    }

    public void setFGACTIVE(Object FGACTIVE) {
        this.FGACTIVE = FGACTIVE;
    }

    public Object getFGEXIGEID() {
        return FGEXIGEID;
    }

    public void setFGEXIGEID(Object FGEXIGEID) {
        this.FGEXIGEID = FGEXIGEID;
    }

    public Object getFGEXIGEMATERIAL() {
        return FGEXIGEMATERIAL;
    }

    public void setFGEXIGEMATERIAL(Object FGEXIGEMATERIAL) {
        this.FGEXIGEMATERIAL = FGEXIGEMATERIAL;
    }

    public Object getCDGRUPOCOMPONENTE() {
        return CDGRUPOCOMPONENTE;
    }

    public void setCDGRUPOCOMPONENTE(Object CDGRUPOCOMPONENTE) {
        this.CDGRUPOCOMPONENTE = CDGRUPOCOMPONENTE;
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
