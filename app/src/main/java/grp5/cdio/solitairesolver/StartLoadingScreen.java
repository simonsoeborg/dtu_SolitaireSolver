package grp5.cdio.solitairesolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartLoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_loading_screen);

        Runnable r = new Runnable() {

            @Override
            public void run() {
                // if you are redirecting from a fragment then use getActivity() as the context.
                startActivity(new Intent(StartLoadingScreen.this, StartSide.class));

            }
        };


        Handler h = new Handler();

        // The Runnable will be executed after the given delay time
        h.postDelayed(r, 5000); // will be delayed for 3 seconds

    }
}