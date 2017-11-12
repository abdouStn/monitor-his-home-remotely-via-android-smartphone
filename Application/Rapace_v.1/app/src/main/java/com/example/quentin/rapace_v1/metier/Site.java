package com.example.quentin.rapace_v1.metier;

import android.util.Log;

import java.util.StringTokenizer;

/** Classe representant un site sous surveillance. */
public class Site {
    /** identifiant du site dans la base de donnee */
    private int id = -1;
    /** nom du site */
    private String nom = "non renseigne";
    /** adresse du site */
    private String adresse = "non renseigne";
    /** descriptif du site */
    private String descriptif = "non renseigne";
    /** url de la video du site */
    private String url = "non renseigne";
    /** etat d'alerte du site */
    private String etat_alerte = "non renseigne";

    /**
     * Constructeur
     */
    public Site(){}

    /**
     * Constructeur
     * @param nom Nom du site.
     * @param url Url du site.
     */
    public Site(String nom, String url){
        this.nom = nom;
        this.url= url;
    }

    /**
     * Constructeur
     * @param data String contenant le réponse (formatee) du serveur.
     */
    public Site(String data) {
        // On utilise une instance de StringTokenizer pour parser la réponse du serveur.
        StringTokenizer strtok = null;
        try {
            strtok = new StringTokenizer(data, " ");
        } catch (NullPointerException e) {
            /* Normalement il n'est pas nécéssaire de catcher une RunTimeException, mais écrire un Log
	       et quitter la fonction ici peut-être utile (aide à comprendre en cas d'erreur!)
	    */
            Log.e("Bad Data", "Impossible d'instancier Utilisateur à partir de null.");
        }
        // SI l'exécution atteint ce point, alors strtok est non nul, autrement NullPointerException aurait été levée.
            String str = null;
        str = strtok.nextToken(">");
            id = Integer.parseInt(str);

        str = strtok.nextToken(">");
   //     Log.v("str : ", str);
        nom = str;

        str = strtok.nextToken(">");
     //   Log.v("str : ", str);
        adresse = str;

        str = strtok.nextToken(">");
       // Log.v("str : ", str);
        descriptif = str;

        str = strtok.nextToken(">");
       // Log.v("str : ", str);
        url = str;

        str = strtok.nextToken(">");
       // Log.v("etat d'alerte : ", str);
        etat_alerte = str;
    }

    /**
     * Accesseur vers l'attribut id
     * @return l'attribut id.
     */
    public int getId() {
        return id;
    }

    /**
     * Accesseur vers l'attribut nom
     * @return l'attribut nom
     */
    public String getNom(){
        return nom;
    }

    /**
     * Accesseur vers l'attribut adresse
     * @return l'attribut adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Accesseur vers l'attribut descriptif
     * @return l'attribut descriptif
     */
    public String getDescriptif(){
        return descriptif;
    }

    /**
     * Accesseur vers l'attribut url
     * @return l'attribut url
     */
    public String getUrl() {
        return  url;
    }

    /**
     * Accesseur vers l'attribut etat_alerte
     * @return l'attribut etat_alerte
     */
    public String getEtat_alerte () { return etat_alerte ;}

    /**
     * Mutateur vers l'attribut id
     * @param id nouvelle valeur de l'attribut id
     */
    public void setId(int id) {
        this.id=id;
    }

    /**
     * Mutateur vers l'attribut nom
     * @param nom_site nouvelle valeur de l'attribut nom
     */
    public void setNom(String nom_site) {
        this.nom = nom_site;
    }

    /**
     * Mutateur vers l'attribut adresse
     * @param adresse nouvelle valeur de l'attribut adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Mutateur vers l'attribut descriptif
     * @param descriptif nouvelle valeur de l'attribut descriptif
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    /**
     * Mutateur vers l'attribut url
     * @param url_video nouvelle valeur de l'attribut url
     */
    public void setUrl(String url_video) {
        this.url = url_video;
    }

    /**
     * Mutateur vers l'attribut etat_alerte
     * @param etat_alerte nouvelle valeur de l'attribut etat_alerte
     */
    public void setEtat_alerte(String etat_alerte) {
        this.etat_alerte = etat_alerte;
    }

    /**
     * Rassemble les données d'un site dans une String.
     * @return String présentant l'ensemble des données d'un site.
     */
    public String toString(){
        return "ID_SITE : "+ id +"\nNOM_SITE : " + nom + "\nAdresse : " + adresse + "\nURL_VIDEO : " + url + "\nDESCRIPTIF : "
                + descriptif + "\nEtat : " + etat_alerte;
    }
}