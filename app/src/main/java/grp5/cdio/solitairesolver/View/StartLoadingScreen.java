/*
Author: Simon Søborg
Collaborator(s): Kristoffer Baumgarten
 */
package grp5.cdio.solitairesolver.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.Fragments.LoadingScreen;
import grp5.cdio.solitairesolver.View.Fragments.Resultat;
import grp5.cdio.solitairesolver.View.Fragments.Start;

public class StartLoadingScreen extends AppCompatActivity {

    public static StartLoadingScreen Instance;
    public static Context context;

    public static StartLoadingScreen getInstance() {
        if(Instance == null) {
            Instance = new StartLoadingScreen();
        }
        return Instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_start);

        getInstance();
        context = this;

        // if you are redirecting from a fragment then use getActivity() as the context.
        Fragment fragment = new LoadingScreen();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentFL, fragment)
                .addToBackStack(null)
                .commit();

    }
}