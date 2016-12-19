package com.example.frede.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(R.string.CustomTitle);
    }
    public void Alpha(View view) {
        Intent i = new Intent(MainActivity.this, SecondeActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.tItem :
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.AlertTitle)
                        .setMessage(R.string.AlertText)
                        .setPositiveButton(R.string.Clear, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new File(getCacheDir() + "/" + "cards.json").getAbsoluteFile().delete();
                            }
                        })
                        .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .show();

                break;
            case R.id.About:
                final SpannableString s = new SpannableString(getString(R.string.aboutText));
                Linkify.addLinks(s, Linkify.ALL);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setTitle(R.string.about);
                TextView textView = new TextView(this);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setText(s);
                dialogBuilder.setView(textView);
                dialogBuilder.setNeutralButton(R.string.Confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })

                        .show();


            default:
                break;
        }



        return super.onOptionsItemSelected(item);
    }
}
