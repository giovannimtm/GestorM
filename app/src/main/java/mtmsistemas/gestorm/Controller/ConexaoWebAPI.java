package mtmsistemas.gestorm.Controller;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import mtmsistemas.gestorm.Model.EMFSESSION;
import mtmsistemas.gestorm.Model.PARAMETROS;

/**
 * Created by Giovanni on 29/11/2017.
 */

public class ConexaoWebAPI {

    public static JSONObject FU_ClassToJsonOBJ(Object CLS_CLASSE) {
        JSONObject LJSN_RETOBJECT = null;
        String LSTR_JSON;
        try {
            LJSN_RETOBJECT = new JSONObject();
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                    .serializeNulls()
                    .create();
            LSTR_JSON = gson.toJson(CLS_CLASSE);
            LJSN_RETOBJECT = new JSONObject(LSTR_JSON);

            return LJSN_RETOBJECT;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LJSN_RETOBJECT;
    }

    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
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

    private static StringBuffer FU_WB_CONECTA(Object CLS_CLASSE, String STR_METODO, String STR_REQUETMETHOD, int IDOBJETO) {
        URL LUR_URL = null;
        HttpURLConnection conn = null;
        OutputStream LOS_OUTPUTSTREAM = null;
        BufferedWriter LBUF_BUFFEREDWRITER = null;
        BufferedReader LBUF_READER = null;
        StringBuffer LSTB_BUFFER = null;
        String LSTR_LINHA = "";
        int LINT_RESPOSTACODE = 0;

        try {
            if (EMFSESSION.LOCAL_IDSESSION == 0 && STR_METODO.toUpperCase().contains("AUTENTICA")) {
                LUR_URL = new URL(PARAMETROS.PSTR_ENDERECOWEBAPI
                        + ":" + PARAMETROS.PSTR_PORTA
                        + "/" + PARAMETROS.PSTR_API
                        + "/" + STR_METODO); // here is your URL path
            } else if (IDOBJETO > 0) {
                LUR_URL = new URL(PARAMETROS.PSTR_ENDERECOWEBAPI
                        + ":" + PARAMETROS.PSTR_PORTA
                        + "/" + PARAMETROS.PSTR_API
                        + "/" + STR_METODO
                        + "/" + EMFSESSION.LOCAL_IDSESSION
                        + "/" + IDOBJETO
                ); // here is your URL path
            } else {
                LUR_URL = new URL(PARAMETROS.PSTR_ENDERECOWEBAPI
                        + ":" + PARAMETROS.PSTR_PORTA
                        + "/" + PARAMETROS.PSTR_API
                        + "/" + STR_METODO
                        + "/" + EMFSESSION.LOCAL_IDSESSION
                        + "/"); // here is your URL path
            }
            conn = (HttpURLConnection) LUR_URL.openConnection();
//            conn.setReadTimeout(105000 /* milliseconds */);
//            conn.setConnectTimeout(105000 /* milliseconds */);
            conn.setConnectTimeout(5 * 1000 * 3600); //set time out
            conn.setReadTimeout(5 * 1000 * 3600);   // set socket time out
            conn.setRequestMethod(STR_REQUETMETHOD);
            conn.setDoInput(true);

            if (STR_REQUETMETHOD.toString().toUpperCase() == "POST" || STR_REQUETMETHOD.toString().toUpperCase() == "PUT") {
                conn.setDoOutput(true);
                LOS_OUTPUTSTREAM = conn.getOutputStream();
                LBUF_BUFFEREDWRITER = new BufferedWriter(new OutputStreamWriter(LOS_OUTPUTSTREAM, "UTF-8"));
                LBUF_BUFFEREDWRITER.write(getPostDataString(FU_ClassToJsonOBJ(CLS_CLASSE)));

                LBUF_BUFFEREDWRITER.flush();
                LBUF_BUFFEREDWRITER.close();
                LOS_OUTPUTSTREAM.close();

            }
            conn.connect();


            LINT_RESPOSTACODE = conn.getResponseCode();
            if (LINT_RESPOSTACODE == HttpsURLConnection.HTTP_OK) {
                LBUF_READER = new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                LSTB_BUFFER = new StringBuffer("");

                while ((LSTR_LINHA = LBUF_READER.readLine()) != null) {
                    LSTB_BUFFER.append(LSTR_LINHA);
                    break;
                }
                LBUF_READER.close();

            } else {
                if (LINT_RESPOSTACODE == 500) {
                    throw new Exception("Não foi possivel completar a operação, certifique-se de que esteja autenticado,Lado Servidor");
                }
                // else {throw new Exception ("false : "+ LINT_RESPOSTACODE);}
            }

            String message = conn.getResponseMessage();

            return LSTB_BUFFER;
        } catch (OutOfMemoryError oomer) {
            Log.e("ERRO", "Não foi possível converter o arquivo de vídeo para a transmissão. OutOfMemoryError. ", oomer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            LUR_URL = null;
            conn = null;
            LOS_OUTPUTSTREAM = null;
            LBUF_BUFFEREDWRITER = null;
            LBUF_READER = null;
            LSTR_LINHA = "";

        }
        return LSTB_BUFFER;
    }

    public static Object FU_WB_EXECUTA(Object CLS_CLASSE, String STR_METODO, String STR_REQUETMETHOD, int IDOBJETO) {
        Gson LGS_JSON = null;
        Object LOBJ_RETORNO = null;

        try {

            LGS_JSON = new Gson();
            LOBJ_RETORNO = LGS_JSON.fromJson(FU_WB_CONECTA(CLS_CLASSE
                    , STR_METODO
                    , STR_REQUETMETHOD, IDOBJETO).toString()
                    , CLS_CLASSE.getClass());

            return LOBJ_RETORNO;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        } finally {
            LGS_JSON = null;
        }
    }

    public static StringBuffer FU_WB_AOBJECT(Object CLS_CLASSE, String STR_METODO, String STR_REQUETMETHOD, int IDOBJETO) {
        StringBuffer LOBJ_RETORNO = null;
        Gson LGS_JSON = null;

        try {
            LOBJ_RETORNO = FU_WB_CONECTA(CLS_CLASSE, STR_METODO, STR_REQUETMETHOD, IDOBJETO);

            return LOBJ_RETORNO;
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
        }
        return LOBJ_RETORNO;
    }


    public static Boolean FU_WB_TestaConexao() throws IOException {
        URL url = new URL(PARAMETROS.PSTR_ENDERECOWEBAPI
                + ":" + PARAMETROS.PSTR_PORTA
                + "/" + PARAMETROS.PSTR_API
                + "/" + "gestoricl/status/"
                + "/" + "8021"
                + ""); // here is your URL path

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "yourAgent");
        connection.setRequestProperty("Connection", "close");
        connection.setConnectTimeout(1000);
        connection.connect();

        return connection.getResponseCode() == 200;

    }
    public static void FU_WB_Arquivo() {
        String fileName = Environment.getExternalStorageDirectory() + "/video.mp4";

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
//        int maxBufferSize = 1 * 1024 * 1024 * bufferSize;//10MB
        int maxBufferSize = 1 * 1024 * 1024 *2 ;//10MB
        File sourceFile = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL("http://192.168.0.103:8021/GiclPLibWebAPI/api/TIPOCOMPONENTE/UPLOAD");
//          URL url = new  URL(PARAMETROS.PSTR_ENDERECOWEBAPI
//                    + ":" + PARAMETROS.PSTR_PORTA
//                    + "/" + PARAMETROS.PSTR_API
//                    + "/" + STR_METODO
//                    + "/" + EMFSESSION.LOCAL_IDSESSION
//                    + "/");

            // Open a HTTP  connection to  the URL
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + fileName + "\"" + lineEnd);

            dos.writeBytes(lineEnd);

            // create a buffer of  maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            int serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();

            Log.i("uploadFile", "HTTP Response is : "
                    + serverResponseMessage + ": " + serverResponseCode);

        } catch (OutOfMemoryError oomer) {
            Log.e("ERRO", "Não foi possível converter o arquivo de vídeo para a transmissão. OutOfMemoryError. ", oomer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {


        }

    }

//    public static void FU_WB_Arquivo() {
//        String fileName = Environment.getExternalStorageState() + "/foto.jpg";
//
//        HttpURLConnection conn = null;
//        DataOutputStream dos = null;
//        String lineEnd = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//        int bytesRead, bytesAvailable, bufferSize;
//        byte[] buffer;
//        int maxBufferSize = 1 * 1024 * 1024;
//        File sourceFile = new File(Environment.getExternalStorageDirectory() + "/foto.jpg");
//        try {
//
//            FileInputStream fileInputStream = new FileInputStream(sourceFile);
//            URL url = new URL("http://192.168.0.103:8021/GiclPLibWebAPI/api/TIPOCOMPONENTE/UPLOAD");
//
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setUseCaches(false);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
//            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//            //conn.setRequestProperty("upload_file", fileName);
//            conn.setRequestProperty("file", fileName);
//
//            dos = new DataOutputStream(conn.getOutputStream());
//
//            dos.writeBytes(twoHyphens + boundary + lineEnd);
//            dos.writeBytes("Content-Disposition: form-data; uploadedInputStream=\"uploaded_file\";fileDetail=\"" + fileName + "\"" + lineEnd);
//
//            dos.writeBytes(lineEnd);
//
//            bytesAvailable = fileInputStream.available();
//
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            buffer = new byte[bufferSize];
//
//            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//            while (bytesRead > 0) {
//
//                dos.write(buffer, 0, bufferSize);
//                bytesAvailable = fileInputStream.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//            }
//
//            dos.writeBytes(lineEnd);
//            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//            int serverResponseCode = conn.getResponseCode();
//            String serverResponseMessage = conn.getResponseMessage();
//
//            fileInputStream.close();
//            dos.flush();
//            dos.close();
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception ex) {
//            try {
//                throw new Exception(ex.getMessage());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } finally {
//
//
//        }
//
//    }

}


//    public String FU_WebAPI(String STR_url){
//
//    String retorno = "";
//        try {
//        URL apiEnd = new URL(STR_url);
//        int INT_codigoResposta;
//        HttpURLConnection conexao;
//        InputStream is;
//
//        conexao = (HttpURLConnection) apiEnd.openConnection();
//        conexao.setRequestMethod("GET");
//        conexao.setReadTimeout(15000);
//        conexao.setConnectTimeout(15000);
//        conexao.connect();
//
//        INT_codigoResposta = conexao.getResponseCode();
//        if(INT_codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
//            is = conexao.getInputStream();
//        }else{
//            is = conexao.getErrorStream();
//        }
//
//        retorno = converterInputStreamToString(is);
//        is.close();
//        conexao.disconnect();
//
//
//    } catch (MalformedURLException e) {
//        e.printStackTrace();
//    }catch (IOException e){
//        e.printStackTrace();
//    }
//
//        return retorno;
//}
//
//    private String converterInputStreamToString(InputStream is){
//        StringBuffer LSB_BUFFER = null;
//        BufferedReader LBR_BUFFERREADER = null;
//        String STR_linha = "";
//
//        try{
//            LSB_BUFFER = new StringBuffer();
//            LBR_BUFFERREADER = new BufferedReader(new InputStreamReader(is));
//
//            while((STR_linha = LBR_BUFFERREADER.readLine())!=null){
//                LSB_BUFFER.append(STR_linha);
//            }
//
//            LBR_BUFFERREADER.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//        return LSB_BUFFER.toString();
//    }
