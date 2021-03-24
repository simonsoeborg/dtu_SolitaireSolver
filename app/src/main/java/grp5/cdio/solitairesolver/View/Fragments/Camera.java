package grp5.cdio.solitairesolver.View.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.StartLoadingScreen;

public class Camera extends Fragment  {
    Camera camera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View cameraFrag = inflater.inflate(R.layout.fragment_camera, container, false);
        // Inflate the layout for this fragment

        if (ContextCompat.checkSelfPermission(cameraFrag.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            startCamera();

        }else{
            Toast.makeText(cameraFrag.getContext() ,"camera permission denied", Toast.LENGTH_LONG).show();
            //TODO - få arrayOf til at fungere, så den bér om permission
            //ActivityCompat.requestPermissions(cameraFrag.getContext(), arrayOf(Manifest.permission.CAMERA));
        }
        return cameraFrag;
    }

    private void startCamera() {


    }
}