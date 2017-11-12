<?php

/* Remarque : Le client doit tester si la chaine est vide, et ne pas l'envoyer, afin de ne pas surcharger le serveur de requetes inutiles,
   mais le serveur doit quand meme tester si les parametres sont non nuls par mesure de sécurité. */

//TODO Modifier ici pour accéder à votre propre bdd
$bdd = new PDO('mysql:host=localhost;dbname=Rapace', 'login', 'mot de passe',
	       array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,

		     ));

// Analyse des données reçues : //


if (!empty(trim($_REQUEST['email'])) and  !empty(trim($_REQUEST['password'])) // trim supprime les espaces de début et fin, empty <-> (!isset and ==false), en gros on n'accède pas à la bdd si le login ou/et le password sont sous la forme : " " .
    ) {
  $email = $_REQUEST['email'];
  $password = $_REQUEST['password'];
  $query = $bdd->prepare("SELECT * FROM Utilisateur WHERE email= :email");
  $query->bindParam(':email', $email, PDO::PARAM_STR);
  $query->execute();
  $answer = $query->fetch(PDO::FETCH_ASSOC);
  if ( password_verify($password, $answer['psswd']) ) {
    foreach ($answer as $value) {
      print $value . " "; 
    }
  }
}

?>
