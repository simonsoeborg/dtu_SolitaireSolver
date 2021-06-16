package grp5.cdio.solitairesolver.View;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import grp5.cdio.solitairesolver.Controller.Controller;
import grp5.cdio.solitairesolver.Service.ObjectDetection.ObjectDetection;
import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.Fragments.CardControl;

public class PhotoHandler implements PictureCallback {
    private final Context context;
    public String lastPicture = null;
    public Bitmap originalPic;

    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        File pictureFileDir = getDir();

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
            Log.d("PhotoHandler", "Can't create directory to save image.");
            Toast.makeText(context, "Can't create directory to save image.", Toast.LENGTH_LONG).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        // Todo - kan nok effektiviseres således at billedet ikke behøver blive gemt før det slettes.
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            // lastPicture = filename;
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inMutable = true;
            originalPic = BitmapFactory.decodeFile(filename, opt);
            //pictureFile.delete();
            Toast.makeText(context, "New Image er taget, passed og slettet igen:" + photoFile, Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Log.d("PhotoHandler", "File" + filename + "not saved: " + error.getMessage());
            lastPicture = null;
            Toast.makeText(context, "Image could not be saved.", Toast.LENGTH_LONG).show();
        }

        Controller con = Controller.getInstance();
        HashMap<String, Bitmap> list = splitImg(lastPicture, originalPic);
        saveSplitImg(list);
        con.loadCards(context, list);

        try {
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.FragmentFL, new CardControl())
                    .addToBackStack(null)
                    .commit();

        } catch (ClassCastException e) {
            Log.d("fragManager", "Can't get the fragment manager with this");
        }
    }

    private File getDir() {
        String path = "CameraAPIDemo";
        File file;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            file = new File(context.getExternalFilesDir(null) + path);
        } else {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path);
        }
        Log.d("PhotoHandler", "Saving image to: " + file.getAbsolutePath());

        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private HashMap<String, Bitmap> splitImg(String filename, Bitmap originalPic){
        // Hvis billedet af en eller anden grund ikke er mutable, lav en kopi af den selv
        if (!originalPic.isMutable()) {
            originalPic = originalPic.copy(Bitmap.Config.ARGB_8888, true);
        }
        Bitmap background = null;
        //Bitmap originalPic = Bitmap.createScaledBitmap(orgPic, 1920, 360, false);
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("blackBackground.jpg");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            background = BitmapFactory.decodeStream(bufferedInputStream);
        } catch (IOException e){
            System.out.println("no picture found");
        }

        //  Bitmap orginialPic = BitmapFactory.decodeFile(filename);
        HashMap<String, Bitmap> piles = new HashMap<>();
        Bitmap bmOverlayDraw =  Bitmap.createBitmap(background.getWidth()/3, background.getHeight()/3, background.getConfig()); // 1/3 bredde af 1920 er nok
        Bitmap bmOverlayFound = Bitmap.createBitmap(background.getWidth()/2, background.getHeight()/2, background.getConfig()); // halv bredde af 1920 er nok
        Bitmap bmOverlayBuild = Bitmap.createBitmap(background.getWidth(), background.getHeight(), background.getConfig()); // Fuld bredde
        Bitmap bmOverlayTotal = Bitmap.createBitmap(background.getWidth(), background.getHeight(), background.getConfig()); // Fuld bredde


        Bitmap drawPile = Bitmap.createBitmap(originalPic, 0, 0, originalPic.getWidth() * 3 / 8, (originalPic.getHeight() / 3));
        Canvas canvasDraw = new Canvas(bmOverlayDraw);
        canvasDraw.drawBitmap(background, new Matrix(), null);
        canvasDraw.drawBitmap(drawPile, 0, 0, null);
        piles.put(ObjectDetection.DRAW_PILE, bmOverlayDraw);

        Bitmap foundationPile = Bitmap.createBitmap(originalPic, originalPic.getWidth() - originalPic.getWidth() * 5 / 8, 0, originalPic.getWidth() * 4 / 8, (originalPic.getHeight() / 3));
        Canvas canvasFoundation = new Canvas(bmOverlayFound);
        canvasFoundation.drawBitmap(background, new Matrix(), null);
        canvasFoundation.drawBitmap(foundationPile, 0, 0, null);
        piles.put(ObjectDetection.GROUND_PILE, bmOverlayFound);

        Bitmap buildPile = Bitmap.createBitmap(originalPic, 0, originalPic.getHeight() / 3, originalPic.getWidth() * 7 / 8, (originalPic.getHeight() * 2 / 3));
        Canvas canvasBuild = new Canvas(bmOverlayBuild);
        canvasBuild.drawBitmap(background, new Matrix(), null);
        canvasBuild.drawBitmap(buildPile, 0, 0, null);
        piles.put(ObjectDetection.BUILD_PILE, bmOverlayBuild);

        Canvas canvasFull = new Canvas(bmOverlayTotal);
        canvasFull.drawBitmap(background, new Matrix(), null);
        canvasFull.drawBitmap(originalPic, 0, 0, null);
        piles.put("total", bmOverlayTotal);
        return piles;
        }

    // for at gamme vores delte billeder.
    private void saveSplitImg(HashMap<String, Bitmap>  map) {
        Collection<Bitmap> values = map.values();
        ArrayList<Bitmap> piles = new ArrayList<>(values);
        for (int i = 0; i < piles.size(); i++) {
            File pictureFileDir = getDir();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = dateFormat.format(new Date());
            String photoFile = "Picture_" + date + "_" + i + ".jpg";

            String filename = pictureFileDir.getPath() + File.separator + photoFile;

            File pictureFile = new File(filename);

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                piles.get(i).compress(Bitmap.CompressFormat.PNG, 90, fos);
                lastPicture = filename;
                Toast.makeText(context, "New Image saved:" + photoFile, Toast.LENGTH_LONG).show();
            } catch (Exception error) {
                Log.d("PhotoHandler", "File" + filename + "not saved: " + error.getMessage());
                lastPicture = null;
                Toast.makeText(context, "Image could not be saved.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
