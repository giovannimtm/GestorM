package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

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

    public TIPOCOMPONENTE FU_WB_BuscaDescricao(Object CDTIPOCOMPONENTE) {
        TIPOCOMPONENTE LCLS_TPCOMPONENTE = null;
        Object LOBJ_Retorno = null;
        try {
            LCLS_TPCOMPONENTE = new TIPOCOMPONENTE(null);
            if (CDTIPOCOMPONENTE != null) {

                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA(LCLS_TPCOMPONENTE, "tipocomponente", "GET",1);
                LCLS_TPCOMPONENTE =(TIPOCOMPONENTE) LOBJ_Retorno;

            } else {
                return null;
            }
            return LCLS_TPCOMPONENTE;

        } catch (Exception ex) {
            //return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_TPCOMPONENTE = null;
            LOBJ_Retorno = null;
        }
        return LCLS_TPCOMPONENTE;
    }

    public String FU_Insert_WB(TIPOCOMPONENTE CLS_TIPOCOMPONENTE) {
        TIPOCOMPONENTE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            LCLS_TPCOMPONENTE = new TIPOCOMPONENTE(null);
            LCLS_UTIL = new ClsUtil();

            if (CLS_TIPOCOMPONENTE != null) {
                LCLS_TPCOMPONENTE.setIMAGEMCOMPONENTE(LCLS_UTIL.FU_converteFotoParaArrayBytes(
                        Environment.getExternalStorageDirectory() + "/foto.jpg"));
                //LCLS_TPCOMPONENTE.setIMAGEMCOMPONENTE(
                //LCLS_UTIL.FU_converteArquivoParaArrayBytes(
                //Environment.getExternalStorageDirectory()+"/MOV_0002.mp4")
                //);

                LCLS_UTIL = null;
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA(LCLS_TPCOMPONENTE, "tipocomponente/insere", "POST",0);
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

    public String Insert(TIPOCOMPONENTE CLS_TIPOCOMPONENTE) {
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

    public Cursor ReturnData() {
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

    public Cursor ReturnDataById(int id) {
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

    public void Update(int id, TIPOCOMPONENTE CLS_TIPOCOMPONENTE) {
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

    public void Delete(int id) {
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