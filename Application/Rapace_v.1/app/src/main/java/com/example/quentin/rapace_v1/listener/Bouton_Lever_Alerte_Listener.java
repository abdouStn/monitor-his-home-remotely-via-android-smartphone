package com.example.quentin.rapace_v1.listener;

import android.view.View;

import com.example.quentin.rapace_v1.Activity.Lecture_Streaming;
import com.example.quentin.rapace_v1.network.Serveur;

/**
 * Ecoute un bouton dont la pression provoque la suspension d'une alerte.
 */
public class Bouton_Lever_Alerte_Listener implements View.OnClickListener {
    /** Activité streaming contenant le bouton */
    private Lecture_Streaming source = null;

    /**
     * Constructeur
     * @param activity Activité streaming contenant le bouton.
     */
    public Bouton_Lever_Alerte_Listener (Lecture_Streaming activity) {
        source = activity;
    }
    @Override
    public void onClick(View v) {

        // On demande au serveur de lever l'alerte :
        Serveur.lever_alerte(source.getIntent().getStringExtra("email"),
                source.getIntent().getStringExtra("psswd"),
                Integer.toString(source.getIntent().getIntExtra("site_id", 0)));

       // on bascule en mode "safe" :
        source.switch_safe_mode();
    }
}
