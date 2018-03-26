package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CENTRORESULTADO extends SQLiteOpenHelper {

    public static String TABLE = "CENTRORESULTADO";
    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE ;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE + "/delete";
    private Object CDCENTRORESULTADO = null;
    private Object FGACTIVE = null;
    private Object CDCENTRORESULTADON1 = null;
    private Object CDCENTRORESULTADON2 = null;
    private Object CDCENTRORESULTADON3 = null;
    private Object CDCENTRORESULTADON4 = null;
    private Object FGHABILITALANCTO = null;
    private Object DSCENTRORESULTADO = null;
    private Object IDSINCRINIZA = null;

    public Object getIDSINCRINIZA() {
        return IDSINCRINIZA;
    }

    public void setIDSINCRINIZA(Object IDSINCRINIZA) {
        this.IDSINCRINIZA = IDSINCRINIZA;
    }

    public CENTRORESULTADO(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE + "("
                + "CDCENTRORESULTADO" + " integer primary key autoincrement,"
                + "FGACTIVE" + " text,"
                + "CDCENTRORESULTADON1" + " text,"
                + "CDCENTRORESULTADON2" + " text,"
                + "CDCENTRORESULTADON3" + " text,"
                + "CDCENTRORESULTADON4" + " text,"
                + "FGHABILITALANCTO" + " text,"
                + "IDSINCRINIZA" + " text,"
                + "DSCENTRORESULTADO" + " text"
                + ")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}

