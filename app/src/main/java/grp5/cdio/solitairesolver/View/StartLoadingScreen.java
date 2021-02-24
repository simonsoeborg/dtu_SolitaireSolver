package grp5.cdio.solitairesolver.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import grp5.cdio.solitairesolver.R;
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
        setContentView(R.layout.activity_start_loading_screen);

        getInstance();
        context = this;

        Runnable r = new Runnable() {

            @Override
            public void run() {
                // if you are redirecting from a fragment then use getActivity() as the context.
                Fragment fragment = new Start();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.FragmentFL, fragment)
                        .commit();
            }
        };


        Handler h = new Handler();

        // The Runnable will be executed after the given delay time
        h.postDelayed(r, 5000); // will be delayed for 3 seconds

    }
}