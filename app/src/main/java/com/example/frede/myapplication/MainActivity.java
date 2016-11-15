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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, getString(R.string.hello), Toast.LENGTH_LONG).show();

                Intent i = new Intent(MainActivity.this, SecondeActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.fItem :
                Toast.makeText(MainActivity.this,getString(R.string.first),Toast.LENGTH_LONG).show();

                break;

            case R.id.sItem :
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.second)
                        .setMessage(R.string.message)
                        .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
            case R.id.tItem :

                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setContentTitle("My notif")
                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .setContentText("My content");


                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(1,builder.build());



            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
