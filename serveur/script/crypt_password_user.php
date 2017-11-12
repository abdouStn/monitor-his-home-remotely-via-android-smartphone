<!-- Outil permettant de crypter un mot de passe -->
<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8" />
</head>
<body>
<p>Encrypate du mot de passe d'un utilisateur dans la base de données :</p>
<form method="post" action="crypt_password_user.php">
<table>
<tr><td><label for="id">mdp :</label></td><td><input type="text" name="id" id="id" /></td></tr>
<tr><td><input type="submit" value="inserer"/></td></tr>
</table>
</form>
</body>
   
<?php
print password_hash($_REQUEST['id'],PASSWORD_DEFAULT);
 
 ?>
</html>
