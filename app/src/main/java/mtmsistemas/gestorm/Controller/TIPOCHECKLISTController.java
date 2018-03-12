package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.TIPOCHECKLIST;

/**
 * Created by Giovanni on 08/03/2018.
 */

public class TIPOCHECKLISTController {

    private SQLiteDatabase db;
    private TIPOCHECKLIST TIPOCHECKLISTModel;

    public static Context contexts;

    public TIPOCHECKLISTController(Context context) {
        TIPOCHECKLISTModel = new TIPOCHECKLIST(contexts);
    }

    //CRUD WB
    public String FU_Insert_WB(TIPOCHECKLIST CLS_TPCHECKLIST) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_TPCHECKLIST != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TPCHECKLIST, TIPOCHECKLIST.INSERT_WB, 0);
            } else {return new String("Não pode enviar classe Null");}
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            CLS_TPCHECKLIST = null;
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public List<TIPOCHECKLIST> FU_Read_WB(TIPOCHECKLIST CLS_TPCHECKLIST , int INT_IDTIPOCOMPNENTE) {
        Gson LGS_JSON = null;
        TIPOCHECKLIST[] LCLS_TIPOCHECKLIST = null;
        String LOBJ_Retorno = null;

        try {

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
                    CLS_TPCHECKLIST,
                    TIPOCHECKLIST.READ_WB,INT_IDTIPOCOMPNENTE).toString();
            LGS_JSON = new Gson();
            LCLS_TIPOCHECKLIST = LGS_JSON.fromJson(LOBJ_Retorno.toString()
                    , TIPOCHECKLIST[].class);

            if (LCLS_TIPOCHECKLIST != null) {

            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {

        }
        return Arrays.asList(LCLS_TIPOCHECKLIST);
    }

    public String FU_Update_WB(TIPOCHECKLIST  CLS_TIPOCHECKLIST , int INT_IDTIPOCOMPNENTE) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCHECKLIST != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCHECKLIST , TIPOCHECKLIST.UPDATE_WB,INT_IDTIPOCOMPNENTE );
            } else {return new String("Não pode enviar classe Null");}
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
    } finally {
        LCLS_UTIL = null;
        LOBJ_Retorno = null;
    }
    }

    public String FU_Delete_WB(TIPOCHECKLIST CLS_TIPOCHECKLIST , int INT_IDTIPOCOMPNENTE) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCHECKLIST != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCHECKLIST , TIPOCHECKLIST.DELETE_WB,INT_IDTIPOCOMPNENTE );
            } else {return new String("Não pode enviar classe Null");}
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    //CRUD BD
    public String FU_Insert_BD(TIPOCHECKLIST CLS_TIPOCHECKLIST) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = TIPOCHECKLISTModel.getWritableDatabase();
            TIPOCHECKLISTModel.onCreate(db);
            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("CDTIPOCHECKLIST", CLS_TIPOCHECKLIST.getCDTIPOCHECKLIST().toString());
            LCVA_VALUES.put("DSTIPOCHECKLIST", CLS_TIPOCHECKLIST.getDSTIPOCHECKLIST().toString());
            LCVA_VALUES.put("SGUSER", CLS_TIPOCHECKLIST.getSGUSER().toString());

            LINT_RETURN = db.insert(TIPOCHECKLIST.TABLE, null, LCVA_VALUES);
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

    public Cursor FU_Read_BD() {
        Cursor LCUR_CURSOR = null;
//        String[] campos = {String.valueOf(EMFSESSION.LOCAL_IDSESSION)};
        String[] campos = {"*"};
        try {
            db = TIPOCHECKLISTModel.getReadableDatabase();
            LCUR_CURSOR = db.query(TIPOCHECKLIST.TABLE, campos, null, null, null, null, null, null);

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

    public Cursor FU_Read_ID_BD(int id) {
        Cursor LCUR_CURSOR = null;
        try {
            String[] campos = {"*"};
            String where = "IDSESSION" + "=" + id;
            db = TIPOCHECKLISTModel.getReadableDatabase();
            LCUR_CURSOR = db.query(TIPOCHECKLIST.TABLE, campos, where, null, null, null, null, null);

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

    public void FU_Update_BD(int id, TIPOCHECKLIST CLS_TIPOCHECKLIST) {
        ContentValues LCVA_VALUES;
        String where = "";
        try {
            db = TIPOCHECKLISTModel.getWritableDatabase();

            where = "IDSESSION" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("CDTIPOCHECKLIST", CLS_TIPOCHECKLIST.getCDTIPOCHECKLIST().toString());
            LCVA_VALUES.put("DSTIPOCHECKLIST", CLS_TIPOCHECKLIST.getDSTIPOCHECKLIST().toString());
            LCVA_VALUES.put("SGUSER", CLS_TIPOCHECKLIST.getSGUSER().toString());

            db.update(TIPOCHECKLIST.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void FU_Delete_BD(int id) {
        try {
            String where = "IDSESSION" + "=" + id;
            db = TIPOCHECKLISTModel.getReadableDatabase();
            db.delete(TIPOCHECKLIST.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
