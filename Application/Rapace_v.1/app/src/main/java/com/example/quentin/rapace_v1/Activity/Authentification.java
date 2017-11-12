package com.example.quentin.rapace_v1.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.quentin.rapace_v1.R;
import com.example.quentin.rapace_v1.listener.Bouton_Authentification_Listener;

/**
 * Activité gérant l'authentification.
 */
public class Authentification extends ActionBarActivity {
    /** Champ dédié à l'adresse mail */
    private EditText inputEmail;
    /** Champ dédié au mot de passe */
    private EditText inputPassword;
    /** Champ dédié à la gestion de préférences */
    private CheckBox checkBox;
    /** Bouton "s'authentifier" */
    private Button bouton_Login;

    // pour le passage de donnees a la seconde activité :
    private static final String EXTRA_ID = "user_id";
    private static final String EXTRA_NOM = "user_login";

    // pour la gestion des préférences :
    private static final String PREFS_NAME = ".Preferences";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_CHECKED = "checked";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        // Nomme l'activité :
        setTitle("Login");

        initialiser_widget();
        initialiser_preferences();


        bouton_Login.setOnClickListener(new Bouton_Authentification_Listener(this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authentification, menu);
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

    // Accesseurs :
    public EditText getInputEmail() {
        return inputEmail;
    }
    public EditText getInputPassword() {
        return inputPassword;
    }
    public String getEXTRA_ID() {
        return EXTRA_ID;
    }
    public String getEXTRA_NOM() {
        return EXTRA_NOM;
    }

    // Gestion du contenu :
    /** Initialise les attributs "widget" */
    private void initialiser_widget() {
        inputEmail = (EditText) findViewById(R.id.loginEmail);
        inputPassword = (EditText) findViewById(R.id.loginPassword);
        checkBox = (CheckBox)findViewById(R.id.cbRememberMe);
        bouton_Login = (Button) findViewById(R.id.connexionButtton);
    }

    /** Charge les paramètres de préférences */
    private void initialiser_preferences(){
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String email = pref.getString(PREF_EMAIL, "");
        String password = pref.getString(PREF_PASSWORD, "");
        String checked = pref.getString(PREF_CHECKED, "");

        inputEmail.setText(email);
        inputPassword.setText(password);
        checkBox.setChecked(Boolean.parseBoolean(checked));
    }

    /** Enregistre préférences après pression sur le bouton "s'authentifier" */
    public void maj_preferences() {
        // Si la case "se souvenir de moi" est cochée, on met les données à jours :
        if (checkBox.isChecked()) {
            getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                    .edit()
                    .putString(PREF_EMAIL, inputEmail.getText().toString())
                    .putString(PREF_PASSWORD, inputPassword.getText().toString())
                    .putString(PREF_CHECKED, "TRUE")
                    .commit();
        }
        // Sinon on les efface :
        else {
            getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit().clear().commit();
        }
    }
}
