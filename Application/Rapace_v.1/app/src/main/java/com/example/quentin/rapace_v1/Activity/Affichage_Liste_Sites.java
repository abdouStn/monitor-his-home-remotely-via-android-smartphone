package com.example.quentin.rapace_v1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.quentin.rapace_v1.R;
import com.example.quentin.rapace_v1.metier.Site;
import com.example.quentin.rapace_v1.bouton.Bouton_Site;
import com.example.quentin.rapace_v1.network.Serveur;

import java.util.Vector;
/**
 * Activité s'occupant de l'affichage des sites sous surveillance.
 *
 */
public class Affichage_Liste_Sites extends Activity {
    final String EXTRA_ID = "user_id";
    final String EXTRA_NOM = "user_login";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_liste_sites);

        // Doit se faire après ?
        TextView nomDisplay = (TextView) findViewById(R.id.nomDisplay);
        // ImageButton imageDisplay = (ImageButton) findViewById(R.id.imageDisplay);

        Intent intent = getIntent();


        nomDisplay.setText(intent.getStringExtra(EXTRA_NOM));

    }

    @Override
    public void onResume(){
        super.onResume();
        Vector<Site> sites = Serveur.charger_site(getIntent().getStringExtra(EXTRA_ID));
        LinearLayout content = (LinearLayout) findViewById(R.id.content2);
        content.removeAllViewsInLayout();

        if ( sites == null ) {
            TextView no_site = new TextView(this);
            no_site.setText("Vous ne surveillez actuellement aucun site.");
            LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            content.addView(no_site, layoutParam);
        }
        else {
            for (int i=0; i < sites.size() ; i++) {
                Bouton_Site bouton_site_i = new Bouton_Site(this,sites.elementAt(i));
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParam.bottomMargin = 12;
                content.addView(bouton_site_i, layoutParam);
            }
        }
   }

}