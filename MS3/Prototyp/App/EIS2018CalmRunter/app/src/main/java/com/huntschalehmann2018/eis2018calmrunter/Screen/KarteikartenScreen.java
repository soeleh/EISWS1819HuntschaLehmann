package com.huntschalehmann2018.eis2018calmrunter.Screen;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.huntschalehmann2018.eis2018calmrunter.R;


public class KarteikartenScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_started);

        Button verlassenBTN = findViewById(R.id.beenden);
        verlassenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button weiterBTN = findViewById(R.id.weiter);
        verlassenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

