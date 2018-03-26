package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.CHECKLISTITEM;
import mtmsistemas.gestorm.Model.IMAGEMDOC;
import mtmsistemas.gestorm.Model.TIPOCOMPONENTE;

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
        contexts = context;
        CHECKLISTITEMController = new CHECKLISTITEM(contexts);
    }

    public String FU_Busca_Descricao_Componente(Object CDTIPOCOMPONENTE) {
        String descricao = "";
        try {
            TIPOCOMPONENTE[] tipocomponentes = TIPOCOMPONENTEController.FU_BuscaDescricao_WB(CDTIPOCOMPONENTE);
            descricao = (String) tipocomponentes[0].getDSTIPOCOMPONENTE();
        } catch (Exception e) {
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
                if (ConexaoWebAPI.FU_WB_TestaConexao() == "true")
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTITEM, CHECKLISTITEM.INSERT_WB, 0);
                else {
                    if (FU_Insert_BD(CLS_CHECKLISTITEM) == "1") {
                        return new String("Salvo banco interno , não foi possível conectar-se a webapi");
                    } else {
                        return new String("Erro salvar banco interno, não foi possível conectar-se a webapi");
                    }
                }

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

    public List<CHECKLISTITEM> FU_Read_WB(CHECKLISTITEM CLS_CHECKLISTITEM, int INT_IDCHECKLIST) {
        Gson LGS_JSON = null;
        CHECKLISTITEM[] LCLS_CHECKLISTITEM = null;
        String LOBJ_Retorno = null;
        Cursor LCUR_Cursor = null;

        try {

            if (ConexaoWebAPI.FU_WB_TestaConexao() == "true") {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
                        null,
                        CHECKLISTITEM.READ_WB,
                        INT_IDCHECKLIST,
                        "").toString();

                LGS_JSON = new Gson();
                LCLS_CHECKLISTITEM = LGS_JSON.fromJson(LOBJ_Retorno.toString(), CHECKLISTITEM[].class);

                if (LCLS_CHECKLISTITEM != null) {
                    for (CHECKLISTITEM item : LCLS_CHECKLISTITEM) {
                        item.setDESCRICAOCOMPONENTE(FU_Busca_Descricao_Componente(item.getCDTIPOCOMPONENTE()));
                    }

                    for (int LINT_COUNT = 0; LCLS_CHECKLISTITEM.length > LINT_COUNT; LINT_COUNT++) {
                        LCUR_Cursor = FU_Read_ID_BD((Integer) LCLS_CHECKLISTITEM[LINT_COUNT].getIDCHECKLIST(), (Integer) LCLS_CHECKLISTITEM[LINT_COUNT].getCDTIPOCOMPONENTE(), "");
                        if (LCUR_Cursor == null && LCUR_Cursor.getCount() == 0) {
                            if (FU_Insert_BD(LCLS_CHECKLISTITEM[LINT_COUNT]) == "1") {
                                //verificar mensagem para avisar erro
                            }
                        }
                    }
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

    public String FU_Update_WB(CHECKLISTITEM CLS_CHECKLISTITEM, int INT_CHECKLISTMESTRE, String STR_CaminhoImagem) {
        Object LOBJ_Retorno = null;
        Cursor LCUR_Cursor;
        IMAGEMDOCController LCLS_IMAGEMDOCController = null;
        IMAGEMDOC LCLS_IMAGEMDOC = null;
        String[] LSTR_Array;

        try {
            if (CLS_CHECKLISTITEM != null || INT_CHECKLISTMESTRE > 0) {
                LCUR_Cursor = FU_Read_ID_BD(INT_CHECKLISTMESTRE, (Integer) CLS_CHECKLISTITEM.getCDTIPOCOMPONENTE(), STR_CaminhoImagem);
                if (LCUR_Cursor != null && LCUR_Cursor.getCount() == 0) {
                    LSTR_Array = STR_CaminhoImagem.split(".");

                    LCLS_IMAGEMDOC = new IMAGEMDOC(contexts);
                    LCLS_IMAGEMDOC.setSGDOCORIGEM("CKL");
                    LCLS_IMAGEMDOC.setIDDOCORIGEM(CLS_CHECKLISTITEM.getIDCHECKLIST());
                    LCLS_IMAGEMDOC.setIDITEMDOCORIGEM(CLS_CHECKLISTITEM.getCDTIPOCOMPONENTE());
                    LCLS_IMAGEMDOC.setDSIMAGEM(CLS_CHECKLISTITEM.getDESCRICAOCOMPONENTE());

                    if (LSTR_Array[1].toString().toUpperCase() == "JPG"
                            || LSTR_Array[1].toString().toUpperCase() == "GIF"
                            || LSTR_Array[1].toString().toUpperCase() == "PNG") {
                        LCLS_IMAGEMDOC.setTIPOARQUIVO("IMAGEM");
                    } else if (LSTR_Array[1].toString().toUpperCase() == "MP4"
                            || LSTR_Array[1].toString().toUpperCase() == "MPG"
                            || LSTR_Array[1].toString().toUpperCase() == "MKV"
                            || LSTR_Array[1].toString().toUpperCase() == "AVI") {
                        LCLS_IMAGEMDOC.setTIPOARQUIVO("VIDEO");
                    }
                    LCLS_IMAGEMDOC.setEXTENSAOARQUIVO(LSTR_Array[1].toString());
                    LCLS_IMAGEMDOCController = new IMAGEMDOCController(contexts);
                    if (LCLS_IMAGEMDOCController.FU_Insert_BD(LCLS_IMAGEMDOC) == "1") {
                        //return new String("Salvo banco interno , não foi possível conectar-se a webapi");
                    }
                }
                if (ConexaoWebAPI.FU_WB_TestaConexao() == "true")
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTITEM, CHECKLISTITEM.UPDATE_WB, INT_CHECKLISTMESTRE);

                if (FU_Update_BD(INT_CHECKLISTMESTRE, CLS_CHECKLISTITEM) == "1") {
                    LOBJ_Retorno = "Salvo banco interno , não foi possível conectar-se a webapi";
                    return new String("Salvo banco interno , não foi possível conectar-se a webapi");
                } else {
                    return new String("Erro salvar banco interno, não foi possível conectar-se a webapi");
                }
            } else {
                return new String("Não pode enviar classe Null");
            }
        } catch (
                Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LOBJ_Retorno = null;
        }
    }

    public String FU_Delete_WB(CHECKLISTITEM CLS_CHECKLISTITEM, int INT_IDCHECKLISTITEM) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTITEM != null || INT_IDCHECKLISTITEM > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTITEM, CHECKLISTITEM.DELETE_WB, INT_IDCHECKLISTITEM);
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
        return Long.toString(LINT_RETURN);
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

    public Cursor FU_Read_ID_BD(int INT_IDCHECKLISTMESTRE, int INT_CDTIPOCOMPONENTE, String STR_CaminhoImagem) {
        Cursor LCUR_CURSOR = null;
        try {
            String[] campos = {"*"};
            String where = "IDCHECKLIST" + "=" + INT_IDCHECKLISTMESTRE;
            if (INT_CDTIPOCOMPONENTE > 0)
                where += "AND CDTIPOCOMPONENTE" + "=" + INT_CDTIPOCOMPONENTE;
            if (STR_CaminhoImagem.toUpperCase().trim() != "")
                where += "AND IMAGEMCOMPONENTE" + "=" + STR_CaminhoImagem;
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

    public String FU_Update_BD(int id, CHECKLISTITEM CLS_CHECKLISTITEM) {
        ContentValues LCVA_VALUES;
        String where = "";
        long LINT_RETURN = 0;
        try {
            db = CHECKLISTITEMController.getWritableDatabase();
            where = "IDCHECKLIST" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("IDCHECKLIST", CLS_CHECKLISTITEM.getIDCHECKLIST().toString());
            LCVA_VALUES.put("CDTIPOCOMPONENTE", CLS_CHECKLISTITEM.getCDTIPOCOMPONENTE().toString());
            LCVA_VALUES.put("FGSITUACAO", CLS_CHECKLISTITEM.getFGSITUACAO().toString());
            LCVA_VALUES.put("PCTAVALIACAO", CLS_CHECKLISTITEM.getPCTAVALIACAO().toString());
            LCVA_VALUES.put("SGUSER", CLS_CHECKLISTITEM.getSGUSER().toString());
            LCVA_VALUES.put("OBSERVACAO", CLS_CHECKLISTITEM.getOBSERVACAO().toString());
            LCVA_VALUES.put("IMAGEMCOMPONENTE", CLS_CHECKLISTITEM.getIMAGEMCOMPONENTE().toString());

            LINT_RETURN = db.update(CHECKLISTITEM.TABLE, LCVA_VALUES, where, null);
            db.close();

            return Long.toString(LINT_RETURN);

        } catch (Exception e) {
            return new String(e.getMessage());
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
