package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class TIPOCHECKLIST extends SQLiteOpenHelper {
    public static String TABLE = "TIPOCHECKLIST";

    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE ;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE+"/delete";

    private Object CDTIPOCHECKLIST = null;
    private Object DSTIPOCHECKLIST = null;
    private Object SGUSER = null;


    public Object getCDTIPOCHECKLIST() {
        return CDTIPOCHECKLIST;
    }

    public void setCDTIPOCHECKLIST(Object CDTIPOCHECKLIST) {
        this.CDTIPOCHECKLIST = CDTIPOCHECKLIST;
    }

    public Object getDSTIPOCHECKLIST() {
        return DSTIPOCHECKLIST;
    }

    public void setDSTIPOCHECKLIST(Object DSTIPOCHECKLIST) {
        this.DSTIPOCHECKLIST = DSTIPOCHECKLIST;
    }

    public Object getSGUSER() {
        return SGUSER;
    }

    public void setSGUSER(Object SGUSER) {
        this.SGUSER = SGUSER;
    }

    public TIPOCHECKLIST(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE + "("
                + "CDTIPOCHECKLIST" + " integer primary key autoincrement,"
                + "DSTIPOCHECKLIST" + " text,"
                + "SGUSER" + " text"
                + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
