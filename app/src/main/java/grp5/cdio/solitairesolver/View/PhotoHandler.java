package grp5.cdio.solitairesolver.View;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.Fragments.Resultat;

public class PhotoHandler implements PictureCallback {
    private final Context context;
    public static String lastPicture = null;

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

        try {
            Log.d("PhotoHandler", "Image saving to: " + filename);
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            lastPicture = filename;
            Toast.makeText(context, "New Image saved:" + photoFile, Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Log.d("PhotoHandler", "File" + filename + "not saved: " + error.getMessage());
            lastPicture = null;
            Toast.makeText(context, "Image could not be saved.", Toast.LENGTH_LONG).show();
        }
    }

    public File getDir() {
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
}
