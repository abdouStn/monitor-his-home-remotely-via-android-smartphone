package com.example.quentin.rapace_v1.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.quentin.rapace_v1.R;
import com.example.quentin.rapace_v1.listener.Bouton_Voir_Listener;

/**
 * Activité intervenant lors de la réception d'un sms d'alerte. Son rôle est de prévenir l'utilisateur d'un danger potentiel.
 */
public class Alert extends ActionBarActivity {
    private IntentFilter intentFilter;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive (Context context, Intent intent) {
            Toast.makeText(Alert.this, "Avant de poursuivre, veuillez vous identifier ", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        Button bouton_voir = (Button) findViewById(R.id.bouton_voir);
        bouton_voir.setOnClickListener(new Bouton_Voir_Listener(this));

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        registerReceiver(intentReceiver, intentFilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alert, menu);
        return true;
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


    @Override
    protected void onDestroy() {
        unregisterReceiver(intentReceiver);
        super.onPause();
    }


}
