package com.example.quentin.rapace_v1.network;

//@todo mettre des exceptions pour ne pas planter en cas de serveur indisponible !

import android.util.Log;

import com.example.quentin.rapace_v1.metier.Site;
import com.example.quentin.rapace_v1.metier.Utilisateur;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.util.Vector;

/** Assure la liaison entre l'application et le serveur Rapace */
public class Serveur {
    //@TODO Adapter l'URL selon le serveur utilisé !!!  ! ( ! Changement ICI ! ) !
    /** url du script d'authentification */
    private static final String url_script_authentification = "http://10.0.2.2/~quentin/authentification.php";
    /** url du script listant les sites que surveille un utilisateur a partir de son identifiant */
    private static final String url_script_demander_site_par_utilisateur = "http://10.0.2.2/~quentin/getSites_By_UserId.php";
    /** url du script permettant de lever une alerte */
    private static final String url_script_lever_alerte = "http://10.0.2.2/~quentin/lever_alerte.php";

    /**
     * Envoie une requete permettant d'authentifier un utilisateur enregistre selon son email et son mot de passe.
     * @param email adresse email du client
     * @param password mot de passe du client (non crypte)
     * @return Une instance d'Utilisateur representant l'utilisateur courant (null si l'authentification a echoue, contenant les informations renvoyee par la base de donnees sinon. )
     */
    public static Utilisateur authentifier(String email, String password) {
        String identifiants = preparer_identifiants(email, password);

        MessagePost demande_authentification = new MessagePost(url_script_authentification, identifiants);
        demande_authentification.start();
        attendre_fin(demande_authentification);

        String reponse = demande_authentification.getReponse_serveur();
        // "String reponse" contient une chaine contenant l'ensemble des attributs d'un unique utilisateur ou est nulle.

        Utilisateur utilisateur = null;
        if (!reponse.isEmpty()) {
            utilisateur = new Utilisateur(reponse);
        }

        return utilisateur;
    }

    /**
     * Sous routine s'occupant de la preparation des arguments de la requete d'authentification
     * @param email adresse email du client
     * @param password mot de passe du client (non crypte)
     * @return String formatee exprimant les parametres de notre requete.
     */
    private static String preparer_identifiants(String email, String password) {
        String identifiants = null;
        try {
            identifiants = URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8")
                    + "&" + URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("Error", "Argument mal formé ?");
            e.printStackTrace();
        }
        return identifiants;
    }

    /**
     * Envoie une requete permettant de lister les sites surveilles par un utilisateur.
     * @param id_utilisateur numero identifiant un utilisateur enregistre
     * @return Liste des sites sous la surveillance de l'utilisateur renseigne
     */
    public static Vector<Site> charger_site(String id_utilisateur) {
        Vector<Site> sites = null;
        String id = preparer_requete_site(id_utilisateur);
        StringTokenizer reponse = null;

        MessagePost demande_site_par_utilisateur = new MessagePost(url_script_demander_site_par_utilisateur, id);
        demande_site_par_utilisateur.start();
        attendre_fin(demande_site_par_utilisateur);

        String reponse_serveur = demande_site_par_utilisateur.getReponse_serveur();
        if (!reponse_serveur.isEmpty()) {
            /* reponse_serveur contient l'ensemble des résultant dans une String formatée,
                On utilise StringTokenizer pour la parser et construire chaque Site correctement.
             */

            sites = new Vector<Site>();
            reponse = new StringTokenizer(reponse_serveur);

            int i = 0;
            while (reponse.hasMoreTokens()) {
                String long_token = reponse.nextToken("<");
                sites.add(new Site(long_token));
                i++;
            }

        }

        return sites;
    }

    /**
     * Sous routine s'occupant de preparer les parametres de la requete "liste_site"
     * @param id_utilisateur numero identifiant un utilisateur enregistre
     * @return String exprimant les parametres de la requete
     */
    private static String preparer_requete_site(String id_utilisateur) {
        String parametre_requete = null;
        try {
            parametre_requete = URLEncoder.encode("user_id", "UTF-8")
                    + "=" + URLEncoder.encode(id_utilisateur, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("Error", "Argument mal formé ?");
            e.printStackTrace();
        }
        return parametre_requete;

    }

    /**
     * Envoie d'une requete levant un etat d'alerte pour un site donne
     * @param email adresse email du client
     * @param password mot de passe du client (crypte)
     * @param id_site numero identifiant un utilisateur enregistre
     */
    public static void lever_alerte(String email, String password, String id_site) {
        String parametres = preparer_requete_alerte(email, password, id_site);

        MessagePost demande_levee = new MessagePost(url_script_lever_alerte, parametres);
        demande_levee.start();
        attendre_fin(demande_levee);
    }

    /**
     * Sous routine s'occupant de formater les parametres pour la requete "lever alerte"
     * @param email adresse email du client
     * @param password mot de passe du client (crypte)
     * @param id_site numero identifiant un utilisateur enregistre
     * @return String exprimant les parametres de la requete
     */
    private static String preparer_requete_alerte(String email, String password, String id_site) {
        String identifiants = null;
        try {
            identifiants = URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8")
                    + "&" + URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8")
                    + "&" + URLEncoder.encode("id_site", "UTF-8")
                    + "=" + URLEncoder.encode(id_site, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("Error", "Argument mal formé ?");
            e.printStackTrace();
        }
        return identifiants;
    }

    /**
     * Attendre qu'une requete HTTP obtienne reponse
     * @param messageHttp requete dont on souhaite attendre la reponse
     */
    private static void attendre_fin(MessageHttp messageHttp) {
        try {
            messageHttp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* Pour décoder un message JSON :
    String result = sb.toString();
                        JSONObject jObj = new JSONObject(result);
                        String res = jObj.getString(KEY_SUCCESS);

                            Toast.makeText(getApplicationContext(), "Success Login", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), jObj.getString(KEY_NAME_U), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), jObj.getString(KEY_NAME_S), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),jObj.getString(KEY_PHOTO), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), jObj.getString(KEY_VIDEO), Toast.LENGTH_SHORT).show();


     */
}