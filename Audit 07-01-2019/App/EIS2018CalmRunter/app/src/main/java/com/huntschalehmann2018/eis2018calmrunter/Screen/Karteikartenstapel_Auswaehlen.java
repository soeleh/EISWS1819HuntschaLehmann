package com.huntschalehmann2018.eis2018calmrunter.Screen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.huntschalehmann2018.eis2018calmrunter.R;
import com.huntschalehmann2018.eis2018calmrunter.Util.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//für json anbindung

public class Karteikartenstapel_Auswaehlen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = Karteikartenstapel_Auswaehlen.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get karteikartennstapel JSON
    private static String url = "localhost:2019/calmrunter/";

    ArrayList<HashMap<String, String>> Karteikartenliste;

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


        Karteikartenliste = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetKarteikartenstapel().execute();


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

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetKarteikartenstapel extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Karteikartenstapel_Auswaehlen.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray karteikartenstapel = jsonObj.getJSONArray("karteikartenstapel");

                    // looping through All Karteikartenstapel
                    for (int i = 0; i < karteikartenstapel.length(); i++) {
                        JSONObject c = karteikartenstapel.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String amountkarteikarten = c.getString("anzahlKarten");
                        String learnduration = c.getString("lerndauer");
                        String tags = c.getString("tag");
                        String kategory = c.getString("kategorie");
                        String studienfach = c.getString("studienfach");
                        String modul = c.getString("modul");

                        // tmp hash map for single karteikarte
                        HashMap<String, String> karteikarte = new HashMap<>();

                        // adding each child node to HashMap key => value
                        karteikarte.put("id", id);
                        karteikarte.put("ersteller", name);
                        karteikarte.put("vorderseite", amountkarteikarten);
                        karteikarte.put("Lerndauer", learnduration);
                        karteikarte.put("tag", tags);
                        karteikarte.put("studienfach", studienfach);
                        karteikarte.put("kategorie", kategory);
                        karteikarte.put("modul", modul);


                        // adding contact to contact list
                        //Karteikartenstapel_Auswaehlen.add(karteikarte);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Karteikartenstapel_Auswaehlen.this, Karteikartenliste,
                    R.layout.item_list, new String[]{"modul", "ersteller",
                    "lerndauer"}, new int[]{R.id.modul,
                    R.id.ersteller, R.id.dauer});

            lv.setAdapter(adapter);
        }

    }
}


