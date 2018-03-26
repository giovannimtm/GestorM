package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.EMFSESSION;
import mtmsistemas.gestorm.Model.SINCRONIZAITEM;
import mtmsistemas.gestorm.Model.SINCRONIZAMESTRE;

/**
 * Created by Giovanni on 21/03/2018.
 */

public class SINCRONIZAController {


    private SQLiteDatabase db;
    private SINCRONIZAMESTRE SINCRONIZAMESTREController;
    private SINCRONIZAITEM SINCRONIZAITEMController;

    public static Context contexts;

    public SINCRONIZAController(Context context) {
        SINCRONIZAITEMController = new SINCRONIZAITEM(context);
        SINCRONIZAMESTREController = new SINCRONIZAMESTRE(context);
        contexts = context;
    }

    //CRUD WB
//    public String FU_Insert_WB(Object CLS_CHECKLISTMESTRE) {
//        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
//        ClsUtil LCLS_UTIL = null;
//        Object LOBJ_Retorno = null;
//        try {
//            if (CLS_CHECKLISTMESTRE != null) {
//                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE, CHECKLISTMESTRE.INSERT_WB, 0);
//            } else {
//                return new String("Não pode enviar classe Null");
//            }
//            return LOBJ_Retorno.toString();
//
//        } catch (Exception ex) {
//            return new String("Exception: " + ex.getMessage());
//            //Log.e("TAG", Log.getStackTraceString(ex));
//        } finally {
//            LCLS_TPCOMPONENTE = null;
//            LCLS_UTIL = null;
//            LOBJ_Retorno = null;
//        }
//    }
//
//    public List<Object> FU_Read_WB(Object CLS_CHECKLISTMESTRE, int INT_IDCHECKLISTMESTRE, String STR_STATUS) {
//        Gson LGS_JSON = null;
//        CHECKLISTMESTRE[] LCLS_CHECKLISTMESTRE = null;
//        String LOBJ_Retorno = null;
//        Cursor LCUR_Cursor = null;
//        try {
//
//            LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
//                    CLS_CHECKLISTMESTRE,
//                    CHECKLISTMESTRE.READ_WB, INT_IDCHECKLISTMESTRE, STR_STATUS).toString();
//            LGS_JSON = new Gson();
//            LCLS_CHECKLISTMESTRE = LGS_JSON.fromJson(LOBJ_Retorno.toString()
//                    , CHECKLISTMESTRE[].class);
//
//            if (LCLS_CHECKLISTMESTRE != null) {
//                for (int LINT_COUNT = 0; LCLS_CHECKLISTMESTRE.length > LINT_COUNT; LINT_COUNT++) {
//                    LCUR_Cursor = FU_Read_ID_BD((Integer) LCLS_CHECKLISTMESTRE[LINT_COUNT].getIDCHECKLIST());
//                    if (LCUR_Cursor == null && LCUR_Cursor.getCount() == 0) {
//                        if (FU_Insert_BD(LCLS_CHECKLISTMESTRE[LINT_COUNT]) == "1") {
//                            //verificar mensagem para avisar erro
//                        }
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            try {
//                throw new Exception(ex.getMessage());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } finally {
//            LGS_JSON = null;
//            LOBJ_Retorno = null;
//            LCUR_Cursor = null;
//        }
//        return Arrays.asList(LCLS_CHECKLISTMESTRE);
//    }
//
//    public String FU_Update_WB(Object CLS_CHECKLISTMESTRE, int INT_IDCHECKLISTMESTRE) {
//        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
//        ClsUtil LCLS_UTIL = null;
//        Object LOBJ_Retorno = null;
//
//        try {
//            if (CLS_CHECKLISTMESTRE != null || INT_IDCHECKLISTMESTRE > 0) {
//                if (ConexaoWebAPI.FU_WB_TestaConexao() == "true")
//                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE, CHECKLISTMESTRE.UPDATE_WB, INT_IDCHECKLISTMESTRE);
//                else if (FU_Update_BD(INT_IDCHECKLISTMESTRE, CLS_CHECKLISTMESTRE) == "1") {
//                    return new String("Salvo banco interno , não foi possível conectar-se a webapi");
//                } else {
//                    return new String("Erro salvar banco interno, não foi possível conectar-se a webapi");
//                }
//
//            } else {
//                return new String("Não pode enviar classe Null");
//            }
//            return LOBJ_Retorno.toString();
//
//        } catch (Exception ex) {
//            return new String("Exception: " + ex.getMessage());
//            //Log.e("TAG", Log.getStackTraceString(ex));
//        } finally {
//            LCLS_TPCOMPONENTE = null;
//            LCLS_UTIL = null;
//            LOBJ_Retorno = null;
//        }
//    }
//
//    public String FU_Delete_WB(Object CLS_CHECKLISTMESTRE, int INT_IDCHECKLISTMESTRE) {
//        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
//        ClsUtil LCLS_UTIL = null;
//        Object LOBJ_Retorno = null;
//
//        try {
//            if (CLS_CHECKLISTMESTRE != null || INT_IDCHECKLISTMESTRE > 0) {
//                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE, CHECKLISTMESTRE.DELETE_WB, INT_IDCHECKLISTMESTRE);
//            } else {
//                return new String("Não pode enviar classe Null");
//            }
//            return LOBJ_Retorno.toString();
//
//        } catch (Exception ex) {
//            return new String("Exception: " + ex.getMessage());
//            //Log.e("TAG", Log.getStackTraceString(ex));
//        } finally {
//            LCLS_TPCOMPONENTE = null;
//            LCLS_UTIL = null;
//            LOBJ_Retorno = null;
//        }
//    }

    //CRUD BD
    public String FU_Insert_BD(String STR_Tabela,String STR_SGOPERACAO,int[] INT_IDOPERACAO,int[] INT_ITEMOPERACAO,int[] INT_SEQOPERACAO) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        ClsUtil LCLS_UTIL = null;
        Cursor LCUR_CURSOR = null;
        try {

            db = SINCRONIZAMESTREController.getWritableDatabase();
            SINCRONIZAMESTREController.onCreate(db);
            LCLS_UTIL = new ClsUtil();

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("TABELASINCRINIZACAO", STR_Tabela.toString());
//            LCVA_VALUES.put("LATITUDE", CLS_CHECKLISTMESTRE.getIDEQUIPAMENTO().toString());
//            LCVA_VALUES.put("LONGITUDE", CLS_CHECKLISTMESTRE.getCDTIPOCHECKLIST().toString());
            LCVA_VALUES.put("TIPOREDE", LCLS_UTIL.FU_verificaTipoConexao(contexts).toString());
//            LCVA_VALUES.put("STATUSREDE", CLS_CHECKLISTMESTRE.getCONTAGEMUSO().toString());
//            LCVA_VALUES.put("FGSINCRINIZA", CLS_CHECKLISTMESTRE.getDTREALIZADO().toString());
//            LCVA_VALUES.put("FGDELETATABELA", CLS_CHECKLISTMESTRE.getCONTAGEMUSO().toString());
            LCVA_VALUES.put("DHMANUTENANCE", LCLS_UTIL.FU_DataAtual().toString());
            LCVA_VALUES.put("SGUSER", EMFSESSION.LOCAL_NMUSUARIO);


            LINT_RETURN = db.insert(SINCRONIZAMESTRE.TABLE, null, LCVA_VALUES);
            //db.close();

            if (LINT_RETURN == -1)
                return "Erro ao inserir registro";
            else {
                String[] campos = {"MAX(IDSINCRINIZA) AS IDSINCRINIZA"};
                LCUR_CURSOR = db.query(SINCRONIZAMESTRE.TABLE, campos, null, null, null, null, null, null);

                if (LCUR_CURSOR != null) {
                    LCUR_CURSOR.moveToFirst();
                }
                db.close();

                LCUR_CURSOR.getString(LCUR_CURSOR.getColumnIndex("IDSINCRINIZA")).trim();

                db = SINCRONIZAMESTREController.getWritableDatabase();
                SINCRONIZAMESTREController.onCreate(db);

                for(int LINT_COUNT=0;INT_IDOPERACAO.length >LINT_COUNT;LINT_COUNT++){
                LCVA_VALUES = new ContentValues();
                LCVA_VALUES.put("IDSINCRINIZA", LCUR_CURSOR.getString(LCUR_CURSOR.getColumnIndex("IDSINCRINIZA")).trim());
                LCVA_VALUES.put("IDOPERACAO", Integer.toString(INT_IDOPERACAO[LINT_COUNT]));
                LCVA_VALUES.put("ITEMOPERACAO", Integer.toString(INT_ITEMOPERACAO[LINT_COUNT]));
                LCVA_VALUES.put("SEQSINCRINIZA", Integer.toString(INT_SEQOPERACAO[LINT_COUNT]));
                LCVA_VALUES.put("SGOPERACAO", STR_SGOPERACAO);
                //LCVA_VALUES.put("SEQITEMOPERACAO", CLS_Classe.getCONTAGEMUSO().toString());
                //LCVA_VALUES.put("FGSINCRINIZA", CLS_Classe.getDTREALIZADO().toString());
                LCVA_VALUES.put("DHMANUTENANCE", LCLS_UTIL.FU_DataAtual().toString());
                LCVA_VALUES.put("SGUSER", EMFSESSION.LOCAL_NMUSUARIO);
                LINT_RETURN = db.insert(SINCRONIZAITEM.TABLE, null, LCVA_VALUES);}
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return LCUR_CURSOR.getString(LCUR_CURSOR.getColumnIndex("IDSINCRINIZA")).trim();
    }

    public Cursor FU_Read_BD() {
        Cursor LCUR_CURSOR = null;
//        String[] campos = {String.valueOf(EMFSESSION.LOCAL_IDSESSION)};
        String[] campos = {"*"};
        try {
            db = SINCRONIZAMESTREController.getReadableDatabase();
            LCUR_CURSOR = db.query(SINCRONIZAMESTRE.TABLE, campos, null, null, null, null, null, null);

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

    public Cursor FU_Read_ID_BD(int INT_ID) {
        Cursor LCUR_CURSOR = null;
        try {
            String[] campos = {" * "};
            String where = "IDSINCRONIZA" + "=" + INT_ID;
            db = SINCRONIZAMESTREController.getReadableDatabase();
            LCUR_CURSOR = db.query(SINCRONIZAMESTRE.TABLE, campos, where, null, null, null, null, null);

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

    public String FU_Update_BD(int INT_ID, String STR_FGSINCRINIZA, String STR_FGDELETATABELA) {
        ContentValues LCVA_VALUES;
        String where = "";
        int LINT_RETURN = 0;
        ClsUtil LCLS_UTIL = null;
        try {
            db = SINCRONIZAMESTREController.getWritableDatabase();

            where = "IDSINCRONIZA" + "=" + INT_ID;

            LCVA_VALUES = new ContentValues();
//            LCVA_VALUES.put("TABELASINCRINIZACAO", CLS_Classe.getClass().getName().toString());
//            LCVA_VALUES.put("LATITUDE", CLS_CHECKLISTMESTRE.getIDEQUIPAMENTO().toString());
//            LCVA_VALUES.put("LONGITUDE", CLS_CHECKLISTMESTRE.getCDTIPOCHECKLIST().toString());
//            LCVA_VALUES.put("TIPOREDE", LCLS_UTIL.FU_verificaTipoConexao(contexts).toString());
//            LCVA_VALUES.put("STATUSREDE", CLS_CHECKLISTMESTRE.getCONTAGEMUSO().toString());
            LCVA_VALUES.put("FGSINCRINIZA", STR_FGSINCRINIZA.toString());
            LCVA_VALUES.put("FGDELETATABELA", STR_FGDELETATABELA.toString());
            LCVA_VALUES.put("DHMANUTENANCE", LCLS_UTIL.FU_DataAtual().toString());
            LCVA_VALUES.put("SGUSER", EMFSESSION.LOCAL_NMUSUARIO);

            LINT_RETURN = db.update(SINCRONIZAMESTRE.TABLE, LCVA_VALUES, where, null);
            db.close();
            return Integer.toString(LINT_RETURN);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return Integer.toString(LINT_RETURN);
    }

    public void FU_Delete_BD(int INT_ID) {
        try {
            String where = "IDSINCRONIZA" + "=" + INT_ID;
            db = SINCRONIZAMESTREController.getReadableDatabase();
            db.delete(SINCRONIZAMESTRE.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
