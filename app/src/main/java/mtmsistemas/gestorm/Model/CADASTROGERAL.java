package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CADASTROGERAL extends SQLiteOpenHelper {

    public static String TABLE = "EQUIPAMENTO";

    private Object CDCADASTRO = null;
    private Object CGCCPF = null;
    private Object FGACTIVE = null;
    private Object FGFISICAJURIDICA = null;
    private Object RZSOCIAL = null;
    private Object NMFANTASIA = null;
    private Object ENDERECO = null;
    private Object NRENDERECO = null;
    private Object COMPLEND = null;
    private Object BAIRRO = null;
    private Object CDCIDADE = null;
    private Object CEP = null;
    private Object NRTELEFONE = null;
    private Object RAMALTELEFONE = null;
    private Object NRCELULAR = null;
    private Object NRFAX = null;
    private Object ENDEMAIL = null;
    private Object HOMEPAGE = null;
    private Object RGINSCEST = null;
    private Object INSCRMUN = null;

    public CADASTROGERAL(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
    }

    public Object getCDCADASTRO() {
        return CDCADASTRO;
    }

    public void setCDCADASTRO(Object CDCADASTRO) {
        this.CDCADASTRO = CDCADASTRO;
    }

    public Object getCGCCPF() {
        return CGCCPF;
    }

    public void setCGCCPF(Object CGCCPF) {
        this.CGCCPF = CGCCPF;
    }

    public Object getFGACTIVE() {
        return FGACTIVE;
    }

    public void setFGACTIVE(Object FGACTIVE) {
        this.FGACTIVE = FGACTIVE;
    }

    public Object getFGFISICAJURIDICA() {
        return FGFISICAJURIDICA;
    }

    public void setFGFISICAJURIDICA(Object FGFISICAJURIDICA) {
        this.FGFISICAJURIDICA = FGFISICAJURIDICA;
    }

    public Object getRZSOCIAL() {
        return RZSOCIAL;
    }

    public void setRZSOCIAL(Object RZSOCIAL) {
        this.RZSOCIAL = RZSOCIAL;
    }

    public Object getNMFANTASIA() {
        return NMFANTASIA;
    }

    public void setNMFANTASIA(Object NMFANTASIA) {
        this.NMFANTASIA = NMFANTASIA;
    }

    public Object getENDERECO() {
        return ENDERECO;
    }

    public void setENDERECO(Object ENDERECO) {
        this.ENDERECO = ENDERECO;
    }

    public Object getNRENDERECO() {
        return NRENDERECO;
    }

    public void setNRENDERECO(Object NRENDERECO) {
        this.NRENDERECO = NRENDERECO;
    }

    public Object getCOMPLEND() {
        return COMPLEND;
    }

    public void setCOMPLEND(Object COMPLEND) {
        this.COMPLEND = COMPLEND;
    }

    public Object getBAIRRO() {
        return BAIRRO;
    }

    public void setBAIRRO(Object BAIRRO) {
        this.BAIRRO = BAIRRO;
    }

    public Object getCDCIDADE() {
        return CDCIDADE;
    }

    public void setCDCIDADE(Object CDCIDADE) {
        this.CDCIDADE = CDCIDADE;
    }

    public Object getCEP() {
        return CEP;
    }

    public void setCEP(Object CEP) {
        this.CEP = CEP;
    }

    public Object getNRTELEFONE() {
        return NRTELEFONE;
    }

    public void setNRTELEFONE(Object NRTELEFONE) {
        this.NRTELEFONE = NRTELEFONE;
    }

    public Object getRAMALTELEFONE() {
        return RAMALTELEFONE;
    }

    public void setRAMALTELEFONE(Object RAMALTELEFONE) {
        this.RAMALTELEFONE = RAMALTELEFONE;
    }

    public Object getNRCELULAR() {
        return NRCELULAR;
    }

    public void setNRCELULAR(Object NRCELULAR) {
        this.NRCELULAR = NRCELULAR;
    }

    public Object getNRFAX() {
        return NRFAX;
    }

    public void setNRFAX(Object NRFAX) {
        this.NRFAX = NRFAX;
    }

    public Object getENDEMAIL() {
        return ENDEMAIL;
    }

    public void setENDEMAIL(Object ENDEMAIL) {
        this.ENDEMAIL = ENDEMAIL;
    }

    public Object getHOMEPAGE() {
        return HOMEPAGE;
    }

    public void setHOMEPAGE(Object HOMEPAGE) {
        this.HOMEPAGE = HOMEPAGE;
    }

    public Object getRGINSCEST() {
        return RGINSCEST;
    }

    public void setRGINSCEST(Object RGINSCEST) {
        this.RGINSCEST = RGINSCEST;
    }

    public Object getINSCRMUN() {
        return INSCRMUN;
    }

    public void setINSCRMUN(Object INSCRMUN) {
        this.INSCRMUN = INSCRMUN;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE + "("
                + "CDCADASTRO" + " integer primary key autoincrement,"
                + "FGACTIVE" + " text,"
                + "FGFISICAJURIDICA" + " text,"
                + "RZSOCIAL" + " text,"
                + "NMFANTASIA" + " text,"
                + "ENDERECO" + " text,"
                + "NRENDERECO" + " text,"
                + "COMPLEND" + " text,"
                + "BAIRRO" + " text,"
                + "CDCIDADE" + " text,"
                + "CEP" + " text,"
                + "NRTELEFONE" + " text,"
                + "RAMALTELEFONE" + " text,"
                + "NRCELULAR" + " text,"
                + "NRFAX" + " text,"
                + "ENDEMAIL" + " text,"
                + "HOMEPAGE" + " text,"
                + "RGINSCEST" + " text,"
                + "INSCRMUN" + " text"
                + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
