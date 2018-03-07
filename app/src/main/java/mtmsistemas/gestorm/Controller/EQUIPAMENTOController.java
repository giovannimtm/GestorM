package mtmsistemas.gestorm.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import mtmsistemas.gestorm.Model.DATABASE;
import mtmsistemas.gestorm.Model.EQUIPAMENTO;

/**
 * Created by Giovanni on 21/02/2018.
 */

public class EQUIPAMENTOController {

    private SQLiteDatabase db;
    private EQUIPAMENTO EQUIPAMENTONModel;

    public EQUIPAMENTOController(Context context) {
        EQUIPAMENTONModel = new EQUIPAMENTO(context);
    }

    public String Insert(EQUIPAMENTO CLS_EQUIPAMENTO) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN;

        try {
            db = EQUIPAMENTONModel.getWritableDatabase();
            LCVA_VALUES = new ContentValues();
          //LCVA_VALUES.put(EQUIPAMENTONModel.IDEQUIPAMENTO      ,CLS_EQUIPAMENTO.getIDEQUIPAMENTO());
            LCVA_VALUES.put(EQUIPAMENTONModel.REFEQUIPAMENTO     ,CLS_EQUIPAMENTO.getREFEQUIPAMENTO()     );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDEMPRESARAST      ,CLS_EQUIPAMENTO.getCDEMPRESARAST()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.NRMCT              ,CLS_EQUIPAMENTO.getNRMCT()              );
            LCVA_VALUES.put(EQUIPAMENTONModel.DSEQUIPAMENTO      ,CLS_EQUIPAMENTO.getDSEQUIPAMENTO()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDCENTRORESULTADO  ,CLS_EQUIPAMENTO.getCDCENTRORESULTADO()  );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDRESPONSAVEL      ,CLS_EQUIPAMENTO.getCDRESPONSAVEL()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDPROPRIETARIO     ,CLS_EQUIPAMENTO.getCDPROPRIETARIO()     );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDCIDADE           ,CLS_EQUIPAMENTO.getCDCIDADE()           );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDGRUPOEQUIPAMENTO ,CLS_EQUIPAMENTO.getCDGRUPOEQUIPAMENTO() );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDTIPOEQUIPAMENTO  ,CLS_EQUIPAMENTO.getCDTIPOEQUIPAMENTO()  );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDMODELOEQUIPAMENTO,CLS_EQUIPAMENTO.getCDMODELOEQUIPAMENTO());
            LCVA_VALUES.put(EQUIPAMENTONModel.CDGRUPOCOMB        ,CLS_EQUIPAMENTO.getCDGRUPOCOMB()        );
            LCVA_VALUES.put(EQUIPAMENTONModel.FGTIPOMEDIDOR      ,CLS_EQUIPAMENTO.getFGTIPOMEDIDOR()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDCOR              ,CLS_EQUIPAMENTO.getCDCOR()              );
            LCVA_VALUES.put(EQUIPAMENTONModel.NRPATRIMONIO       ,CLS_EQUIPAMENTO.getNRPATRIMONIO()       );
            LCVA_VALUES.put(EQUIPAMENTONModel.OBSERVACAO         ,CLS_EQUIPAMENTO.getOBSERVACAO()         );
            LCVA_VALUES.put(EQUIPAMENTONModel.ANOFABRICA         ,CLS_EQUIPAMENTO.getANOFABRICA()         );
            LCVA_VALUES.put(EQUIPAMENTONModel.ANOMODELO          ,CLS_EQUIPAMENTO.getANOMODELO()          );
            LCVA_VALUES.put(EQUIPAMENTONModel.DTAQUISICAO        ,CLS_EQUIPAMENTO.getDTAQUISICAO()        );
            LCVA_VALUES.put(EQUIPAMENTONModel.FGACTIVE           ,CLS_EQUIPAMENTO.getFGACTIVE()           );
            LCVA_VALUES.put(EQUIPAMENTONModel.SGUSER             ,CLS_EQUIPAMENTO.getSGUSER()             );
            LCVA_VALUES.put(EQUIPAMENTONModel.OBJECTVERSION      ,CLS_EQUIPAMENTO.getOBJECTVERSION()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.SERVERORIG         ,CLS_EQUIPAMENTO.getSERVERORIG()         );
            LCVA_VALUES.put(EQUIPAMENTONModel.DHCTRLREPLIC       ,CLS_EQUIPAMENTO.getDHCTRLREPLIC()       );

            LINT_RETURN = db.insert(EQUIPAMENTO.TABLE, null, LCVA_VALUES);
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
        String[] campos = {String.valueOf(EQUIPAMENTONModel.getIDEQUIPAMENTO())};

        try {
            db = EQUIPAMENTONModel.getReadableDatabase();
            LCUR_CURSOR = db.query(EQUIPAMENTO.TABLE, campos, null, null, null, null, null, null);

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
            String where = "IDEQUIPAMENTO = " + id;
            db = EQUIPAMENTONModel.getReadableDatabase();
            LCUR_CURSOR = db.query(DATABASE.TABLE, campos, where, null, null, null, null, null);

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

    public void Update(int id, EQUIPAMENTO CLS_EQUIPAMENTO) {
        ContentValues LCVA_VALUES;
        String where = "";
        try {
            db = EQUIPAMENTONModel.getWritableDatabase();

            where = "IDEQUIPAMENTO = " + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put(EQUIPAMENTONModel.REFEQUIPAMENTO     ,CLS_EQUIPAMENTO.getREFEQUIPAMENTO()     );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDEMPRESARAST      ,CLS_EQUIPAMENTO.getCDEMPRESARAST()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.NRMCT              ,CLS_EQUIPAMENTO.getNRMCT()              );
            LCVA_VALUES.put(EQUIPAMENTONModel.DSEQUIPAMENTO      ,CLS_EQUIPAMENTO.getDSEQUIPAMENTO()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDCENTRORESULTADO  ,CLS_EQUIPAMENTO.getCDCENTRORESULTADO()  );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDRESPONSAVEL      ,CLS_EQUIPAMENTO.getCDRESPONSAVEL()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDPROPRIETARIO     ,CLS_EQUIPAMENTO.getCDPROPRIETARIO()     );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDCIDADE           ,CLS_EQUIPAMENTO.getCDCIDADE()           );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDGRUPOEQUIPAMENTO ,CLS_EQUIPAMENTO.getCDGRUPOEQUIPAMENTO() );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDTIPOEQUIPAMENTO  ,CLS_EQUIPAMENTO.getCDTIPOEQUIPAMENTO()  );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDMODELOEQUIPAMENTO,CLS_EQUIPAMENTO.getCDMODELOEQUIPAMENTO());
            LCVA_VALUES.put(EQUIPAMENTONModel.CDGRUPOCOMB        ,CLS_EQUIPAMENTO.getCDGRUPOCOMB()        );
            LCVA_VALUES.put(EQUIPAMENTONModel.FGTIPOMEDIDOR      ,CLS_EQUIPAMENTO.getFGTIPOMEDIDOR()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.CDCOR              ,CLS_EQUIPAMENTO.getCDCOR()              );
            LCVA_VALUES.put(EQUIPAMENTONModel.NRPATRIMONIO       ,CLS_EQUIPAMENTO.getNRPATRIMONIO()       );
            LCVA_VALUES.put(EQUIPAMENTONModel.OBSERVACAO         ,CLS_EQUIPAMENTO.getOBSERVACAO()         );
            LCVA_VALUES.put(EQUIPAMENTONModel.ANOFABRICA         ,CLS_EQUIPAMENTO.getANOFABRICA()         );
            LCVA_VALUES.put(EQUIPAMENTONModel.ANOMODELO          ,CLS_EQUIPAMENTO.getANOMODELO()          );
            LCVA_VALUES.put(EQUIPAMENTONModel.DTAQUISICAO        ,CLS_EQUIPAMENTO.getDTAQUISICAO()        );
            LCVA_VALUES.put(EQUIPAMENTONModel.FGACTIVE           ,CLS_EQUIPAMENTO.getFGACTIVE()           );
            LCVA_VALUES.put(EQUIPAMENTONModel.SGUSER             ,CLS_EQUIPAMENTO.getSGUSER()             );
            LCVA_VALUES.put(EQUIPAMENTONModel.OBJECTVERSION      ,CLS_EQUIPAMENTO.getOBJECTVERSION()      );
            LCVA_VALUES.put(EQUIPAMENTONModel.SERVERORIG         ,CLS_EQUIPAMENTO.getSERVERORIG()         );
            LCVA_VALUES.put(EQUIPAMENTONModel.DHCTRLREPLIC       ,CLS_EQUIPAMENTO.getDHCTRLREPLIC()       );


            db.update(EQUIPAMENTO.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void Delete(int id) {
        try {
            String where = "IDEQUIPAMENTO = " + id;
            db = EQUIPAMENTONModel.getReadableDatabase();
            db.delete(EQUIPAMENTO.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static List<EQUIPAMENTO> FU_LIST_EQUIPAMENTO() throws Exception {
        Gson LGS_JSON = null;
        EQUIPAMENTO[] LCLS_EQUIPAMENTO = null;
        String LOBJ_Retorno = null;

        try {

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_AOBJECT(
                    null,
                    "equipamento",
                    "GET",0).toString();
            LGS_JSON = new Gson();
            LCLS_EQUIPAMENTO = LGS_JSON.fromJson(LOBJ_Retorno.toString()
                    , EQUIPAMENTO[].class);

            if (LCLS_EQUIPAMENTO != null) {

            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {

        }
        return Arrays.asList(LCLS_EQUIPAMENTO);
    }
}
