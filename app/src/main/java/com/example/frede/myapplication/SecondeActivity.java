package com.example.frede.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SecondeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        GetBieresServices.startActionBiers(this);
        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),intentFilter);

    }

    public static final String BIERS_UPDATE = "com.octip.cours.inf3044_11.BIERS_UPDATE";
        public class BierUpdate extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(SecondeActivity.this,getString(R.string.donwloaded),Toast.LENGTH_LONG).show();

            }
        }
}

