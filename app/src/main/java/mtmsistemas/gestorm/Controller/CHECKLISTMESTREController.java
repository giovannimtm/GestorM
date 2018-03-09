package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mtmsistemas.gestorm.Model.CHECKLISTMESTRE;

/**
 * Created by Giovanni on 09/03/2018.
 */

public class CHECKLISTMESTREController {


    private SQLiteDatabase db;
    private CHECKLISTMESTRE CHECKLISTMESTREController;

    public static Context contexts;

    public CHECKLISTMESTREController(Context context) {
        CHECKLISTMESTREController = new CHECKLISTMESTRE(contexts);
    }

    //CRUD WB
    public String FU_Insert_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE) {
        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_CHECKLISTMESTRE != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE , CHECKLISTMESTRE.INSERT_WB, 0);
            } else {return new String("Não pode enviar classe Null");}
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

    public String FU_Read_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE , int INT_IDCHECKLISTMESTRE) {
        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTMESTRE != null || INT_IDCHECKLISTMESTRE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE , CHECKLISTMESTRE.READ_WB,INT_IDCHECKLISTMESTRE );
            } else {return new String("Não pode enviar classe Null");}
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

    public String FU_Update_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE , int INT_IDCHECKLISTMESTRE) {
        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTMESTRE != null || INT_IDCHECKLISTMESTRE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE , CHECKLISTMESTRE.UPDATE_WB,INT_IDCHECKLISTMESTRE );
            } else {return new String("Não pode enviar classe Null");}
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

    public String FU_Delete_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE , int INT_IDCHECKLISTMESTRE) {
        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTMESTRE != null || INT_IDCHECKLISTMESTRE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE , CHECKLISTMESTRE.DELETE_WB,INT_IDCHECKLISTMESTRE );
            } else {return new String("Não pode enviar classe Null");}
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
    public String FU_Insert_BD(CHECKLISTMESTRE CLS_CHECKLISTMESTRE) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = CHECKLISTMESTREController.getWritableDatabase();
            CHECKLISTMESTREController.onCreate(db);
            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("IDEQUIPAMENTO", CLS_CHECKLISTMESTRE.getIDEQUIPAMENTO().toString());
            LCVA_VALUES.put("CDTIPOCHECKLIST", CLS_CHECKLISTMESTRE.getCDTIPOCHECKLIST().toString());
            LCVA_VALUES.put("CDCADASTRORESP", CLS_CHECKLISTMESTRE.getCDCADASTRORESP().toString());
            LCVA_VALUES.put("CONTAGEMUSO", CLS_CHECKLISTMESTRE.getCONTAGEMUSO().toString());
            LCVA_VALUES.put("DTREALIZADO", CLS_CHECKLISTMESTRE.getDTREALIZADO().toString());
            LCVA_VALUES.put("LOCALREALIZADO", CLS_CHECKLISTMESTRE.getLOCALREALIZADO().toString());
            LCVA_VALUES.put("FGSITUACAO", CLS_CHECKLISTMESTRE.getFGSITUACAO().toString());
            LCVA_VALUES.put("OBSERVACAO", CLS_CHECKLISTMESTRE.getOBSERVACAO().toString());
            LCVA_VALUES.put("IDORDEMSERVICO", CLS_CHECKLISTMESTRE.getIDORDEMSERVICO().toString());
            LCVA_VALUES.put("CDCENTRORESULTADO", CLS_CHECKLISTMESTRE.getCDCENTRORESULTADO().toString());
            LCVA_VALUES.put("IMAGEMCOMPONENTE", CLS_CHECKLISTMESTRE.getIMAGEMCOMPONENTE().toString());

            LINT_RETURN = db.insert(CHECKLISTMESTRE.TABLE, null, LCVA_VALUES);
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
            db = CHECKLISTMESTREController.getReadableDatabase();
            LCUR_CURSOR = db.query(CHECKLISTMESTRE.TABLE, campos, null, null, null, null, null, null);

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
            db = CHECKLISTMESTREController.getReadableDatabase();
            LCUR_CURSOR = db.query(CHECKLISTMESTRE.TABLE, campos, where, null, null, null, null, null);

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

    public void FU_Update_BD(int id, CHECKLISTMESTRE CLS_CHECKLISTMESTRE) {
        ContentValues LCVA_VALUES;
        String where = "";
        try {
            db = CHECKLISTMESTREController.getWritableDatabase();

            where = "IDSESSION" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("IDEQUIPAMENTO", CLS_CHECKLISTMESTRE.getIDEQUIPAMENTO().toString());
            LCVA_VALUES.put("CDTIPOCHECKLIST", CLS_CHECKLISTMESTRE.getCDTIPOCHECKLIST().toString());
            LCVA_VALUES.put("CDCADASTRORESP", CLS_CHECKLISTMESTRE.getCDCADASTRORESP().toString());
            LCVA_VALUES.put("CONTAGEMUSO", CLS_CHECKLISTMESTRE.getCONTAGEMUSO().toString());
            LCVA_VALUES.put("DTREALIZADO", CLS_CHECKLISTMESTRE.getDTREALIZADO().toString());
            LCVA_VALUES.put("LOCALREALIZADO", CLS_CHECKLISTMESTRE.getLOCALREALIZADO().toString());
            LCVA_VALUES.put("FGSITUACAO", CLS_CHECKLISTMESTRE.getFGSITUACAO().toString());
            LCVA_VALUES.put("OBSERVACAO", CLS_CHECKLISTMESTRE.getOBSERVACAO().toString());
            LCVA_VALUES.put("IDORDEMSERVICO", CLS_CHECKLISTMESTRE.getIDORDEMSERVICO().toString());
            LCVA_VALUES.put("CDCENTRORESULTADO", CLS_CHECKLISTMESTRE.getCDCENTRORESULTADO().toString());
            LCVA_VALUES.put("IMAGEMCOMPONENTE", CLS_CHECKLISTMESTRE.getIMAGEMCOMPONENTE().toString());

            db.update(CHECKLISTMESTRE.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void FU_Delete_BD(int id) {
        try {
            String where = "IDSESSION" + "=" + id;
            db = CHECKLISTMESTREController.getReadableDatabase();
            db.delete(CHECKLISTMESTRE.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
