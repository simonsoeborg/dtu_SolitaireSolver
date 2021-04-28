package grp5.cdio.solitairesolver.View.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.StartLoadingScreen;

public class Resultat extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View resultatFrag = inflater.inflate(R.layout.fragment_resultat, container, false);
        Context context = StartLoadingScreen.context;
        TextView instructionTV = resultatFrag.findViewById(R.id.instructionTV_resultatFrag);
        Button tagBilledeBtn = resultatFrag.findViewById(R.id.TagBilledeBtn_resultatFrag);

        tagBilledeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Run logic to determine next move

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentFL, new CameraFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });

        return resultatFrag;
    }
}