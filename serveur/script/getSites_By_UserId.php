<?php

/* Remarque : Le client doit tester si la chaine est vide, et ne pas l'envoyer, afin de ne pas surcharger le serveur de requetes inutiles,
   mais le serveur doit quand meme tester si les parametres sont non nuls par mesure de sécurité. */

//TODO Modifier ici pour accéder à votre propre bdd
$bdd = new PDO('mysql:host=localhost;dbname=Rapace', 'login', 'mot de passe',
	       array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,

		     ));

// Analyse des données reçues : //
if (!empty((int)htmlentities($_REQUEST['user_id']))) { // Les id sont sous forme d'int, alors on teste le parametre est sous forme de numéro.
  $user_id = (int)$_REQUEST['user_id']; // Néanmoins nous voulons 
   $query = $bdd->prepare("SELECT DISTINCT Site.* " .
			  "FROM Site " .
			  "JOIN Surveille " .
			  "ON Site.id = Surveille.id_site ".
			  "WHERE Surveille.id_utilisateur=" . $user_id
			  );
   $query->execute();

   while ($answer = $query->fetch(PDO::FETCH_ASSOC)) {
	foreach ($answer as $value) {
//Les chevrons servent au formatage de la reponse : '>' = "fin de l'attribut"
      print $value . '>'; 
    }
print '<'; // '<' = "fin de la ligne"
  }
}
else {
  print null; // On renvoi null à l'application cliente.
}
?>
