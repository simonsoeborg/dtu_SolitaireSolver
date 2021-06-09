package grp5.cdio.solitairesolver.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.Controller.Controller;


public class CardControl extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View controlFrag = inflater.inflate(R.layout.fragment_card_control, container, false);
        super.onCreate(savedInstanceState);
        Button tagBillede = controlFrag.findViewById(R.id.tag_billede_igen);
        Button fortsaet = controlFrag.findViewById(R.id.Continue);


        Controller con = new Controller();
        String bestMove = con.getMove().toString();

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

        fortsaet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.makeMove();
                assert getFragmentManager() != null;

                Bundle argumemt = new Bundle();
                argumemt.putString("bestMove", bestMove);

                Resultat resultatFrag = new Resultat();
                resultatFrag.setArguments(argumemt);

                getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentFL, resultatFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return controlFrag;
    }
}