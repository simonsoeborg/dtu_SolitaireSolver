package grp5.cdio.solitairesolver.View.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.CameraPreview;
import grp5.cdio.solitairesolver.View.PhotoHandler;

public class CameraFragment extends Fragment  {
    private Camera mCamera;
    private CameraPreview mPreview;
    private static final int MY_CAMERA_PERMISSION_CODE = 42069;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View cameraFrag = inflater.inflate(R.layout.fragment_camera, container, false);
        Context context = cameraFrag.getContext();


        // dette er den visuelle grid layout
        View gridgrp1 = cameraFrag.findViewById(R.id.grp1Line);
        View gridgrp2 = cameraFrag.findViewById(R.id.grp2Line);
        View gridgrp3 = cameraFrag.findViewById(R.id.grp3Line);
        View gridgrp4 = cameraFrag.findViewById(R.id.grp4Line);
        View gridgrp5 = cameraFrag.findViewById(R.id.grp5Line);
        View gridgrp6 = cameraFrag.findViewById(R.id.grp6Line);
        View gridgrp7 = cameraFrag.findViewById(R.id.grp7Line);
        View gridAdskilleren = cameraFrag.findViewById(R.id.adskilleren);



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
            Button captureButton = (Button) cameraFrag.findViewById(R.id.button_capture);



            // for at få den visuelle layout foran kameraet.
            gridgrp1.bringToFront();
            gridgrp2.bringToFront();
            gridgrp3.bringToFront();
            gridgrp4.bringToFront();
            gridgrp5.bringToFront();
            gridgrp6.bringToFront();
            gridgrp7.bringToFront();
            gridAdskilleren.bringToFront();

            captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        mCamera.takePicture(null, null, new PhotoHandler(context));
                    }
                }
            );
        }

        return cameraFrag;
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