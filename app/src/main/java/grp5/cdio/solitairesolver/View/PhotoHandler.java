package grp5.cdio.solitairesolver.View;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.Fragments.CardControl;

public class PhotoHandler implements PictureCallback {
    private final Context context;
    public String lastPicture = null;
    public Bitmap orginialPic;

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
            orginialPic = BitmapFactory.decodeFile(filename);
            pictureFile.delete();
             Toast.makeText(context, "New Image er taget, passed og slettet igen:" + photoFile, Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Log.d("PhotoHandler", "File" + filename + "not saved: " + error.getMessage());
            lastPicture = null;
            Toast.makeText(context, "Image could not be saved.", Toast.LENGTH_LONG).show();
        }

        saveSplitImg(splitImg(lastPicture, orginialPic));

        try{
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
            file = new File (context.getExternalFilesDir(null) + path);
        } else {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path);
        }
        Log.d("PhotoHandler", "Saving image to: " + file.getAbsolutePath());

        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private ArrayList<Bitmap> splitImg(String filename, Bitmap orginialPic){

      //  Bitmap orginialPic = BitmapFactory.decodeFile(filename);

        ArrayList<Bitmap> piles =  new ArrayList<>();

        // Todo : Der skal specificeres de rigtige x og y kordinater (første pixel), og relevante længder. pt er de bare randome.

        Bitmap foundationPile = Bitmap.createBitmap(orginialPic, orginialPic.getWidth()-orginialPic.getWidth()*5/8, 0, orginialPic.getWidth()*4/8, (orginialPic.getHeight()/3 ));
        piles.add(foundationPile);

        Bitmap drawPile = Bitmap.createBitmap(orginialPic, 0, 0, orginialPic.getWidth()*3/8, (orginialPic.getHeight()/3 ));
        piles.add(drawPile);

        Bitmap buildPile = Bitmap.createBitmap(orginialPic, 0, orginialPic.getHeight()/3, orginialPic.getWidth()*7/8, (orginialPic.getHeight()*2/3 ));
        piles.add(buildPile);

        return  piles;
    }

    // for at gamme vores delte billeder.
    private void saveSplitImg(ArrayList<Bitmap> piles){

        for (int i = 0; i < piles.size(); i++) {
            File pictureFileDir = getDir();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = dateFormat.format(new Date());
            String photoFile = "Picture_" + date + "_"+ i +".jpg";

            String filename = pictureFileDir.getPath() + File.separator + photoFile;

            File pictureFile = new File(filename);

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                piles.get(i).compress(Bitmap.CompressFormat.PNG, 90,fos);
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
