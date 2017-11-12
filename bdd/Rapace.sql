-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 14 Avril 2015 à 09:25
-- Version du serveur :  5.6.21
-- Version de PHP :  5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `Rapace`
--

-- --------------------------------------------------------

--
-- Structure de la table `Site`
--

CREATE TABLE IF NOT EXISTS `Site` (
`id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `descriptif` text NOT NULL,
  `url` varchar(255) NOT NULL,
  `en_alerte` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Site`
--

INSERT INTO `Site` (`id`, `nom`, `adresse`, `descriptif`, `url`, `en_alerte`) VALUES
(1, 'maison principale', '742 Evergreen Terrace à Springfield', 'Une maison rose et peuplé d''êtres jaunes.', 'http://url.com', 0),
(2, 'Pandora', '123 rue de l''espace', 'Une planète peuplée de grand être bleu.', 'http://url.com', 0),
(3, 'Titanic', '505 boulevard des poissons', 'Un navire réputé incoulable.', 'http://url.com', 1),
(4, 'Tatooine', '56 rue de l''espace', 'Une planète célèbre pour ses courses de modules.', 'http://url.com', 0),
(5, 'Ville d''Halloween', '1 boulevard l''effroi', 'Une ville hébergeant un squelette excentrique.', 'http://url.com', 0),
(6, 'Jurassik Park', '86 rue des lézard', 'Un parc d''attraction avec des gros lézards.', 'http://url.com', 0);

-- --------------------------------------------------------

--
-- Structure de la table `Surveille`
--

CREATE TABLE IF NOT EXISTS `Surveille` (
  `id_utilisateur` int(11) NOT NULL,
  `id_site` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Surveille`
--

INSERT INTO `Surveille` (`id_utilisateur`, `id_site`) VALUES
(2, 5),
(2, 5),
(3, 6),
(4, 3),
(4, 2),
(5, 4),
(6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE IF NOT EXISTS `Utilisateur` (
`id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `psswd` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id`, `nom`, `prenom`, `psswd`, `email`) VALUES
(2, 'Burton', 'Tim', '$2y$10$bBx4oGiWyv8/SOo26oqKM.1W2myD719uOmp9pRxc26NB2WczlXeuO', 'tim.burton@rapace.com'),
(3, 'Spielberg', 'Steven', '$2y$10$aRKglUJoX0gnmUtZdgvWc.YXr83EGRFiQC33Wwufz58NAAJe8Bq/W', 'steven.spielberg@rapace.com'),
(4, 'Cameron', 'James', '$2y$10$Ro.Ixd1Zq0FA8YucB6k4..3.H74JIuyYF/I3DkV3gGAH5m56qNYKy', 'james.cameron@rapace.com'),
(5, 'Lucas', 'George', '$2y$10$V6KBOTm/e1zlobRui.dB.uAawD0svxLkKei8s/8mSfECJi0B0WQmW', 'george.lucas@rapace.com'),
(6, 'Groening', 'Matt', '$2y$10$okC/2z7Gwnf.LLnjZXbLLuiaD9GCTjA0F6RDFYJh4BdYhPk0fxegS', 'matt.groening@rapace.com');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Site`
--
ALTER TABLE `Site`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Site`
--
ALTER TABLE `Site`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
