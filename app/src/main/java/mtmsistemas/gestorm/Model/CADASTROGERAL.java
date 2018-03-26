package mtmsistemas.gestorm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CADASTROGERAL extends SQLiteOpenHelper {

    public static String TABLE = "CADASTROGERAL";
    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE ;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE + "/delete";

    private Object _CDCADASTRO = null;
    private Object _CGCCPF = null;
    private Object _FGACTIVE = null;
    private Object _FGFISICAJURIDICA = null;
    private Object _RZSOCIAL = null;
    private Object _NMFANTASIA = null;
    private Object _ENDERECO = null;
    private Object _NRENDERECO = null;
    private Object _COMPLEND = null;
    private Object _BAIRRO = null;
    private Object _CDCIDADE = null;
    private Object _CEP = null;
    private Object _NRTELEFONE = null;
    private Object _RAMALTELEFONE = null;
    private Object _NRCELULAR = null;
    private Object _NRFAX = null;
    private Object _ENDEMAIL = null;
    private Object _HOMEPAGE = null;
    private Object _RGINSCEST = null;
    private Object _INSCRMUN = null;
    private Object _IDSINCRINIZA = null;

    public Object get_IDSINCRINIZA() {
        return _IDSINCRINIZA;
    }

    public void set_IDSINCRINIZA(Object _IDSINCRINIZA) {
        this._IDSINCRINIZA = _IDSINCRINIZA;
    }

    public CADASTROGERAL(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
    }

    public Object get_CDCADASTRO() {
        return _CDCADASTRO;
    }

    public void set_CDCADASTRO(Object _CDCADASTRO) {
        this._CDCADASTRO = _CDCADASTRO;
    }

    public Object get_CGCCPF() {
        return _CGCCPF;
    }

    public void set_CGCCPF(Object _CGCCPF) {
        this._CGCCPF = _CGCCPF;
    }

    public Object get_FGACTIVE() {
        return _FGACTIVE;
    }

    public void set_FGACTIVE(Object _FGACTIVE) {
        this._FGACTIVE = _FGACTIVE;
    }

    public Object get_FGFISICAJURIDICA() {
        return _FGFISICAJURIDICA;
    }

    public void set_FGFISICAJURIDICA(Object _FGFISICAJURIDICA) {
        this._FGFISICAJURIDICA = _FGFISICAJURIDICA;
    }

    public Object get_RZSOCIAL() {
        return _RZSOCIAL;
    }

    public void set_RZSOCIAL(Object _RZSOCIAL) {
        this._RZSOCIAL = _RZSOCIAL;
    }

    public Object get_NMFANTASIA() {
        return _NMFANTASIA;
    }

    public void set_NMFANTASIA(Object _NMFANTASIA) {
        this._NMFANTASIA = _NMFANTASIA;
    }

    public Object get_ENDERECO() {
        return _ENDERECO;
    }

    public void set_ENDERECO(Object _ENDERECO) {
        this._ENDERECO = _ENDERECO;
    }

    public Object get_NRENDERECO() {
        return _NRENDERECO;
    }

    public void set_NRENDERECO(Object _NRENDERECO) {
        this._NRENDERECO = _NRENDERECO;
    }

    public Object get_COMPLEND() {
        return _COMPLEND;
    }

    public void set_COMPLEND(Object _COMPLEND) {
        this._COMPLEND = _COMPLEND;
    }

    public Object get_BAIRRO() {
        return _BAIRRO;
    }

    public void set_BAIRRO(Object _BAIRRO) {
        this._BAIRRO = _BAIRRO;
    }

    public Object get_CDCIDADE() {
        return _CDCIDADE;
    }

    public void set_CDCIDADE(Object _CDCIDADE) {
        this._CDCIDADE = _CDCIDADE;
    }

    public Object get_CEP() {
        return _CEP;
    }

    public void set_CEP(Object _CEP) {
        this._CEP = _CEP;
    }

    public Object get_NRTELEFONE() {
        return _NRTELEFONE;
    }

    public void set_NRTELEFONE(Object _NRTELEFONE) {
        this._NRTELEFONE = _NRTELEFONE;
    }

    public Object get_RAMALTELEFONE() {
        return _RAMALTELEFONE;
    }

    public void set_RAMALTELEFONE(Object _RAMALTELEFONE) {
        this._RAMALTELEFONE = _RAMALTELEFONE;
    }

    public Object get_NRCELULAR() {
        return _NRCELULAR;
    }

    public void set_NRCELULAR(Object _NRCELULAR) {
        this._NRCELULAR = _NRCELULAR;
    }

    public Object get_NRFAX() {
        return _NRFAX;
    }

    public void set_NRFAX(Object _NRFAX) {
        this._NRFAX = _NRFAX;
    }

    public Object get_ENDEMAIL() {
        return _ENDEMAIL;
    }

    public void set_ENDEMAIL(Object _ENDEMAIL) {
        this._ENDEMAIL = _ENDEMAIL;
    }

    public Object get_HOMEPAGE() {
        return _HOMEPAGE;
    }

    public void set_HOMEPAGE(Object _HOMEPAGE) {
        this._HOMEPAGE = _HOMEPAGE;
    }

    public Object get_RGINSCEST() {
        return _RGINSCEST;
    }

    public void set_RGINSCEST(Object _RGINSCEST) {
        this._RGINSCEST = _RGINSCEST;
    }

    public Object get_INSCRMUN() {
        return _INSCRMUN;
    }

    public void set_INSCRMUN(Object _INSCRMUN) {
        this._INSCRMUN = _INSCRMUN;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE + "("
                + "_CDCADASTRO" + " integer primary key autoincrement,"
                + "_FGACTIVE" + " text,"
                + "_FGFISICAJURIDICA" + " text,"
                + "_RZSOCIAL" + " text,"
                + "_NMFANTASIA" + " text,"
                + "_ENDERECO" + " text,"
                + "_NRENDERECO" + " text,"
                + "_COMPLEND" + " text,"
                + "_BAIRRO" + " text,"
                + "_CDCIDADE" + " text,"
                + "_CEP" + " text,"
                + "_NRTELEFONE" + " text,"
                + "_RAMALTELEFONE" + " text,"
                + "_NRCELULAR" + " text,"
                + "_NRFAX" + " text,"
                + "_ENDEMAIL" + " text,"
                + "_HOMEPAGE" + " text,"
                + "_RGINSCEST" + " text,"
                + "_INSCRMUN" + " text"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
