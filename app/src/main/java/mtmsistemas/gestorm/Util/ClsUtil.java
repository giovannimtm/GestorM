package mtmsistemas.gestorm.Util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.regex.Pattern;

import mtmsistemas.gestorm.Activity.ACT_CheckList;
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
                if (FU_podeEscreverMidiaExterna())
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

    public boolean FU_podeEscreverMidiaExterna() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            return true;
        else
            return false;

    }

    public void FU_redimensionaImagemEColocaNaView(String caminho, ImageView view, int altura, int largura) throws IOException {
        // Get the dimensions of the View
        int targetW = view.getWidth();
        if (targetW == 0)
            targetW = largura;
        int targetH = view.getHeight();
        if (targetH == 0)
            targetH = altura;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(caminho, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(caminho, bmOptions);

        bitmap = FU_correctOrientation(bitmap, caminho);

        view.setImageBitmap(bitmap);
    }

    public static Bitmap FU_correctOrientation(Bitmap bitmap, String caminho) throws IOException {
        ExifInterface exif = new ExifInterface(caminho);
        int orientation = ExifInterface.ORIENTATION_NORMAL;
        orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                bitmap = rotateBitmap(bitmap, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                bitmap = rotateBitmap(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                bitmap = rotateBitmap(bitmap, 270);
                break;
        }
        return bitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void FU_permissoes(Context context, Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

            if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }

            if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 1);
            }
        }
    }

    public static Dialog FU_imageDialog(Context context, String caminho){
        final Dialog nagDialog = new Dialog(context,android.R.style.Theme_NoTitleBar_OverlayActionModes);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setCancelable(false);
        nagDialog.setContentView(R.layout.dialog_visualizar_foto);
        Button btnClose = (Button)nagDialog.findViewById(R.id.btnIvClose);
        ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
        Bitmap bitmap = BitmapFactory.decodeFile(caminho);
        try {
            bitmap = FU_correctOrientation(bitmap, caminho);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivPreview.setImageBitmap(bitmap);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                nagDialog.dismiss();
            }
        });
        return nagDialog;
    }

    public static Intent FU_visualizarFotosDaPasta(Context context, String pasta){
        String bucketId = "";

        final String[] projection = new String[] {"DISTINCT " + MediaStore.Images.Media.BUCKET_DISPLAY_NAME + ", " + MediaStore.Images.Media.BUCKET_ID};
        final Cursor cur = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        while (cur != null && cur.moveToNext()) {
            final String bucketName = cur.getString((cur.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)));
            if (bucketName.equals(pasta)) {
                bucketId = cur.getString((cur.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_ID)));
                break;
            }
        }
        Uri mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        mediaUri = mediaUri.buildUpon()
                .authority("media")
                .appendQueryParameter("bucketId", bucketId)
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW, mediaUri);
        return intent;
    }

    public static File FU_criaArquivoImagemNaPAsta(String nomePasta){
        String caminhoPasta = Environment.getExternalStorageDirectory() + "/DCIM/Gestor/CheckList/" + nomePasta;
        File pasta = new File(caminhoPasta);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss");
        String nome = s.format(new Date()) + ".jpg";
        File arquivo = new File(caminhoPasta, nome);
        return arquivo;
    }

    public static Intent FU_salvaFotoPastaEspecifica(File foto){
        Uri relativePath = Uri.fromFile(foto);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, relativePath);
        return intent;
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
