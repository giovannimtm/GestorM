package mtmsistemas.gestorm.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import mtmsistemas.gestorm.Model.EMFSESSION;
import mtmsistemas.gestorm.Model.EQUIPAMENTO;

/**
 * Created by Giovanni on 30/11/2017.
 */

public class EquipamentoRestClient {

    public EQUIPAMENTO getInformacao(String end){
        String json;
        EQUIPAMENTO retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJson(json);

        return retorno;
    }

    private EQUIPAMENTO parseJson(String json){
        try {
            EQUIPAMENTO pessoa = new EQUIPAMENTO(null);

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data;

            JSONObject objArray = array.getJSONObject(0);

            JSONObject obj = objArray.getJSONObject("user");
            //Atribui os objetos que estão nas camadas mais altas
            pessoa.setIDEQUIPAMENTO(obj.getString("IDEQUIPAMENTO"));
            pessoa.setDSEQUIPAMENTO(obj.getString("DSEQUIPAMENTO"));
//            pessoa.setSenha(obj.getString("password"));
//            pessoa.setTelefone(obj.getString("phone"));
//            data = new Date(obj.getLong("dob")*1000);
//            pessoa.setNascimento(sdf.format(data));

//            //Nome da pessoa é um objeto, instancia um novo JSONObject
//            JSONObject nome = obj.getJSONObject("name");
//            pessoa.setNome(nome.getString("first"));
//            pessoa.setSobrenome(nome.getString("last"));
//
//            //Endereco tambem é um Objeto
//            JSONObject endereco = obj.getJSONObject("location");
//            pessoa.setEndereco(endereco.getString("street"));
//            pessoa.setEstado(endereco.getString("state"));
//            pessoa.setCidade(endereco.getString("city"));

//            //Imagem eh um objeto
//            JSONObject foto = obj.getJSONObject("picture");
//            pessoa.setFoto(baixarImagem(foto.getString("large")));

            return pessoa;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static JSONObject FU_ClassToJsonOBJ(Object CLS_CLASSE)
    {
        JSONObject LJSN_RETOBJECT = new JSONObject();

        try{
//            Car audi = new Car("Audi", "A4", 1.8);
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                    .serializeNulls()
                    .create();
            String json = gson.toJson(CLS_CLASSE);
            LJSN_RETOBJECT = new JSONObject(json);


//        for(LINT_COUNT = 0 ; LFID_VARIAVEIS.length > LINT_COUNT; LINT_COUNT++)
//        {
//            LFID_VARIAVEIS[LINT_COUNT].getName();
//
//            for(int LINT_METODO=0; LMET_METODOS.length > LINT_METODO;LINT_METODO++)
//            {
//                if(LMET_METODOS[LINT_METODO].getName().toUpperCase().equals("GET_"+LFID_VARIAVEIS[LINT_COUNT].getName().toUpperCase()))
//                {
//                    LJSN_RETOBJECT.put(LFID_VARIAVEIS[LINT_COUNT].getName().toString() ,
//                            LMET_METODOS[LINT_METODO].getDefaultValue());
//                }
//                }
//            }
            return LJSN_RETOBJECT;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LJSN_RETOBJECT;
    }


public static Object FU_WebService(Object CLS_CLASSE, String STR_REQUETMETHOD){

    try {
        URL url = new URL("http://192.168.0.104:8021/GiclPLibWebAPI/api/gestoricl/autentica"); // here is your URL path
//        URL url = new URL("http://192.168.43.122:8021/GiclPLibWebAPI/api/gestoricl/autentica"); // here is your URL path

        JSONObject postDataParams = new JSONObject();
        Log.e("params",postDataParams.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(105000 /* milliseconds */);
        conn.setConnectTimeout(105000 /* milliseconds */);
        conn.setRequestMethod(STR_REQUETMETHOD);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        //writer.write(getPostDataString(postDataParams));
        writer.write(getPostDataString(FU_ClassToJsonOBJ(CLS_CLASSE)));
        writer.flush();
        writer.close();
        os.close();
        EMFSESSION LCLS_EMFSESSION = new EMFSESSION(null);
        int responseCode=conn.getResponseCode();
        Gson gson = new Gson();

        if (responseCode == HttpsURLConnection.HTTP_OK) {

            BufferedReader in=new BufferedReader(new
                    InputStreamReader(
                    conn.getInputStream()));

            StringBuffer sb = new StringBuffer("");
            String line="";

            while((line = in.readLine()) != null) {

                sb.append(line);
                LCLS_EMFSESSION = gson.fromJson(sb.toString(),EMFSESSION.class);

                break;
            }
//// String contendo o JSON
//            String json = "{ bool:true, integer:1, string:\"json\" }";
//
//// Obtém o JSONObject da string JSON
//            JSONObject jsonObject = JSONObject.fromObject(json);
//
//// Obtém o objeto do JSONObject
//            BeanA bean = (BeanA) JSONObject.toBean(jsonObject, BeanA.class);
            in.close();
            return LCLS_EMFSESSION;

        }
        else {
            return new String("false : "+responseCode);
        }
    }
    catch(Exception e){
        return new String("Exception: " + e.getMessage());
    }
}
    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


    public static List<EQUIPAMENTO> FU_LIST_EQUIPAMENTO() throws Exception {
        Gson LGS_JSON = null;
        EQUIPAMENTO[] LCLS_EQUIPAMENTO = null;
        String LOBJ_Retorno=null;

        try{

            LOBJ_Retorno = ConexaoWebAPI.FU_WB_AOBJECT(
                    null,
                    "equipamento",
                    "GET").toString();
            LGS_JSON = new Gson();
            LCLS_EQUIPAMENTO = LGS_JSON.fromJson(LOBJ_Retorno.toString()
                                                ,EQUIPAMENTO[].class);

            if(LCLS_EQUIPAMENTO != null)
            {

            }
        }catch (Exception ex){
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        finally {

        }
        return Arrays.asList(LCLS_EQUIPAMENTO);
    }
}
