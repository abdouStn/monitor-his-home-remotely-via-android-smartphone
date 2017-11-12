package com.example.quentin.rapace_v1.listener;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.quentin.rapace_v1.Activity.Lecture_Streaming;

/**
 * Ecoute un bouton dont la pression provoque un appel de police.
 */
public class Bouton_Call_Police_Listener implements View.OnClickListener {
    /** Activité streaming contenant le bouton */
    private Lecture_Streaming source = null;

    /**
     * Constructeur
     * @param lecture_streaming Activité streaming contenant le bouton.
     */
    public Bouton_Call_Police_Listener(Lecture_Streaming lecture_streaming) {
        source = lecture_streaming;
    }

    @Override
    public void onClick(View v) {
        String n = "tel:17";
        Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse(n) );
        source.startActivity(intent);
    }
}
