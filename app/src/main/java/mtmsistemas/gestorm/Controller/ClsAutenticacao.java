package mtmsistemas.gestorm.Controller;

import java.util.List;

import mtmsistemas.gestorm.BuildConfig;
import mtmsistemas.gestorm.Model.CHECKLISTITEM;
import mtmsistemas.gestorm.Model.EMFSESSION;
import mtmsistemas.gestorm.Model.EQUIPAMENTO;

/**
 * Created by Giovanni on 29/01/2018.
 */

public class ClsAutenticacao {

    public static String FU_AutenticaUsuario(EMFSESSION LCLS_SESSION) {
        Object LOBJ_Retorno = null;
        String LSTR_RETURN = "false";

        try {
            LCLS_SESSION = new EMFSESSION(null);

            if (BuildConfig.DEBUG) {
                LCLS_SESSION.setDHSESSION("0");
                LCLS_SESSION.setEQUIPMENT("2");
                LCLS_SESSION.setPASSWORD("MTM");
                LCLS_SESSION.setSGENVIRONMENT("GESTOR");
                LCLS_SESSION.setSGLANGUAGE("PT-BR");
                LCLS_SESSION.setSGUSER("MASTER");
            }
            //para teste , sem teste deixar a o if de cima
            // e pegar os dados da tela
            if (EMFSESSION.LOCAL_IDSESSION > 0) {
                LCLS_SESSION.setIDSESSION(EMFSESSION.LOCAL_IDSESSION);
            }

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA(LCLS_SESSION, "gestoricl/autentica", "POST", 0);
            if (LOBJ_Retorno != null) {
                if (LOBJ_Retorno.toString().toUpperCase().contains("EXCEPTION:")) {
                    return LOBJ_Retorno.toString();
                }
                LCLS_SESSION = null;
                LCLS_SESSION = (EMFSESSION) LOBJ_Retorno;

                //if(LCLS_SESSION.getMENSAGEM().length() > 6){
                // return LCLS_SESSION.getMENSAGEM().toString();}

                EMFSESSION.LOCAL_IDSESSION = LCLS_SESSION.getIDSESSION();
                CHECKLISTITEM checklistmestre = new CHECKLISTITEM(null);
                CHECKLISTITEMController l = new CHECKLISTITEMController(null);
                CHECKLISTITEM[] checklist ;
                //checklist = (CHECKLISTITEM[]) l.FU_Read_WB(checklistmestre,24);

                List<EQUIPAMENTO> List_Equipamento ;
                EQUIPAMENTO LCLS_EQUIPAMENTO = new EQUIPAMENTO(null);
                EQUIPAMENTOController LCLS_EQUIPAMENTOCONTROLLER = new EQUIPAMENTOController(null);
                //LCLS_EQUIPAMENTO = (EQUIPAMENTO) LCLS_EQUIPAMENTOCONTROLLER.FU_LIST_EQUIPAMENTO();

                //LCLS_EQUIPAMENTOCONTROLLER.F

                //EMFSESSIONConroller controle = new EMFSESSIONConroller(EMFSESSIONConroller.contexts);
                //controle.Insert(LCLS_SESSION);
                //controle.ReturnData();//controle.ReturnDataById(11254);
                //controle.Update(11254, LCLS_SESSION);
                //controle.Delete(11254);
                //TOCOMPONENTEController controller = new TIPOCOMPONENTEController(null);
                //controller.FU_WB_BuscaDescricao(2);

                //ConexaoWebAPI.FU_WB_EXECUTA(null, "TIPOCOMPONENTE/UPLOAD", "POST", 0);
                LSTR_RETURN = "true";
            }

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
//            Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
        }
        return LSTR_RETURN;
    }


    //----------------------------------codigo pode ser util para teste---------------------------------------
//        if (BuildInfo.DEBUG) {
//        return debugBackground;
//    } else {
//        return getBaseContext().getWallpaper();
//    }
}
