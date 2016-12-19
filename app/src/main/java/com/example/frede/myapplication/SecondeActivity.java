package com.example.frede.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SecondeActivity extends AppCompatActivity {
    RecyclerView r;
    JSONArray biers;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);

        File f = new File(getCacheDir() + "/" + "cards.json");
        if (f.exists()){
            Toast.makeText(SecondeActivity.this, getString(R.string.AlreadyDown), Toast.LENGTH_LONG).show();
            spinner.setVisibility(View.GONE);
        }
        else{
            Toast.makeText(SecondeActivity.this, getString(R.string.needToDownload), Toast.LENGTH_LONG).show();
            GetBieresServices.startActionBiers(this);
            IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
            LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentFilter);
        }
        biers=alphaOrder();

        r = (RecyclerView) findViewById(R.id.rv_biere);
        r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        r.setAdapter(new BiersAdapter(biers));

    }


    public static final String BIERS_UPDATE = "com.octip.cours.inf3044_11.BIERS_UPDATE";

    public class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(SecondeActivity.this, getString(R.string.downloaded), Toast.LENGTH_LONG).show();
            // create refresh
            spinner.setVisibility(View.GONE);
            Intent intentNot = new Intent(SecondeActivity.this, SecondeActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(SecondeActivity.this, 0, intentNot, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(SecondeActivity.this)
                    .setContentTitle(getString(R.string.downloaded))
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setContentText(getString(R.string.CardDownloaded));


            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1,builder.build());
            ((BiersAdapter)r.getAdapter()).setNewBiere(alphaOrder());

        }
    }

    public JSONArray getBiersFromFile() {
        try {

            InputStream is = new FileInputStream(getCacheDir() + "/" + "cards.json");
            byte[] buffer = new byte[is.available()];

            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public JSONArray alphaOrder() {

        biers = getBiersFromFile();
        JSONArray sortedJsonArray = new JSONArray();

        List<JSONObject> jsonValues = new ArrayList<>();
        for (int i = 0; i < biers.length(); i++) {
            try {
                jsonValues.add(biers.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            private static final String KEY_NAME = "name";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return valA.compareTo(valB);
            }
        });

        for (int i = 0; i < biers.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        return sortedJsonArray;
    }
}


