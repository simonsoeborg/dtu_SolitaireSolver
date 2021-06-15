package grp5.cdio.solitairesolver.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.StartLoadingScreen;

public class LoadingScreen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View startFrag = inflater.inflate(R.layout.activity_start_loading_screen, container, false);


        Handler h = new Handler();

        Runnable r = new Runnable() {

            @Override
            public void run() {
                // if you are redirecting from a fragment then use getActivity() as the context.
                Fragment fragment = new Start();
                getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentFL, fragment)
                        .addToBackStack(null)
                        .commit();

            }
        };
        // The Runnable will be executed after the given delay time
        h.postDelayed(r, 1000); // will be delayed for 3 seconds

        return startFrag;
    }
}
