package com.example.quentin.rapace_v1.listener;

import android.content.Intent;
import android.view.View;

import com.example.quentin.rapace_v1.bouton.Bouton_Site;
import com.example.quentin.rapace_v1.Activity.Lecture_Streaming;

/** Ecoute un Bouton_Site */
public class Bouton_Site_Listener implements View.OnClickListener {
    /** Bouton_Site à écouter */
    private Bouton_Site bouton = null;

    /**
     * Constructeur
     * @param bouton_site bouton à écouter.
     */
    public Bouton_Site_Listener(Bouton_Site bouton_site) {
        bouton = bouton_site;
    }

    @Override
    public void onClick(View v) {
        Intent toLecture_Streaming = new Intent(bouton.getActivity().getApplicationContext(), Lecture_Streaming.class);
        toLecture_Streaming.putExtra("site_id", bouton.getSite().getId());
        toLecture_Streaming.putExtra("site_etat", bouton.getSite().getEtat_alerte());
        toLecture_Streaming.putExtra("email", bouton.getActivity().getIntent().getStringExtra("email"));
        toLecture_Streaming.putExtra("psswd", bouton.getActivity().getIntent().getStringExtra("psswd"));
        bouton.getActivity().startActivity(toLecture_Streaming);

    }
}
