## APPLICATION RAPACE

###	INSTALLATION DE L'APPLI

* Installer la base de donnée Rapace (cf. bdd/notice.txt).

* Installer les scripts Rapace sur votre serveur (cf. server/notice.txt).

* Renommer le fichier "Application/Rapace_v1/gradlew.bat.txt" en ".../gradlew.bat"

* Ouvrez l'application avec AndroidStudio et opérer les modifications suivantes :

	- Allez dans le package rapace.network
	- Sélectionnez la classe serveur
	- remplacer l'url des scripts par l'emplacement des scrips sur votre serveur.


#### Rappel: Pour envoyer des sms avec l'émulateur android il faut :

* se connecter en ligne de commande sur le port correspondant à la machine virtuelle :

	     - on utilise la commande : telnet #serveur #port

         dans notre cas cela donne : telnet localhost 5554
         
		 Remarque : le numéro de port utilisé par votre machine virtuelle est indiqué dans le coin supérieur gauche de votre fenêtre contenant la machine virtuelle.

* on peut alors utiliser les commandes livrées avec l'émulateur android :

	      - on utilise la commande : sms send #num_telephonne #contenu_message

          Par exemple : sms send 007 "Mon nom est Bond, James Bond"

          Se traduit par la réception chez notre machine virtuelle d'un message provenant du numéro "007" contenant "Mon nom est Bond, James Bond".


### Bugs connus :

* L'application se ferme lorsque je clique sur le bouton authentifier.
  - Vérifiez que votre serveur soit lancé.
  - Il se peut que l'erreur soit dû à un problème de compatibilité si vous utilisez une API < 10, préférez l'API 15 ou 21.

* Impossible de lire une vidéo en streaming.
  - Vérifier que le flux soit valide (via vlc par exemple).
  - S'il s'agit de lecture continue, notre lecteur à encore du mal à les supporter.

* L'application se ferme lorsque je quitte l'activité Lecture_Streaming et que je reviens à l'activité Afficher_Liste_Site :
  - C'est un problème connu et rencontré uniquement avec l'API 17, préferez l'API 15 ou 21 pour lesquels aucun problème n'est connu.


### AUTHORS

Abdoulaye DIALLO

Quentin PHILIPPOT

Jihen Fourati

Redoine EL OUASTI


### Supervisor

Abdelhak SERIAI (LIRMM - Laboratoire d’Informatique, de Robotique et de Microélectronique de Montpellier)

### DATE
Mai 2015
