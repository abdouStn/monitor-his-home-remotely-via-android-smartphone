<?php
if (!empty($_POST['email']) and 
    !empty($_POST['password']) and
    !empty($_POST['id_site'])) {

//TODO Modifier ici pour accéder à votre propre bdd
  $bdd = new PDO('mysql:host=localhost;dbname=Rapace', 'login', 'mot de passe');

  $statement = $bdd->prepare('
UPDATE Site, Utilisateur, Surveille
SET en_alerte = 0
WHERE Site.id = :id_site
AND Utilisateur.email = :email
AND Utilisateur.psswd = :password
AND Utilisateur.id = Surveille.id_utilisateur
AND Surveille.id_site = Site.id;
');


  $statement->bindParam(':email', $_POST['email'], PDO::PARAM_STR);
  // Remarque : Le mot de passe reçu correspond au mot de passe CRYPTE !
  //      on peut donc le passer tel quel en parametre.
  $statement->bindParam(':password', $_POST['password'], PDO::PARAM_STR);
  $statement->bindParam(':id_site', $_POST['id_site'], PDO::PARAM_INT);

  $statement->execute();
}
?>
