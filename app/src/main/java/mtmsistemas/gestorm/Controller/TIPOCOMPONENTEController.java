package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.TIPOCOMPONENTE;

/**
 * Created by Giovanni on 28/02/2018.
 */

public class TIPOCOMPONENTEController {

    private SQLiteDatabase db;
    private TIPOCOMPONENTE TIPOCOMPONENTEModel;

    public static Context contexts;

    public TIPOCOMPONENTEController(Context context) {
        TIPOCOMPONENTEModel = new TIPOCOMPONENTE(contexts);
    }

    public TIPOCOMPONENTE[] FU_BuscaDescricao_WB(Object CDTIPOCOMPONENTE) {
        TIPOCOMPONENTE[] LCLS_TPCOMPONENTES = null;
        TIPOCOMPONENTE LCLS_TPCOMPONENTE = null;
        Object LOBJ_Retorno = null;
        int LINT_CDTIPOCOMPONENTE;
        Gson LGS_JSON = null;
        try {
            LCLS_TPCOMPONENTE = new TIPOCOMPONENTE(null);
            if (CDTIPOCOMPONENTE != null) {
                LINT_CDTIPOCOMPONENTE = (Integer) CDTIPOCOMPONENTE;

                        LGS_JSON = new Gson();
                LOBJ_Retorno = LGS_JSON.fromJson(ConexaoWebAPI.FU_WB_ARROBJECT(
                        LCLS_TPCOMPONENTE
                        , TIPOCOMPONENTE.READ_WB
                        , LINT_CDTIPOCOMPONENTE,"").toString(), TIPOCOMPONENTE[].class);
                LCLS_TPCOMPONENTES = (TIPOCOMPONENTE[]) LOBJ_Retorno;
            } else {
                return null;
            }
            return LCLS_TPCOMPONENTES;

        } catch (Exception ex) {
            //return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_TPCOMPONENTE = null;
            LOBJ_Retorno = null;
        }
        return LCLS_TPCOMPONENTES;
    }

    //CRUD WB
    public String FU_Insert_WB(TIPOCOMPONENTE CLS_TIPOCOMPONENTE) {
        TIPOCOMPONENTE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_TIPOCOMPONENTE != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, TIPOCOMPONENTE.INSERT_WB, 0);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_TPCOMPONENTE = null;
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public List<TIPOCOMPONENTE> FU_Read_WB(TIPOCOMPONENTE CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        Gson LGS_JSON = null;
        TIPOCOMPONENTE[] LCLS_TIPOCOMPONENTE = null;
        String LOBJ_Retorno = null;

        try {

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
                    CLS_TIPOCOMPONENTE,
                    TIPOCOMPONENTE.READ_WB, INT_IDTIPOCOMPNENTE,"").toString();
            LGS_JSON = new Gson();
            LCLS_TIPOCOMPONENTE = LGS_JSON.fromJson(LOBJ_Retorno.toString()
                    , TIPOCOMPONENTE[].class);

            if (LCLS_TIPOCOMPONENTE != null) {

            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {

        }
        return Arrays.asList(LCLS_TIPOCOMPONENTE);
    }

    public String FU_Update_WB(TIPOCOMPONENTE CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        TIPOCOMPONENTE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, TIPOCOMPONENTE.UPDATE_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_TPCOMPONENTE = null;
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Delete_WB(TIPOCOMPONENTE CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        TIPOCOMPONENTE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, TIPOCOMPONENTE.DELETE_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_TPCOMPONENTE = null;
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    //CRUD BD
    public String FU_Insert_BD(TIPOCOMPONENTE CLS_TIPOCOMPONENTE) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = TIPOCOMPONENTEModel.getWritableDatabase();
            TIPOCOMPONENTEModel.onCreate(db);
            LCVA_VALUES = new ContentValues();
//            LCVA_VALUES.put(EMFSESSION., EMFSESSION.LOCAL_IDSESSION);

            LCVA_VALUES.put("CDTIPOCOMPONENTE", CLS_TIPOCOMPONENTE.getCDTIPOCOMPONENTE().toString());
            LCVA_VALUES.put("DSTIPOCOMPONENTE", CLS_TIPOCOMPONENTE.getDSTIPOCOMPONENTE().toString());
            LCVA_VALUES.put("FGACTIVE", CLS_TIPOCOMPONENTE.getFGACTIVE().toString());
            LCVA_VALUES.put("FGEXIGEID", CLS_TIPOCOMPONENTE.getFGEXIGEID().toString());
            LCVA_VALUES.put("FGEXIGEMATERIAL", CLS_TIPOCOMPONENTE.getFGEXIGEMATERIAL().toString());
            LCVA_VALUES.put("CDGRUPOCOMPONENTE", CLS_TIPOCOMPONENTE.getCDGRUPOCOMPONENTE().toString());

            LINT_RETURN = db.insert(TIPOCOMPONENTE.TABLE, null, LCVA_VALUES);
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
            db = TIPOCOMPONENTEModel.getReadableDatabase();
            LCUR_CURSOR = db.query(TIPOCOMPONENTE.TABLE, campos, null, null, null, null, null, null);

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
            db = TIPOCOMPONENTEModel.getReadableDatabase();
            LCUR_CURSOR = db.query(TIPOCOMPONENTE.TABLE, campos, where, null, null, null, null, null);

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

    public void FU_Update_BD(int id, TIPOCOMPONENTE CLS_TIPOCOMPONENTE) {
        ContentValues LCVA_VALUES;
        String where = "";
        try {
            db = TIPOCOMPONENTEModel.getWritableDatabase();

            where = "IDSESSION" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("CDTIPOCOMPONENTE", CLS_TIPOCOMPONENTE.getCDTIPOCOMPONENTE().toString());
            LCVA_VALUES.put("DSTIPOCOMPONENTE", CLS_TIPOCOMPONENTE.getDSTIPOCOMPONENTE().toString());
            LCVA_VALUES.put("FGACTIVE", CLS_TIPOCOMPONENTE.getFGACTIVE().toString());
            LCVA_VALUES.put("FGEXIGEID", CLS_TIPOCOMPONENTE.getFGEXIGEID().toString());
            LCVA_VALUES.put("FGEXIGEMATERIAL", CLS_TIPOCOMPONENTE.getFGEXIGEMATERIAL().toString());
            LCVA_VALUES.put("CDGRUPOCOMPONENTE", CLS_TIPOCOMPONENTE.getCDGRUPOCOMPONENTE().toString());

            db.update(TIPOCOMPONENTE.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void FU_Delete_BD(int id) {
        try {
            String where = "IDSESSION" + "=" + id;
            db = TIPOCOMPONENTEModel.getReadableDatabase();
            db.delete(TIPOCOMPONENTE.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}