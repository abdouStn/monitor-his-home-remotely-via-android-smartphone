package com.example.quentin.rapace_v1.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe abstraite implémentant des fonctions de bases necessaires à l'envoi de requete Http.
 */
public abstract class MessageHttp extends Thread {
    /** adresse de la cible */
    private URL url = null;
    /** parametre de la requete */
    private String param = null;
    /** classe gerant l'envoi et la reception des requetes Http */
    private HttpURLConnection connection = null;
    /** reponse obtenue apres traitement de notre requete */
    private static String reponse_serveur = null;

    /**
     * Constructeur
     * @param url adresse de la cible
     * @param param paremetres de la requete
     */
    public  MessageHttp(String url, String param) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            Log.e("Erreur", "URL invalide (malformée ?)");
        }
        this.param = param;

    }

    /**
     * Accesseur vers l'url de la cible
     * @return l'url de la cible
     */
    public URL getUrl() {
        return url;
    }

    /**
     * Accesseur vers les parametres de la requete
     * @return parametres de la requete
     */
    public String getParam() {
        return param;
    }

    /**
     * Accesseur vers l'instance de HttpURLConnection
     * @return l'instance de HttpURLConnection
     */
    public HttpURLConnection getConnection() {
        return connection;
    }

    /**
     * Accesseur vers la reponse obtenu apres traitement de la requete. Attention aux problemes de syncronisation.
     * @return reponse obtenue apres traitement de la requete
     */
    public String getReponse_serveur() {
        return reponse_serveur;
    }

    /**
     * Mutateur vers l'url de la cible.
     * @param url nouvelle valeur de l'url
     */
    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * Mutateur des parametres de la requete
     * @param param nouvelle valeur des parametres de requete
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * Mutateur vers l'instance de HttpURLConnection
     * @param connection nouvelle valeur de l'instance de HttpURLConnection
     */
    public void setConnection(HttpURLConnection connection) {
        this.connection = connection;
    }

    /**
     * Mutateur vers la reponse obtenu apres traitement de la requete
     * @param reponse_serveur
     */
    public void setReponse_serveur(String reponse_serveur) {
        this.reponse_serveur = reponse_serveur;
    }

    /**
     * SubClassResponsability
     */
    @Override
    public void run(){}

    /**
     * Ouvre une nouvelle connection.
     */
    public void ouvrir_connection() {
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Log.e("Erreur", "Impossible d'accéder à l'url demandée " + url.toString());
        }

    }

    /**
     * Ferme une connection active.
     */
    public void fermer_connection() {
        connection.disconnect();
    }

    /**
     * Ouvertue du tampon de reception
     * @return tampon de reception ouvert (si reussite)
     */
    public BufferedReader obtenir_tampon_reception() {
        BufferedReader _tampon_reception = null;
        // Ouverture du flux :
        try {
            _tampon_reception = new BufferedReader
                    (new InputStreamReader(connection.getInputStream()));
        } catch (IOException e1) {
            Log.e("Erreur", "Erreur dans la réception du flux en provenance de " + url.toString());
        }
        return _tampon_reception;
    }

    /**
     * Encadre la reception d'une reponse serveur
     * @return reponse a la requete
     */
    public String recevoir_reponse() {
        BufferedReader tampon_reception = obtenir_tampon_reception();
        String message = lire(tampon_reception);

        // Fermeture du tampon :
        try {
            tampon_reception.close();
        } catch (IOException e1) {
            Log.e("Erreur", "Impossible de fermer le flux de récpetion en provenance de " + url.toString());
        }
        return message;
    }

    /**
     * Lit le contenu d'un tampon
     * @param bufferedReader tampon a lire
     * @return String fidele au contenu du tampon
     */
    public String lire(BufferedReader bufferedReader) {
        String line;
        StringBuffer sb = new StringBuffer();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e1) {
            Log.e("Erreur", "Erreur dans la lecture du flux en provenance de " + url.toString());
        }
        return sb.toString();
    }
}
