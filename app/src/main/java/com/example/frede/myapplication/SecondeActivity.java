package com.example.frede.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SecondeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        GetBieresServices.startActionBiers(this);
        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),intentFilter);
        //
        JSONArray biers = getBiersFromFile();
        if(biers == null)
        {
            Toast.makeText(SecondeActivity.this,"error",Toast.LENGTH_LONG).show();
            Log.d("tag","error");
        }
        else {
            RecyclerView r = (RecyclerView) findViewById(R.id.rv_biere);
            r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            r.setAdapter(new BiersAdapter(biers));
        }
    }


    public static final String BIERS_UPDATE = "com.octip.cours.inf3044_11.BIERS_UPDATE";
        public class BierUpdate extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(SecondeActivity.this,getString(R.string.downloaded),Toast.LENGTH_LONG).show();
                //setAdapterBeer...
            }

        }
    public JSONArray getBiersFromFile()
    {
        try {
            Log.d("tag","yolo");

            InputStream is = new FileInputStream(getCacheDir()+"/"+"bieres.json");
            byte[] buffer = new byte[is.available()];

            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

