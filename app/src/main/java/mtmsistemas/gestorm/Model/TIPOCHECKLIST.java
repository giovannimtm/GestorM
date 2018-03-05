package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class TIPOCHECKLIST extends SQLiteOpenHelper {
    public static String TABLE = "TIPOCHECKLIST";
    private Object CDTIPOCHECKLIST = null;
    private Object DSTIPOCHECKLIST = null;
    private Object SGUSER = null;

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
