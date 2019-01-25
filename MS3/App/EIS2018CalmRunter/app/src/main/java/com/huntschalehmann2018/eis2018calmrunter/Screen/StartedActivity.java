package com.huntschalehmann2018.eis2018calmrunter.Screen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huntschalehmann2018.eis2018calmrunter.R;
import com.huntschalehmann2018.eis2018calmrunter.Resources.Karteikarte;
import com.huntschalehmann2018.eis2018calmrunter.Resources.Karteikartenstapel;

public class StartedActivity extends AppCompatActivity{

    private int karteikartenstapelDurationInMinutes = 0, streckenLaenge = 0, averageWalkingSpeedInMeter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_started);

        //Momentan static, wird durch später durch Serverabfrage gefüllt
        Karteikartenstapel karteikartenstapel1 = new Karteikartenstapel("CGA","Christopher");
        karteikartenstapel1.addKarteikarte(new Karteikarte("Das hier ist die erste Frage","das hier ist die erste Antwort",5));
        karteikartenstapel1.addKarteikarte(new Karteikarte("Das hier ist die zweite Frage","das hier ist die zweite Antwort",5));
        karteikartenstapel1.addKarteikarte(new Karteikarte("Das hier ist die dritte Frage","das hier ist die dritte Antwort",5));
        karteikartenstapel1.addKarteikarte(new Karteikarte("Das hier ist die vierte Frage","das hier ist die vierte Antwort",5));
        karteikartenstapel1.addKarteikarte(new Karteikarte("Das hier ist die fünfte Frage","das hier ist die fünfte Antwort",5));
        karteikartenstapel1.addKarteikarte(new Karteikarte("Das hier ist die sechste Frage","das hier ist die sechste Antwort",5));



        //Berechnung der Strecke in Metern nach der Formel
        //Dauer des Karteikartenstapels holen
        karteikartenstapelDurationInMinutes = karteikartenstapel1.getDurationInMinutes();
        //Streckenlänge (m) = Dauer der Lerneinheit (min) x durchschnittliche Laufdistanz (m) pro Sekunde * 60/2
        streckenLaenge = karteikartenstapelDurationInMinutes * averageWalkingSpeedInMeter * 60/2;

        //Füllen der Textfelder mit jedem Klick auf Button Weiter
        TextView nameDerLerneinheit = (TextView) findViewById(R.id.nameDerLerneinheit);
        for(int y=0; y<=karteikartenstapel1.getKarteikartenlistenLaenge(); y++){
            TextView frage = (TextView) findViewById(R.id.aktuelleFrage);
            /*
            String stringantwort = karteikartenstapel1.toString()

            frage.setText(karteikartenstapel1.get(y));
            */
        }
        final Button zeigeRueckseite = (Button) findViewById(R.id.weiter);


        zeigeRueckseite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.aktuelleFrage);
                tv.setText("Programiersprache, Betriebssystem, Prozessorarchitektur und Programmiersprache");
                zeigeRueckseite.setText("Nächste Karte");
            }
        });



        Button verlassen = (Button) findViewById(R.id.beenden);
        verlassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartedActivity.this, Homescreen.class));
            }
        });
    }
}