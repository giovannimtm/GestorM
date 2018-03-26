package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.IMAGEMDOC;

/**
 * Created by Giovanni on 20/03/2018.
 */

public class IMAGEMDOCController {

    private SQLiteDatabase db;
    private IMAGEMDOC IMAGEMDOCModel;

    public Context PCON_context;

    public IMAGEMDOCController(Context LCON_Context) {
        PCON_context = LCON_Context;
        IMAGEMDOCModel = new IMAGEMDOC(PCON_context);
    }

    //CRUD WB
    public String FU_Insert_WB(IMAGEMDOC CLS_IMAGEMDOC) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_IMAGEMDOC != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_IMAGEMDOC, IMAGEMDOC.INSERT_WB, 0);
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

    public List<IMAGEMDOC> FU_Read_WB(IMAGEMDOC CLS_IMAGEMDOC, int INT_IDIMAGEMDOC) {
        Gson LGS_JSON = null;
        IMAGEMDOC[] LCLS_IMAGEMDOC =null;
        String LOBJ_Retorno = null;

        try {

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
                    CLS_IMAGEMDOC,
                    IMAGEMDOC.READ_WB, INT_IDIMAGEMDOC, "").toString();
            LGS_JSON = new Gson();
            LCLS_IMAGEMDOC = LGS_JSON.fromJson(LOBJ_Retorno.toString()
                    , IMAGEMDOC[].class);

            if (LCLS_IMAGEMDOC != null) {

            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {

        }
        return Arrays.asList(LCLS_IMAGEMDOC);
    }

    public String FU_Update_WB(IMAGEMDOC CLS_IMAGEMDOC, int INT_IDIMAGEMDOC) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {

            if (CLS_IMAGEMDOC != null || INT_IDIMAGEMDOC > 0) {
                if (ConexaoWebAPI.FU_WB_TestaConexao() == "true")
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_IMAGEMDOC, IMAGEMDOC.UPDATE_WB, INT_IDIMAGEMDOC);
                else
                {
                    if(FU_Update_BD(INT_IDIMAGEMDOC,CLS_IMAGEMDOC) == 1){
                        return new String("Salvo banco interno , não foi possível conectar-se a webapi");
                    }else
                    {return new String("Erro salvar banco interno, não foi possível conectar-se a webapi");}

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

    public String FU_Delete_WB(IMAGEMDOC CLS_IMAGEMDOC, int INT_IDIMAGEMDOC) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_IMAGEMDOC != null || INT_IDIMAGEMDOC > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_IMAGEMDOC, IMAGEMDOC.DELETE_WB, INT_IDIMAGEMDOC);
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
    public String FU_Insert_BD(IMAGEMDOC CLS_IMAGEMDOC) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = IMAGEMDOCModel.getWritableDatabase();
            IMAGEMDOCModel.onCreate(db);
            LCVA_VALUES = new ContentValues();
//            LCVA_VALUES.put(EMFSESSION., EMFSESSION.LOCAL_IDSESSION);
            LCVA_VALUES.put("SGDOCORIGEM", CLS_IMAGEMDOC.getSGDOCORIGEM().toString());
            LCVA_VALUES.put("IDDOCORIGEM", CLS_IMAGEMDOC.getIDDOCORIGEM().toString());
            LCVA_VALUES.put("IDITEMDOCORIGEM", CLS_IMAGEMDOC.getIDITEMDOCORIGEM().toString());
            LCVA_VALUES.put("SEQUENCIA", CLS_IMAGEMDOC.getSEQUENCIA().toString());
            LCVA_VALUES.put("TIPOARQUIVO", CLS_IMAGEMDOC.getTIPOARQUIVO().toString());
            LCVA_VALUES.put("IMAGEM", CLS_IMAGEMDOC.getIMAGEM().toString());
            LCVA_VALUES.put("DSIMAGEM", CLS_IMAGEMDOC.getDSIMAGEM().toString());
            LCVA_VALUES.put("EXTENSAOARQUIVO", CLS_IMAGEMDOC.getEXTENSAOARQUIVO().toString());

            LINT_RETURN = db.insert(IMAGEMDOC.TABLE, null, LCVA_VALUES);
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
            db = IMAGEMDOCModel.getReadableDatabase();
            LCUR_CURSOR = db.query(IMAGEMDOC.TABLE, campos, null, null, null, null, null, null);

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
            db = IMAGEMDOCModel.getReadableDatabase();
            LCUR_CURSOR = db.query(IMAGEMDOC.TABLE, campos, where, null, null, null, null, null);

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

    public int FU_Update_BD(int INT_IDIMAGEMDOC, IMAGEMDOC CLS_IMAGEMDOC) {
        ContentValues LCVA_VALUES;
        String where = "";
        int LINT_RETURN = 0;
        try {
            db = IMAGEMDOCModel.getWritableDatabase();

            where = "IDSESSION" + "=" + INT_IDIMAGEMDOC;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("SGDOCORIGEM", CLS_IMAGEMDOC.getSGDOCORIGEM().toString());
            LCVA_VALUES.put("IDDOCORIGEM", CLS_IMAGEMDOC.getIDDOCORIGEM().toString());
            LCVA_VALUES.put("IDITEMDOCORIGEM", CLS_IMAGEMDOC.getIDITEMDOCORIGEM().toString());
            LCVA_VALUES.put("SEQUENCIA", CLS_IMAGEMDOC.getSEQUENCIA().toString());
            LCVA_VALUES.put("TIPOARQUIVO", CLS_IMAGEMDOC.getTIPOARQUIVO().toString());
            LCVA_VALUES.put("IMAGEM", CLS_IMAGEMDOC.getIMAGEM().toString());
            LCVA_VALUES.put("DSIMAGEM", CLS_IMAGEMDOC.getDSIMAGEM().toString());
            LCVA_VALUES.put("EXTENSAOARQUIVO", CLS_IMAGEMDOC.getEXTENSAOARQUIVO().toString());

            LINT_RETURN = db.update(IMAGEMDOC.TABLE, LCVA_VALUES, where, null);
            db.close();

            return LINT_RETURN;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LCVA_VALUES = null;
        }
        return LINT_RETURN;
    }

    public void FU_Delete_BD(int INT_IDIMAGEMDOC) {
        try {
            String where = "IDSESSION" + "=" + INT_IDIMAGEMDOC;
            db = IMAGEMDOCModel.getReadableDatabase();
            db.delete(IMAGEMDOC.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}

