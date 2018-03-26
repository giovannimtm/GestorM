package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by Giovanni on 21/03/2018.
 */

public class SINCRONIZAITEM   extends SQLiteOpenHelper {
    public static String TABLE = "SINCRONIZAITEM";

    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE ;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE+"/delete";

    private int IDSINCRINIZA;
    private int SEQSINCRINIZA;
    private String SGOPERACAO;
    private String IDOPERACAO;
    private String ITEMOPERACAO;
    private String SEQITEMOPERACAO;
    private String FGSINCRINIZA;
    private String FGDELETATABELA;
    private Date DHMANUTENANCE;
    private String SGUSER;


    public SINCRONIZAITEM(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
        String sql = "CREATE TABLE " + TABLE + "("
                + "ID "+ " integer primary key autoincrement,"
                + "IDSINCRINIZA " + " text,"
                + "SEQSINCRINIZA " + " text,"
                + "SGOPERACAO " + " text,"
                + "IDOPERACAO " + " text,"
                + "ITEMOPERACAO " + " text,"
                + "SEQITEMOPERACAO " + " text,"
                + "FGSINCRINIZA " + " text,"
                + "DHMANUTENANCE " + " text,"
                + "SGUSER " + " text"
                + ")";
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            //NotificationCompat.MessagingStyle.Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
