package grp5.cdio.solitairesolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartSide extends AppCompatActivity {
    Button tagBillede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_side);

        tagBillede = findViewById(R.id.TagBillede);

        tagBillede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartSide.this, Resultat.class));
            }
        });


    }
}