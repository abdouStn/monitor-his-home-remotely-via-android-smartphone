<!-- Page HTML implémentée pour faciliter l'insertion d'un utilisateur dans la bdd Rapace (cryptage automatique du mdp) -->
<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8" />
</head>
<body>
<p>Insertion d'un utilisateur dans la base de données :</p>
<form method="post" action="admin_insert_user.php">
<table>
<tr><td><label for="nom">Nom :</label></td><td><input type="text" name="nom" id="nom" /></td></tr>
<tr><td><label for="prenom">Prenom:</label></td><td><input type="text" name="prenom" id="prenom" /></td></tr>
<tr><td><label for="login">Login :</label></td><td><input type="text" name="login" id="login" /></td></tr>
<tr><td><label for="psswd">Mot de passe :</label></td><td><input type="text" name="psswd" id="psswd" /></td></tr>
<tr><td><label for="email">Adresse e-mail :</label></td><td><input type="text" name="email" id="email" /></td></tr>
<tr><td><input type="submit" value="inserer"/></td></tr>
</table>
</form>
</body>
   
<?php

/* Remarque : Le client doit tester si la chaine est vide, et ne pas l'envoyer, afin de ne pas surcharger le serveur de requetes inutiles,
   mais le serveur doit quand meme tester si les parametres sont non nuls par mesure de sécurité. */

$bdd = new PDO('mysql:host=localhost;dbname=Rapace', 'root', 'strxi3qa',
	       array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
		     ));

// Analyse des données reçues : //
if (!empty(trim($_REQUEST['nom'])) and
    !empty(trim($_REQUEST['prenom'])) and
    !empty(trim($_REQUEST['psswd'])) and
    !empty(trim($_REQUEST['email']))
// trim supprime les espaces de début et fin, empty <-> (!isset and ==false), en gros on n'accède pas à la bdd si le login ou/et le password sont sous la forme : " " .
    ) {
  $query = $bdd->prepare("INSERT INTO Utilisateur (nom, prenom, psswd, email) VALUES (:nom, :prenom, :psswd, :email)");
  $query->bindParam(':nom', $_REQUEST['nom'], PDO::PARAM_STR);
  $query->bindParam(':prenom', $_REQUEST['prenom'], PDO::PARAM_STR);
  $query->bindParam(':psswd', password_hash($_REQUEST['psswd'],PASSWORD_DEFAULT), PDO::PARAM_STR);
  $query->bindParam(':email', $_REQUEST['email'], PDO::PARAM_STR);
  $query->execute();
 
} ?>
</html>
