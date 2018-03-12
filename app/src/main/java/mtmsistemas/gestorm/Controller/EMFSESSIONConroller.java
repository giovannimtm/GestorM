package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mtmsistemas.gestorm.Model.EMFSESSION;

/**
 * Created by Giovanni on 30/01/2018.
 */

public class EMFSESSIONConroller {
    private SQLiteDatabase db;
    private EMFSESSION EMFSESSIONModel;

    public static Context contexts;

    public EMFSESSIONConroller(Context context) {
        EMFSESSIONModel = new EMFSESSION(contexts);
//        myhelper = new DATABASE(context);
    }

    public String Insert(EMFSESSION CLS_EMFSESSION) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = EMFSESSIONModel.getWritableDatabase();
            EMFSESSIONModel.onCreate(db);
            LCVA_VALUES = new ContentValues();
//            LCVA_VALUES.put(EMFSESSION., EMFSESSION.LOCAL_IDSESSION);

            LCVA_VALUES.put("SGUSER", CLS_EMFSESSION.getSGUSER());
            LCVA_VALUES.put("PASSWORD", CLS_EMFSESSION.getPASSWORD());
            LCVA_VALUES.put("SGENVIRONMENT", CLS_EMFSESSION.getSGENVIRONMENT());
            LCVA_VALUES.put("SGLANGUAGE", CLS_EMFSESSION.getSGLANGUAGE());
            LCVA_VALUES.put("EQUIPMENT", CLS_EMFSESSION.getEQUIPMENT());

            LINT_RETURN = db.insert(EMFSESSION.TABLE, null, LCVA_VALUES);
            db.close();

            if (LINT_RETURN == -1)
                return "Erro ao inserir registro";
            else
                return "Registro Inserido com sucesso";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return "";
    }

    public Cursor ReturnData() {
        Cursor LCUR_CURSOR = null;
//        String[] campos = {String.valueOf(EMFSESSION.LOCAL_IDSESSION)};
        String[] campos = {"*"};
        try {
            db = EMFSESSIONModel.getReadableDatabase();
            LCUR_CURSOR = db.query(EMFSESSION.TABLE, campos, null, null, null, null, null, null);

            if (LCUR_CURSOR != null) {
                LCUR_CURSOR.moveToFirst();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return LCUR_CURSOR;
    }

    public Cursor ReturnDataById(int id) {
        Cursor LCUR_CURSOR = null;
        try {
            String[] campos = {"*"};
            String where = "IDSESSION" + "=" + id;
            db = EMFSESSIONModel.getReadableDatabase();
            LCUR_CURSOR = db.query(EMFSESSION.TABLE, campos, where, null, null, null, null, null);

            if (LCUR_CURSOR != null) {
                LCUR_CURSOR.moveToFirst();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return LCUR_CURSOR;
    }

    public void Update(int id, EMFSESSION CLS_EMFSESSION) {
        ContentValues LCVA_VALUES;
        String where = "";
        try {
            db = EMFSESSIONModel.getWritableDatabase();

            where = "IDSESSION" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("SGUSER", CLS_EMFSESSION.getSGUSER());
            LCVA_VALUES.put("PASSWORD", CLS_EMFSESSION.getPASSWORD());
            LCVA_VALUES.put("SGENVIRONMENT", CLS_EMFSESSION.getSGENVIRONMENT());
            LCVA_VALUES.put("SGLANGUAGE", CLS_EMFSESSION.getSGLANGUAGE());
            LCVA_VALUES.put("EQUIPMENT", CLS_EMFSESSION.getEQUIPMENT());

            db.update(EMFSESSION.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void Delete(int id) {
        try {
            String where = "IDSESSION" + "=" + id;
            db = EMFSESSIONModel.getReadableDatabase();
            db.delete(EMFSESSION.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
