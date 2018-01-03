package mtmsistemas.gestorm.Util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.format.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.regex.Pattern;

import mtmsistemas.gestorm.R;

/**
 * Created by Giovanni on 19/10/2017.
 */

public class ClsUtil{

    public static  String caminhoApp;

    public boolean FU_dataAnteriorAHoje(String data)
    {
        Boolean validou;
        try
        {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formatador.parse(data);

            Date hoje = now.getTime();
            validou = dataFormatada.before(hoje);
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            validou = false;
        }
        return validou;
    }

    public boolean FU_validaPlaca(String placa)
    {
        return Pattern.matches("[A-Z]{3}-[0-9]{4}",placa);
    }

    public byte[] FU_converteArquivoParaArrayBytes(String caminho)
    {
        byte[] imagem = null;
        try
        {
            FileInputStream fis = new FileInputStream(caminho);
            imagem = new byte[fis.available()];
            fis.read(imagem);
            fis.close();
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return imagem;
    }

    public Bitmap FU_converteArrayBytesParaImagem(byte[] byteArray)
    {
        Bitmap imagem = null;
        try
        {
             imagem = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return imagem;
    }

    public String FU_salvaByteArrayComoArquivoERetornaCaminho(byte[] arquivo, String nomeArquivo)
    {
        //exemplo do nome do arquivo com a pasta "teste\\logo.png"
        String caminho = null;
        try
        {
            FileOutputStream out;
            File file;

            try
            {
                out = new FileOutputStream(caminhoApp + "/" + nomeArquivo);
                caminho = caminhoApp + "/" + nomeArquivo;
                out.write(arquivo);
                out.close();
            }catch (IOException e)
            {
                if (FU_podeLerEEscreverMidiaExterna())
                {
                    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), nomeArquivo);
                    out = new FileOutputStream(file.getPath());
                    caminho = file.getPath();
                }
                else
                {
                    out = new FileOutputStream(nomeArquivo);
                    caminho = null;
                }
                out.write(arquivo);
                out.close();
            }
        }catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return caminho;
    }

    public boolean FU_podeLerEEscreverMidiaExterna() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) && Environment.MEDIA_MOUNTED_READ_ONLY.equals(state) == false);
    }

    //Para a função abaixo funcionar é preciso extender a activity para a classe
//    public String FU_retornaCaminhoImagem(Resources objeto, Integer idImagem)
//    {
//        Bitmap bitMap = BitmapFactory.decodeResource(objeto, idImagem);
//
//        String fileName ="img1.jpg";
//        String mPath = "";
//        try {
//
//            FileOutputStream out1 = openFileOutput(fileName, Context.MODE_PRIVATE);
//
//            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, out1);
//
//            out1.flush();
//
//            out1.close();
//
//            File f = getFileStreamPath(fileName);
//
//            mPath = f.getAbsolutePath();
//
//        } catch (FileNotFoundException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return mPath;
//    }

}
