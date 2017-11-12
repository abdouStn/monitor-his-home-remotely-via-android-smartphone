package com.example.quentin.rapace_v1.network;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Permet l'envoi de requete HTTP suivant la methode POST
 */
public class MessagePost extends MessageHttp {

    /**
     * Constructeur
     * @param url adresse de la cible
     * @param param parametres de la requete
     */
    public MessagePost(String url, String param) {
        super(url, param);
    }

    /**
     * Traitement effectue par le Thread a son execution
     */
    @Override
    public void run(){
        ouvrir_connection();
        emettre_requete();
        setReponse_serveur(recevoir_reponse());
        fermer_connection();

    }

    /**
     * Ouverture du tampon d'emission
     * @return tampon d'emission ouvert
     */
   public OutputStreamWriter obtenir_tampon_emission() {
        OutputStreamWriter _tampon_emission = null;
        try {
            _tampon_emission = new OutputStreamWriter(getConnection().getOutputStream());
        } catch (IOException e) {
            Log.e("Erreur", "Impossible d'obtenir un flux de sortie.");
            e.printStackTrace();
        }
        return _tampon_emission;
    }

    /**
     * Preparation de la requete : chargement des parametres en tampon.
     * @param _tampon_emission tampon d'emission ouvert
     */
    public void preparerRequete(OutputStreamWriter _tampon_emission) {
        // On y écrit notre morceau de requête HTTP contenu dans param :
        try {
            _tampon_emission.write(getParam());
        } catch (IOException e) {
            Log.e("Erreur", "Impossible d'écrire sur le flux sortant.");
            e.printStackTrace();
        }
    }

    /**
     * Envoi une requete HTTP suivant la methode POST
     * @param _tampon_emission tampon d'emission ouvert et prepare
     */
    public void envoyerRequete(OutputStreamWriter _tampon_emission) {
        // On vide le buffer afin d'envoyer notre requête HTTP :
        try {
            _tampon_emission.flush();
        } catch (IOException e) {
            Log.e("Erreur", "Impossible d'envoyer la requete HTTP.");
            e.printStackTrace();
        }
    }

    /**
     * Met en forme et emet une requete
     */
    public void emettre_requete() {
        // On autorise un tampon pour l'écriture de la requête :
        getConnection().setDoOutput(true);
        getConnection().setChunkedStreamingMode(0);

        OutputStreamWriter tampon_sortie = obtenir_tampon_emission();
        if (tampon_sortie == null) {
       Log.e("tampon" , " nul");
        }
        preparerRequete(tampon_sortie);
        envoyerRequete(tampon_sortie);

        // On ferme le tampon car il ne nous sera plus utile :
        try {
            tampon_sortie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


