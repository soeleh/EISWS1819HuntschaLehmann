package com.huntschalehmann2018.eis2018calmrunter.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.huntschalehmann2018.eis2018calmrunter.R;

import java.util.Date;

public class Karteikartenstapel_Auswaehlen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosekarteikartenstapel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout banner1 = findViewById(R.id.firstlernkarte);
        banner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Karteikartenstapel_Auswaehlen.this, StartedActivity.class));
            }
        });

        LinearLayout banner2 = findViewById(R.id.firstlernkarte);
        banner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Karteikartenstapel_Auswaehlen.this, StartedActivity.class));
            }
        });

        LinearLayout banner3 = findViewById(R.id.firstlernkarte);
        banner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Karteikartenstapel_Auswaehlen.this, StartedActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homescreen, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myProfile) {
            startActivity(new Intent(this, Profile.class));
        } else if (id == R.id.nav_createKarteikartenstapel) {
            startActivity(new Intent(this, Karteikartenstapel_Erstellen.class));
        } else if (id == R.id.nav_favorites) {
            startActivity(new Intent(this, Karteikarten_Gespeichert.class));
        } else if (id == R.id.nav_statistics) {
            startActivity(new Intent(this, Statistiken.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, Einstellungen.class));
        } else if (id == R.id.nav_faq) {
            startActivity(new Intent(this, FAQ.class));
        } else if (id == R.id.nav_developer) {
            startActivity(new Intent(this, Developer.class));
        } else if (id == R.id.nav_impressum) {
            startActivity(new Intent(this, Impressum.class));
        } else if (id == R.id.nav_data_privacy) {
            startActivity(new Intent(this, Datenschutz.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}