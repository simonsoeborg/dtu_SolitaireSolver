package grp5.cdio.solitairesolver.View.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;


import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.CameraPreview;
import grp5.cdio.solitairesolver.View.PhotoHandler;

public class CameraFragment extends Fragment  {
    private Camera mCamera;
    private CameraPreview mPreview;
    private static final int MY_CAMERA_PERMISSION_CODE = 42069;
    private Resultat resultat = new Resultat();

    AsyncTask<?, ?, ?> runningTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View cameraFrag = inflater.inflate(R.layout.fragment_camera, container, false);
        View  resultFrag= inflater.inflate(R.layout.fragment_resultat, container, false);

        Context context = cameraFrag.getContext();

        // dette er den visuelle grid layout
        View constraintlayout = cameraFrag.findViewById(R.id.constraintlayout);


        if (!checkCameraHardware(context)) {
            Toast.makeText(context ,"Camera does not exist", Toast.LENGTH_LONG).show();
        } else if (!checkCameraAccess(context)) {
            Toast.makeText(context ,"Missing rights, please grant", Toast.LENGTH_LONG).show();
        } else {
            mCamera = getCameraInstance();
            mPreview = new CameraPreview(context, mCamera);
            FrameLayout preview = (FrameLayout) cameraFrag.findViewById(R.id.camera_preview);
            preview.addView(mPreview);

            // Add a listener to the Capture button
            ImageButton captureButton = (ImageButton) cameraFrag.findViewById(R.id.button_capture);

            // for at få den visuelle layout foran kameraet.

            constraintlayout.bringToFront();
            constraintlayout.invalidate();


            captureButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        runningTask = new AsycSavPicture();
                        runningTask.execute();
                        // get an image from the camera
                    }
                }
            );
        }
        Log.d("TEST", "done: ");

        return cameraFrag;
    }

    // Todo Der er nået gald med linje 89 - er ikke sikker på hvordan 'casten' fungere
    private final class AsycSavPicture extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... objects) {

                mCamera.takePicture(null, null, PhotoHandler.getInstance(getContext()));
                Log.d("TEST1", "pre: ");

                return "Executed";        }

        @SuppressLint("ResourceType")
        @Override
        protected void onPostExecute(String result) {
            Log.d("TEST2", "POSTexecuted: ");

            Fragment resultatFrag = new Resultat();

            getFragmentManager().beginTransaction()
                    .replace(R.id.FragmentFL, resultatFrag)
                    .addToBackStack(null)
                    .commit();
        }
    }


    /** A safe way to get an instance of the Camera object. */
    public Camera getCameraInstance(){
        Camera c = null;
        try {
            int i = findRearFacingCamera();
            if (i == -1)
                c = Camera.open(); // attempt to get a Camera instance
            else
                c = Camera.open(i);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    /** Finds ID for a read facing camera, just in case the default camera is a font-facing one */
    private int findRearFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
                Log.d("findRearFacingCamera", "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    /** Check if this access was granted to the camera and request it if missing */
    private boolean checkCameraAccess(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            return false;
        }
    }

    /** Is called when a user accepts or denies access to camera, redraws the fragment, is access is granted */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FragmentTransaction ftr = getFragmentManager().beginTransaction();
                ftr.detach(CameraFragment.this).attach(CameraFragment.this).commit();
            }
        }
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    public void onPause() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        super.onPause();
    }
}