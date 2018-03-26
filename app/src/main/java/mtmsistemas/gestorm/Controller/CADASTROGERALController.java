package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.CADASTROGERAL;

/**
 * Created by Giovanni on 19/03/2018.
 */

public class CADASTROGERALController {

    private SQLiteDatabase db;
    private CADASTROGERAL CADASTROGERALController;

    public CADASTROGERALController(Context contexts) {
        CADASTROGERALController = new CADASTROGERAL(contexts);
    }

    //CRUD WB
    public String FU_Insert_WB(CADASTROGERAL CLS_CADASTROGERAL) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_CADASTROGERAL != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CADASTROGERAL , CADASTROGERAL.INSERT_WB, 0);
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

    public List<CADASTROGERAL> FU_Read_WB(CADASTROGERAL CLS_CADASTROGERAL , int INT_IDCADASTROGERAL, String STR_STATUS) {
        Gson LGS_JSON = null;
        CADASTROGERAL[] LCLS_CADASTROGERAL = null;
        String LOBJ_Retorno = null;
        Cursor LCUR_Cursor = null;
        try {

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
                    CLS_CADASTROGERAL,
                    CADASTROGERAL.READ_WB,
                    INT_IDCADASTROGERAL,
                    STR_STATUS).toString();

            LGS_JSON = new Gson();
            LCLS_CADASTROGERAL = LGS_JSON.fromJson(LOBJ_Retorno.toString(), CADASTROGERAL[].class);

            if (LCLS_CADASTROGERAL != null) {
                for (int LINT_COUNT = 0; LCLS_CADASTROGERAL.length > LINT_COUNT; LINT_COUNT++) {
                    LCUR_Cursor = FU_Read_ID_BD((Integer) LCLS_CADASTROGERAL[LINT_COUNT].get_CDCADASTRO());
                    if (LCUR_Cursor == null && LCUR_Cursor.getCount() == 0) {
                        if (FU_Insert_BD(LCLS_CADASTROGERAL[LINT_COUNT]) == "1") {
                            //verificar mensagem para avisar deu certo
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
        return Arrays.asList(LCLS_CADASTROGERAL);
    }

    public String FU_Update_WB(CADASTROGERAL CLS_CADASTROGERAL , int INT_CADASTROGERAL) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CADASTROGERAL != null || INT_CADASTROGERAL > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CADASTROGERAL , CADASTROGERAL.UPDATE_WB,INT_CADASTROGERAL );
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

    public String FU_Delete_WB(CADASTROGERAL CLS_CADASTROGERAL , int INT_CADASTROGERAL) {
        ClsUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CADASTROGERAL != null || INT_CADASTROGERAL > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CADASTROGERAL , CADASTROGERAL.DELETE_WB,INT_CADASTROGERAL );
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
    public String FU_Insert_BD(CADASTROGERAL CLS_CADASTROGERAL) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = CADASTROGERALController.getWritableDatabase();
            CADASTROGERALController.onCreate(db);
            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("CDCADASTRO", CLS_CADASTROGERAL.get_CDCADASTRO().toString());
            LCVA_VALUES.put("CGCCPF", CLS_CADASTROGERAL.get_CGCCPF().toString());
            LCVA_VALUES.put("FGACTIVE", CLS_CADASTROGERAL.get_FGACTIVE().toString());
            LCVA_VALUES.put("FGFISICAJURIDICA", CLS_CADASTROGERAL.get_FGFISICAJURIDICA().toString());
            LCVA_VALUES.put("RZSOCIAL", CLS_CADASTROGERAL.get_RZSOCIAL().toString());
            LCVA_VALUES.put("NMFANTASIA", CLS_CADASTROGERAL.get_NMFANTASIA().toString());
            LCVA_VALUES.put("ENDERECO", CLS_CADASTROGERAL.get_ENDERECO().toString());
            LCVA_VALUES.put("NRENDERECO", CLS_CADASTROGERAL.get_NRENDERECO().toString());
            LCVA_VALUES.put("COMPLEND", CLS_CADASTROGERAL.get_COMPLEND().toString());
            LCVA_VALUES.put("BAIRRO", CLS_CADASTROGERAL.get_BAIRRO().toString());
            LCVA_VALUES.put("CEP", CLS_CADASTROGERAL.get_CEP().toString());
            LCVA_VALUES.put("NRTELEFONE", CLS_CADASTROGERAL.get_NRTELEFONE().toString());
            LCVA_VALUES.put("RAMALTELEFONE", CLS_CADASTROGERAL.get_RAMALTELEFONE().toString());
            LCVA_VALUES.put("NRCELULAR", CLS_CADASTROGERAL.get_NRCELULAR().toString());
            LCVA_VALUES.put("NRFAX", CLS_CADASTROGERAL.get_NRFAX().toString());
            LCVA_VALUES.put("ENDEMAIL", CLS_CADASTROGERAL.get_ENDEMAIL().toString());
            LCVA_VALUES.put("HOMEPAGE", CLS_CADASTROGERAL.get_HOMEPAGE().toString());
            LCVA_VALUES.put("RGINSCEST", CLS_CADASTROGERAL.get_RGINSCEST().toString());
            LCVA_VALUES.put("INSCRMUN", CLS_CADASTROGERAL.get_INSCRMUN().toString());

            LINT_RETURN = db.insert(CLS_CADASTROGERAL.TABLE, null, LCVA_VALUES);
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
            db = CADASTROGERALController.getReadableDatabase();
            LCUR_CURSOR = db.query(CADASTROGERAL.TABLE, campos, null, null, null, null, null, null);

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
            db = CADASTROGERALController.getReadableDatabase();
            LCUR_CURSOR = db.query(CADASTROGERAL.TABLE, campos, where, null, null, null, null, null);

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

    public void FU_Update_BD(int id, CADASTROGERAL CLS_CADASTROGERAL) {
        ContentValues LCVA_VALUES;
        String where = "";
        try {
            db = CADASTROGERALController.getWritableDatabase();

            where = "IDSESSION" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("CGCCPF", CLS_CADASTROGERAL.get_CGCCPF().toString());
            LCVA_VALUES.put("FGACTIVE", CLS_CADASTROGERAL.get_FGACTIVE().toString());
            LCVA_VALUES.put("FGFISICAJURIDICA", CLS_CADASTROGERAL.get_FGFISICAJURIDICA().toString());
            LCVA_VALUES.put("RZSOCIAL", CLS_CADASTROGERAL.get_RZSOCIAL().toString());
            LCVA_VALUES.put("NMFANTASIA", CLS_CADASTROGERAL.get_NMFANTASIA().toString());
            LCVA_VALUES.put("ENDERECO", CLS_CADASTROGERAL.get_ENDERECO().toString());
            LCVA_VALUES.put("NRENDERECO", CLS_CADASTROGERAL.get_NRENDERECO().toString());
            LCVA_VALUES.put("COMPLEND", CLS_CADASTROGERAL.get_COMPLEND().toString());
            LCVA_VALUES.put("BAIRRO", CLS_CADASTROGERAL.get_BAIRRO().toString());
            LCVA_VALUES.put("CEP", CLS_CADASTROGERAL.get_CEP().toString());
            LCVA_VALUES.put("NRTELEFONE", CLS_CADASTROGERAL.get_NRTELEFONE().toString());
            LCVA_VALUES.put("RAMALTELEFONE", CLS_CADASTROGERAL.get_RAMALTELEFONE().toString());
            LCVA_VALUES.put("NRCELULAR", CLS_CADASTROGERAL.get_NRCELULAR().toString());
            LCVA_VALUES.put("NRFAX", CLS_CADASTROGERAL.get_NRFAX().toString());
            LCVA_VALUES.put("ENDEMAIL", CLS_CADASTROGERAL.get_ENDEMAIL().toString());
            LCVA_VALUES.put("HOMEPAGE", CLS_CADASTROGERAL.get_HOMEPAGE().toString());
            LCVA_VALUES.put("RGINSCEST", CLS_CADASTROGERAL.get_RGINSCEST().toString());
            LCVA_VALUES.put("INSCRMUN", CLS_CADASTROGERAL.get_INSCRMUN().toString());
            db.update(CLS_CADASTROGERAL.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void FU_Delete_BD(int id) {
        try {
            String where = "IDSESSION" + "=" + id;
            db = CADASTROGERALController.getReadableDatabase();
            db.delete(CADASTROGERAL.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
