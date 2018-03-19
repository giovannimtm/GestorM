package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.CHECKLISTITEM;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CHECKLISTITEMController {

//    List<CHECKLISTITEM> itens = new ArrayList<CHECKLISTITEM>();
//
//    public List<CHECKLISTITEM> getItens(){return itens;}
//
//    public CHECKLISTITEM getItem(int posicao){
//        return itens.get(posicao);
//    }


    private SQLiteDatabase db;
    private CHECKLISTITEM CHECKLISTITEMController;
    private TIPOCOMPONENTEController TIPOCOMPONENTEController = new TIPOCOMPONENTEController(null);

    public static Context contexts;

    public CHECKLISTITEMController(Context context) {
        CHECKLISTITEMController = new CHECKLISTITEM(contexts);
    }

    public String FU_Busca_Descricao_Componente(Object CDTIPOCOMPONENTE){
        String descricao = "";
        try{
            descricao = (String) TIPOCOMPONENTEController.FU_BuscaDescricao_WB(CDTIPOCOMPONENTE)[0].getDSTIPOCOMPONENTE();
        }catch (Exception e){
            e.printStackTrace();
        }
        return descricao;
    }

    //CRUD WB
    public String FU_Insert_WB(CHECKLISTITEM CLS_CHECKLISTITEM) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_CHECKLISTITEM != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTITEM , CHECKLISTITEM.INSERT_WB, 0);
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

    public List<CHECKLISTITEM> FU_Read_WB(CHECKLISTITEM CLS_CHECKLISTITEM , int INT_IDCHECKLIST) {
        Gson LGS_JSON = null;
        CHECKLISTITEM[] LCLS_CHECKLISTITEM = null;
        String LOBJ_Retorno = null;

        try {

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
                    null,
                    CHECKLISTITEM.READ_WB,INT_IDCHECKLIST,"").toString();
            LGS_JSON = new Gson();
            LCLS_CHECKLISTITEM = LGS_JSON.fromJson(LOBJ_Retorno.toString()
                    , CHECKLISTITEM[].class);

            if (LCLS_CHECKLISTITEM != null) {
                for (CHECKLISTITEM item : LCLS_CHECKLISTITEM) {
                    item.setDESCRICAOCOMPONENTE(FU_Busca_Descricao_Componente(item.getCDTIPOCOMPONENTE()));
                }
            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {

        }
        return Arrays.asList(LCLS_CHECKLISTITEM);
    }

    public String FU_Update_WB(CHECKLISTITEM CLS_CHECKLISTITEM , int INT_CHECKLISTITEM) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTITEM != null || INT_CHECKLISTITEM > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTITEM , CHECKLISTITEM.UPDATE_WB,INT_CHECKLISTITEM );
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

    public String FU_Delete_WB(CHECKLISTITEM CLS_CHECKLISTITEM , int INT_IDCHECKLISTITEM) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTITEM != null || INT_IDCHECKLISTITEM > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTITEM , CHECKLISTITEM.DELETE_WB,INT_IDCHECKLISTITEM );
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
    public String FU_Insert_BD(CHECKLISTITEM CLS_CHECKLISTITEM) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = CHECKLISTITEMController.getWritableDatabase();
            CHECKLISTITEMController.onCreate(db);
            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("IDCHECKLIST", CLS_CHECKLISTITEM.getIDCHECKLIST().toString());
            LCVA_VALUES.put("CDTIPOCOMPONENTE", CLS_CHECKLISTITEM.getCDTIPOCOMPONENTE().toString());
            LCVA_VALUES.put("FGSITUACAO", CLS_CHECKLISTITEM.getFGSITUACAO().toString());
            LCVA_VALUES.put("PCTAVALIACAO", CLS_CHECKLISTITEM.getPCTAVALIACAO().toString());
            LCVA_VALUES.put("SGUSER", CLS_CHECKLISTITEM.getSGUSER().toString());
            LCVA_VALUES.put("OBSERVACAO", CLS_CHECKLISTITEM.getOBSERVACAO().toString());
            LCVA_VALUES.put("IMAGEMCOMPONENTE", CLS_CHECKLISTITEM.getIMAGEMCOMPONENTE().toString());

            LINT_RETURN = db.insert(CHECKLISTITEM.TABLE, null, LCVA_VALUES);
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
            db = CHECKLISTITEMController.getReadableDatabase();
            LCUR_CURSOR = db.query(CHECKLISTITEM.TABLE, campos, null, null, null, null, null, null);

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
            db = CHECKLISTITEMController.getReadableDatabase();
            LCUR_CURSOR = db.query(CHECKLISTITEM.TABLE, campos, where, null, null, null, null, null);

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

    public void FU_Update_BD(int id, CHECKLISTITEM CLS_CHECKLISTITEM) {
        ContentValues LCVA_VALUES;
        String where = "";
        try {
            db = CHECKLISTITEMController.getWritableDatabase();

            where = "IDSESSION" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("IDCHECKLIST", CLS_CHECKLISTITEM.getIDCHECKLIST().toString());
            LCVA_VALUES.put("CDTIPOCOMPONENTE", CLS_CHECKLISTITEM.getCDTIPOCOMPONENTE().toString());
            LCVA_VALUES.put("FGSITUACAO", CLS_CHECKLISTITEM.getFGSITUACAO().toString());
            LCVA_VALUES.put("PCTAVALIACAO", CLS_CHECKLISTITEM.getPCTAVALIACAO().toString());
            LCVA_VALUES.put("SGUSER", CLS_CHECKLISTITEM.getSGUSER().toString());
            LCVA_VALUES.put("OBSERVACAO", CLS_CHECKLISTITEM.getOBSERVACAO().toString());
            LCVA_VALUES.put("IMAGEMCOMPONENTE", CLS_CHECKLISTITEM.getIMAGEMCOMPONENTE().toString());

            db.update(CHECKLISTITEM.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void FU_Delete_BD(int id) {
        try {
            String where = "IDSESSION" + "=" + id;
            db = CHECKLISTITEMController.getReadableDatabase();
            db.delete(CHECKLISTITEM.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
