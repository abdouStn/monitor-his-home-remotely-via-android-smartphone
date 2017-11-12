package com.example.quentin.rapace_v1.bouton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.quentin.rapace_v1.metier.Site;
import com.example.quentin.rapace_v1.listener.Bouton_Site_Listener;

/** Bouton cliquable représentant un site surveillé.*/
public class Bouton_Site extends Button {

    private Site site = null;
    private Activity activity = null;

    // Constructeur :
    /**
     * Constructeur
     * @param activity Activité contenant le bouton.
     * @param site Site représenté par le bouton.
     */
        public Bouton_Site(Activity activity, Site site){

        super(activity.getApplicationContext().getApplicationContext());
        this.activity = activity;
        this.site = site;

        if (site.getEtat_alerte().equals("1")) {
            setBackgroundColor(Color.argb(255, 255, 58, 32));
        }
        else {
            setBackgroundColor(Color.argb(255, 36, 255, 128));
        }

        setText(site.getNom());
        // Personnalisation :
        //setImageDrawable(getResources().getDrawable(R.drawable.android));
        setOnClickListener(new Bouton_Site_Listener(this));

    }

    /**
     * Accesseur vers l'instance de Site représentée.
     * @return le site réprésenté.
     */
    public Site getSite() {
        return site;
    }

    /**
     * Accesseur vers l'activité contenant le bouton.
     * @return Activité contenant le bouton.
     */
    public Activity getActivity() {
        return activity;
    }
}
