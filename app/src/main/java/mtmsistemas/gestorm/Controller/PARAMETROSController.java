package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mtmsistemas.gestorm.Model.EMFSESSION;
import mtmsistemas.gestorm.Model.PARAMETROS;


/**
 * Created by Giovanni on 09/03/2018.
 */

public class PARAMETROSController {


    private SQLiteDatabase db;
    private PARAMETROS PARAMETROSModel;

    public static Context contexts;

    public PARAMETROSController(Context context) {
        PARAMETROSModel = new PARAMETROS(contexts);

    }

    //CRUD WB
    public String FU_Insert_WB(PARAMETROS CLS_PARAMETROS) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_PARAMETROS != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_PARAMETROS, PARAMETROS.INSERT_WB, 0);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Read_WB(PARAMETROS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETROS.READ_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Update_WB(PARAMETROS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETROS.UPDATE_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Delete_WB(PARAMETROS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETROS.DELETE_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
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
    public String FU_Insert_BD(PARAMETROS CLS_PARAMETROS) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = PARAMETROSModel.getWritableDatabase();
            PARAMETROSModel.onUpgrade(db,0,1);
            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("ENDERECOWEBAPI", PARAMETROS.getPstrEnderecowebapi().toString());
            LCVA_VALUES.put("NMUSUARIO", EMFSESSION.LOCAL_NMUSUARIO);
            LINT_RETURN = db.insert(PARAMETROS.TABLE, null, LCVA_VALUES);
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
            db = PARAMETROSModel.getReadableDatabase();
            LCUR_CURSOR = db.query(PARAMETROS.TABLE, campos, null, null, null, null, null, null);

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
            String where = "IDPARAMETRO" + "=" + id;
            db = PARAMETROSModel.getReadableDatabase();
            LCUR_CURSOR = db.query(PARAMETROS.TABLE, campos, where, null, null, null, null, null);

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

    public void FU_Update_BD(int id) {
        ContentValues LCVA_VALUES;
        String where = "";
        long LINT_RETURN = 0;
        try {
            db = PARAMETROSModel.getWritableDatabase();

            if (id > 0)
                where = "IDPARAMETRO" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("ENDERECOWEBAPI", PARAMETROS.getPstrEnderecowebapi().toString());
            LCVA_VALUES.put("NMUSUARIO", EMFSESSION.LOCAL_NMUSUARIO.toUpperCase());
            LINT_RETURN = db.update(PARAMETROS.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void FU_Delete_BD(int id) {
        String where = "";
        int LINT_RETURN;
        try {
            if (id > 0)
                where = "IDPARAMETRO" + "=" + id;

            db = PARAMETROSModel.getReadableDatabase();
            LINT_RETURN = db.delete(PARAMETROS.TABLE, where, null);
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
