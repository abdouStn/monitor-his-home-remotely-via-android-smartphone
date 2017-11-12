package com.example.quentin.rapace_v1.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.quentin.rapace_v1.Activity.Authentification;

public class Bouton_Voir_Listener implements View.OnClickListener {
    private Activity source = null;

    public Bouton_Voir_Listener (Activity source) {
        this.source = source;
    }
    @Override
    public void onClick(View v) {
        Intent message = new Intent(source.getBaseContext(), Authentification.class);
        source.startActivity(message);
    }
}

