/*
Author: Simon Søborg
Collaborator(s): Kristoffer Baumgarten, Theis Folkmann
 */
package grp5.cdio.solitairesolver.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.StartLoadingScreen;


public class Start extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View startFrag = inflater.inflate(R.layout.fragment_start, container, false);

        Button tagBillede = startFrag.findViewById(R.id.TagBilledeBtn_startFrag);
        Context context = StartLoadingScreen.context;

        tagBillede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentFL, new CameraFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return startFrag;
    }
}