package com.example.quentin.rapace_v1.listener;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.quentin.rapace_v1.Activity.Affichage_Liste_Sites;
import com.example.quentin.rapace_v1.Activity.Authentification;
import com.example.quentin.rapace_v1.R;
import com.example.quentin.rapace_v1.network.Serveur;
import com.example.quentin.rapace_v1.metier.Utilisateur;

/**
 * Ecoute un bouton, et lance une demande d'authentification.
 */
public class Bouton_Authentification_Listener implements View.OnClickListener {
/** Activité d'authentification à écouter.*/
    public Authentification source = null;

    /**
     * Constructeur
     * @param authentification Activité Authentification à écouter.
     */
    public Bouton_Authentification_Listener(Authentification authentification) {
        source = authentification;
    }
    @Override
    public void onClick(View v) {
        source.maj_preferences();

        // On récupère le contenu des EditText :
        String email = source.getInputEmail().getText().toString();
        String password = source.getInputPassword().getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(source.getApplicationContext(), R.string.email_or_password_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        // Sinon on lance une demande d'authentification auprès du serveur :
        Utilisateur utilisateur = Serveur.authentifier(email, password);

        // Si l'utilisateur existe dans la base de données, alors on lance l'activité suivante :
        if (utilisateur != null) {

            Toast.makeText(source.getApplicationContext(), "connection réussie", Toast.LENGTH_SHORT).show();

            Intent toAffichage_Liste_Sites = new Intent(source.getApplicationContext(), Affichage_Liste_Sites.class);
            toAffichage_Liste_Sites.putExtra(source.getEXTRA_ID(), Integer.toString(utilisateur.getId()));
            toAffichage_Liste_Sites.putExtra(source.getEXTRA_NOM(), utilisateur.getNom());
            toAffichage_Liste_Sites.putExtra("email", utilisateur.getEmail());
            toAffichage_Liste_Sites.putExtra("psswd", utilisateur.getPsswd());

            source.startActivity(toAffichage_Liste_Sites);

            }
        else {
            Toast.makeText(source.getApplicationContext(), "Identifiants incorrects", Toast.LENGTH_SHORT).show();
        }
    }
}