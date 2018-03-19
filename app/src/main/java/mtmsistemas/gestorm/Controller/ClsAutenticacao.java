package mtmsistemas.gestorm.Controller;

import mtmsistemas.gestorm.BuildConfig;
import mtmsistemas.gestorm.Model.CHECKLISTMESTRE;
import mtmsistemas.gestorm.Model.EMFSESSION;

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
                EMFSESSION.LOCAL_IDSESSION = LCLS_SESSION.getIDSESSION();

                CHECKLISTMESTREController checklistmestreController = new CHECKLISTMESTREController(null);
                CHECKLISTMESTRE checklistmestre = new CHECKLISTMESTRE(null);
                checklistmestreController.FU_Read_WB(checklistmestre,0,"");

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
