package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vinicius on 25/10/2017.
 */

public class EQUIPAMENTO extends SQLiteOpenHelper {

    public static String TABLE = "EQUIPAMENTO";
    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE + "/read";
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE+"/delete";

    public String IDEQUIPAMENTO = null;
    public String REFEQUIPAMENTO = null;
    public String CDEMPRESARAST = null;
    public String NRMCT = null;
    public String DSEQUIPAMENTO = null;
    public String CDCENTRORESULTADO = null;
    public String CDRESPONSAVEL = null;
    public String CDPROPRIETARIO = null;
    public String CDCIDADE = null;
    public String CDGRUPOEQUIPAMENTO = null;
    public String CDTIPOEQUIPAMENTO = null;
    public String CDMODELOEQUIPAMENTO = null;
    public String CDGRUPOCOMB = null;
    public String FGTIPOMEDIDOR = null;
    public String CDCOR = null;
    public String NRPATRIMONIO = null;
    public String OBSERVACAO = null;
    public String ANOFABRICA = null;
    public String ANOMODELO = null;
    public String DTAQUISICAO = null;
    public String FGACTIVE = null;
    public String SGUSER = null;
    public String OBJECTVERSION = null;
    public String SERVERORIG = null;
    public String DHCTRLREPLIC = null;
    //    private String MENSAGEM;


    public String getIDEQUIPAMENTO() {
        return IDEQUIPAMENTO;
    }

    public void setIDEQUIPAMENTO(String IDEQUIPAMENTO) {
        this.IDEQUIPAMENTO = IDEQUIPAMENTO;
    }

    public String getREFEQUIPAMENTO() {
        return REFEQUIPAMENTO;
    }

    public void setREFEQUIPAMENTO(String REFEQUIPAMENTO) {
        this.REFEQUIPAMENTO = REFEQUIPAMENTO;
    }

    public String getCDEMPRESARAST() {
        return CDEMPRESARAST;
    }

    public void setCDEMPRESARAST(String CDEMPRESARAST) {
        this.CDEMPRESARAST = CDEMPRESARAST;
    }

    public String getNRMCT() {
        return NRMCT;
    }

    public void setNRMCT(String NRMCT) {
        this.NRMCT = NRMCT;
    }

    public String getDSEQUIPAMENTO() {
        return DSEQUIPAMENTO;
    }

    public void setDSEQUIPAMENTO(String DSEQUIPAMENTO) {
        this.DSEQUIPAMENTO = DSEQUIPAMENTO;
    }

    public String getCDCENTRORESULTADO() {
        return CDCENTRORESULTADO;
    }

    public void setCDCENTRORESULTADO(String CDCENTRORESULTADO) {
        this.CDCENTRORESULTADO = CDCENTRORESULTADO;
    }

    public String getCDRESPONSAVEL() {
        return CDRESPONSAVEL;
    }

    public void setCDRESPONSAVEL(String CDRESPONSAVEL) {
        this.CDRESPONSAVEL = CDRESPONSAVEL;
    }

    public String getCDPROPRIETARIO() {
        return CDPROPRIETARIO;
    }

    public void setCDPROPRIETARIO(String CDPROPRIETARIO) {
        this.CDPROPRIETARIO = CDPROPRIETARIO;
    }

    public String getCDCIDADE() {
        return CDCIDADE;
    }

    public void setCDCIDADE(String CDCIDADE) {
        this.CDCIDADE = CDCIDADE;
    }

    public String getCDGRUPOEQUIPAMENTO() {
        return CDGRUPOEQUIPAMENTO;
    }

    public void setCDGRUPOEQUIPAMENTO(String CDGRUPOEQUIPAMENTO) {
        this.CDGRUPOEQUIPAMENTO = CDGRUPOEQUIPAMENTO;
    }

    public String getCDTIPOEQUIPAMENTO() {
        return CDTIPOEQUIPAMENTO;
    }

    public void setCDTIPOEQUIPAMENTO(String CDTIPOEQUIPAMENTO) {
        this.CDTIPOEQUIPAMENTO = CDTIPOEQUIPAMENTO;
    }

    public String getCDMODELOEQUIPAMENTO() {
        return CDMODELOEQUIPAMENTO;
    }

    public void setCDMODELOEQUIPAMENTO(String CDMODELOEQUIPAMENTO) {
        this.CDMODELOEQUIPAMENTO = CDMODELOEQUIPAMENTO;
    }

    public String getCDGRUPOCOMB() {
        return CDGRUPOCOMB;
    }

    public void setCDGRUPOCOMB(String CDGRUPOCOMB) {
        this.CDGRUPOCOMB = CDGRUPOCOMB;
    }

    public String getFGTIPOMEDIDOR() {
        return FGTIPOMEDIDOR;
    }

    public void setFGTIPOMEDIDOR(String FGTIPOMEDIDOR) {
        this.FGTIPOMEDIDOR = FGTIPOMEDIDOR;
    }

    public String getCDCOR() {
        return CDCOR;
    }

    public void setCDCOR(String CDCOR) {
        this.CDCOR = CDCOR;
    }

    public String getNRPATRIMONIO() {
        return NRPATRIMONIO;
    }

    public void setNRPATRIMONIO(String NRPATRIMONIO) {
        this.NRPATRIMONIO = NRPATRIMONIO;
    }

    public String getOBSERVACAO() {
        return OBSERVACAO;
    }

    public void setOBSERVACAO(String OBSERVACAO) {
        this.OBSERVACAO = OBSERVACAO;
    }

    public String getANOFABRICA() {
        return ANOFABRICA;
    }

    public void setANOFABRICA(String ANOFABRICA) {
        this.ANOFABRICA = ANOFABRICA;
    }

    public String getANOMODELO() {
        return ANOMODELO;
    }

    public void setANOMODELO(String ANOMODELO) {
        this.ANOMODELO = ANOMODELO;
    }

    public String getDTAQUISICAO() {
        return DTAQUISICAO;
    }

    public void setDTAQUISICAO(String DTAQUISICAO) {
        this.DTAQUISICAO = DTAQUISICAO;
    }

    public String getFGACTIVE() {
        return FGACTIVE;
    }

    public void setFGACTIVE(String FGACTIVE) {
        this.FGACTIVE = FGACTIVE;
    }

    public String getSGUSER() {
        return SGUSER;
    }

    public void setSGUSER(String SGUSER) {
        this.SGUSER = SGUSER;
    }

    public String getOBJECTVERSION() {
        return OBJECTVERSION;
    }

    public void setOBJECTVERSION(String OBJECTVERSION) {
        this.OBJECTVERSION = OBJECTVERSION;
    }

    public String getSERVERORIG() {
        return SERVERORIG;
    }

    public void setSERVERORIG(String SERVERORIG) {
        this.SERVERORIG = SERVERORIG;
    }

    public String getDHCTRLREPLIC() {
        return DHCTRLREPLIC;
    }

    public void setDHCTRLREPLIC(String DHCTRLREPLIC) {
        this.DHCTRLREPLIC = DHCTRLREPLIC;
    }


//    public String get_Mensagem() {return MENSAGEM;}
//
//    public void set_Mensagem(String Mensagem) {this.MENSAGEM = Mensagem;}


//    public EQUIPAMENTO(JSONObject object) {
//        try {
//            this.IDEQUIPAMENTO = object.getString("IDEQUIPAMENTO");
//            this.REFEQUIPAMENTO = object.getString("REFEQUIPAMENTO");;
//            this.CDEMPRESARAST = object.getString("CDEMPRESARAST");;
//            this.NRMCT = object.getString("NRMCT");;
//            this.DSEQUIPAMENTO = object.getString("DSEQUIPAMENTO");
////            this._CDCENTRORESULTADO = object.getString("id");
////            this._CDRESPONSAVEL = object.getString("id");
////            this._CDPROPRIETARIO = object.getString("id");
////            this._CDCIDADE = object.getString("id");
////            this._CDGRUPOEQUIPAMENTO = object.getString("id");
////            this._CDTIPOEQUIPAMENTO = object.getString("id");
////            this._CDMODELOEQUIPAMENTO = object.getString("id");
////            this._CDGRUPOCOMB = object.getString("id");
////            this._FGTIPOMEDIDOR = object.getString("id");
////            this._CDCOR = object.getString("id");
////            this._NRPATRIMONIO = object.getString("id");
////            this._OBSERVACAO = object.getString("id");
////            this._ANOFABRICA = object.getString("id");
////            this._ANOMODELO = object.getString("id");
////            this._DTAQUISICAO = object.getString("id");
////            this._FGACTIVE = object.getString("id");
////            this._SGUSER = object.getString("id");
////            this._OBJECTVERSION = object.getString("id");
////            this._SERVERORIG = object.getString("id");
////            this._DHCTRLREPLIC = object.getString("id");
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public EQUIPAMENTO(EQUIPAMENTO CLS_EQUIPAMENTO) {
//        this.IDEQUIPAMENTO = CLS_EQUIPAMENTO.IDEQUIPAMENTO;
//        this.DSEQUIPAMENTO = CLS_EQUIPAMENTO.DSEQUIPAMENTO;
//    }

    //Retorna uma lista de pessoas com as informações retornadas do JSON
//    public List<EQUIPAMENTO> getPessoas(String jsonString) {
//        List<EQUIPAMENTO> pessoas = new ArrayList<EQUIPAMENTO>();
//        try {
//            JSONArray pessoasJson = new JSONArray(jsonString);
//            JSONObject pessoa;
//
//            for (int i = 0; i < pessoasJson.length(); i++) {
//                pessoa = new JSONObject(pessoasJson.getString(i));
//                Log.i("PESSOA ENCONTRADA: ",
//                        "nome=" + pessoa.getString("IDEQUIPAMENTO"));
//
//                EQUIPAMENTO objetoPessoa = new EQUIPAMENTO();
//                objetoPessoa.setIDEQUIPAMENTO(pessoa.getString("IDEQUIPAMENTO"));
//                objetoPessoa.setDSEQUIPAMENTO(pessoa.getString("REFEQUIPAMENTO"));
//                pessoas.add(objetoPessoa);
//            }
//
//        } catch (JSONException e) {
//            Log.e("Erro", "Erro no parsing do JSON", e);
//        }
//        return pessoas;
//    }

    public EQUIPAMENTO(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE + "("
                + IDEQUIPAMENTO + " integer primary key autoincrement,"
                + REFEQUIPAMENTO + " text,"
                + CDEMPRESARAST + " text,"
                + NRMCT + " text,"
                + DSEQUIPAMENTO + " text,"
                + CDCENTRORESULTADO + " text,"
                + CDRESPONSAVEL + " text,"
                + CDPROPRIETARIO + " text,"
                + CDCIDADE + " text,"
                + CDGRUPOEQUIPAMENTO + " text,"
                + CDTIPOEQUIPAMENTO + " text,"
                + CDMODELOEQUIPAMENTO + " text,"
                + CDGRUPOCOMB + " text,"
                + FGTIPOMEDIDOR + " text,"
                + CDCOR + " text,"
                + NRPATRIMONIO + " text,"
                + OBSERVACAO + " text,"
                + ANOFABRICA + " text,"
                + ANOMODELO + " text,"
                + DTAQUISICAO + " text,"
                + FGACTIVE + " text,"
                + SGUSER + " text,"
                + OBJECTVERSION + " text,"
                + SERVERORIG + " text,"
                + DHCTRLREPLIC + " text,"
                + ")";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
