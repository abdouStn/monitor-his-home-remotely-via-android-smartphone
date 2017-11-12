package com.example.quentin.rapace_v1.metier;

import android.util.Log;
import java.util.StringTokenizer;

/** Classe representant un utilisateur inscrit */
public class Utilisateur {
    /** identifiant dans la base de donnees */
    private int id = -1;
    /** nom de l'utilisateur */
    private String nom = "non renseigné";
    /** prenom de l'utilisateur */
    private String prenom = "non renseigné";
    /** mot de passe (crypte) de l'utilisateur */
    private String psswd = "non renseigné";
    /** adresse email de l'utilisateur */
    private String email = "non renseigné";

    /**
     * Constructeur
     */
    public Utilisateur(){}

    /**
     * Constructeur
     * @param data Reponse formatee du serveur.
     */
    public Utilisateur(String data) {
        //On utilise une instance de StringTokenize pour parser notre réponse :
        StringTokenizer strtok = null;
        try {
            strtok = new StringTokenizer(data, " ");
        } catch (NullPointerException e) {
            /* Normalement il n'est pas nécéssaire de catcher une RunTimeException, mais écrire un Log
	       et quitter la fonction ici peut-être utile (aide à comprendre en cas d'erreur!)
	    */
            Log.w("Bad Data", "Impossible d'instancier Utilisateur à partir de null.");
        }
        // SI l'exécution atteint ce point, alors strtok est non nul, autrement NullPointerException aurait été levée.


            id = Integer.parseInt(strtok.nextToken());
            nom = strtok.nextToken();
            prenom = strtok.nextToken();
            psswd = strtok.nextToken();
            email = strtok.nextToken();

     }

    /**
     * Accesseur vers l'identifiant de l'utilisateur
     * @return identiant de l'utilisateur
     */
    public int getId() {
	return id;
    }

    /**
     * Accesseur vers le nom de l'utilisateur
     * @return nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur vers le prenom de l'utilisateur
     * @return prenom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Accesseur vers le mot de passe (crypte) de l'utilisateur
     * @return mot de passe (crypte) de l'utilisateur
     */
    public String getPsswd() {
        return psswd;
    }

    /**
     * Accesseur vers l'adresse email de l'utilisateur
     * @return adresse email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mutateur vers l'identifiant de l'utilisateur
     * @param v nouvelle valeur de l'identifiant
     */
    public void setId(int v) {
        // On ne veut pas d'ID négatif, cela n'existe pas dans nos tables !
        if (v > 0) {
            id = v;
        }
    }

    /**
     * Mutateur vers le nom de l'utilisateur
     * @param v nouvelle valeur du nom
     */
    public void setNom(String v) {
        nom = v;
    }

    /**
     * Mutateur vers le prenom de l'utilisateur
     * @param v nouvelle valeur du prenom
     */
    public void setPrenom(String v) {
        prenom = v;
    }

    /**
     * Mutateur vers le mot de passe (crypte) de l'utilisateur
     * @param v nouvelle valeur du mot de passe (crypte) de l'utilisateur
     */
    public void setPsswd(String v) {
        psswd = v;
    }

    /**
     * Mutateur vers l'adresse email de l'utilisateur
     * @param v nouvelle valeur de l'adresse email de l'utilisateur
     */
    public void setEmail(String v) {
        //@TODO Exrepression régulière (filtre)
        email = v;
    }

    /**
     * Rassemble les donnees de l'utilisateur dans une chaine de caracteres.
     * @return Descriptif des donnees de l'utilisateur.
     */
    public String toString() {
	return "[id] " + getId() + "\n[nom/prenom] " + getNom() + " " + getPrenom() + "\n[email] " + getEmail();
    }
}
