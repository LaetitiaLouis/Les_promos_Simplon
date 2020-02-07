
-- Role
INSERT INTO role (libelle, admin) VALUES 
('administrateur', true), 
('utilisateur', false);


-- Utilisateurs
INSERT INTO utilisateur (nom, prenom, date_de_naissance, presentation, commentaires, email, mot_de_passe, pseudo, role_id)  VALUES 
( 'maunier', 'cédric', '1983/05/05','passioné de dev','Simple commentaire', 'cedric.maunier.lp4@gmail.com','changeme','drikc', 1),
( 'tobelem', 'josselin','1984/12/01','formateur émérite','Commentaire de Josselin', 'jtobelem@simplon.co','password','jtobelem', 2),
( 'louis', 'laëtitia', '1979/06/12','véritable bordelaise','Commentaire de Laëtitia', 'laetitia.louis.lp4@gmail.com','motdepasse','léti', 1),
( 'Merchadou', 'Sébastien', '1984/02/20','cuisine','commentaire panda', 'merchadou.sebastien@gmail.com','mdpadmin','pandi', 1),
( 'Philipe', 'phil','1984/12/01','formateur émérite','Commentaire de Philippe', 'philippe@simplon.co','password','phil', 2),
( 'StarJS', 'StarNode', '1979/06/12','véritable bcodeur','Commentaire de StarJS', 'StarJs.lp4@gmail.com','motdepasse','starjs', 1),
( 'balcon', 'sophie', '1985/01/17','passioné de moto','Simple commentaire', 'sophie.balconlp4@gmail.com','changeme','sbalcon', 1),
( 'bensaid', 'Anissa','1989/12/01','Cheffe de projet','Commentaire d\'anissa', 'abensaid@simplon.co','password','abensaid', 2),
( 'test', 'test', '1979/06/12','tttttttteeeeeeeessssssssssssssssssssttttttttttttttttttt test test tests tettetetetessssttt','Commentaireeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee de commentaireeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee', 'commentaire.lp4@gmail.com','motdepasse','commentaire', 1);

-- Promos
insert into promo (nom, annee_fin, specialite) values 
('LP1', '2017', 'webdev'),
('LP2', '2018', 'webdev'),
('LP3', '2019', 'webdev'),
('LP4', '2020', 'webdev'),
('LP1S', '2020', 'cybersecu');

--Apprenants
INSERT INTO apprenant (entite_affectation, utilisateur_id, promo_nom) VALUES 
('Gradignan', 1, 'LP4'),
('Gradignan', 4, 'LP4'),
('Paris', 6, 'LP4'),
('Nantes', 7, 'LP4'),
('Paris', 9, 'LP4'),
('Gradignan', 3, 'LP4');


-- Formateurs
INSERT INTO formateur (utilisateur_id) VALUES (2),(5),(8);

--Photos
INSERT into photo (nom, categorie, date_photo, image_url,utilisateur_id) values 
('Photo Cédric','evenement', '2020/01/22', 'image.jpg',1),
('Photo Laetitia','evenement', '2020/01/22', 'image.jpg',3),
('Photo Seb','evenement', '2020/01/22', 'image.jpg',4),
('Photo StarJs','evenement', '2020/01/22', 'image.jpg',6),
('Photo Sophie','evenement', '2020/01/22', 'image.jpg',7),
('Photo Commentaire','evenement', '2020/01/22', 'image.jpg',9),
('Photo Phil','evenement', '2020/01/22', 'image.jpg',5),
('Photo Josslein','evenement', '2020/01/22', 'image.jpg',2),
('Galette des rois','Convivialité', '2020/01/04', 'image.jpg',3);

--Lien Utilisateur/photos
insert into utilisateur_photos (utilisateur_id, photos_id) values 
(1,1),
(3,2),
(4,3),
(6,4),
(7,5),
(9,6),
(5,7),
(2,8),
(3,9);

--Promo apprenant
insert into apprenant_promo(promo_id, apprenant_id) values
('LP4', 1),
('LP4', 3),
('LP4', 4),
('LP4', 6),
('LP4', 7),
('LP4', 9);

--Promo formateur
insert into formateur_promos(promo_id, formateur_id) values
('LP4', 2),
('LP3', 2),
('LP4', 5),
('LP4', 8),
('LP3', 5);

--Projet
insert into projet (nom, type_projet, descriptif) values
('Fil-rouge', 'projet', 'Projet Fullstack collectif'),
('Nanotechnologie', 'veille', 'Les Nanotechnologies, comment ça marche ?'),
('IA', 'veille', 'Présentation des IA'),
('Puissance4', 'projet', 'Faire un puissance 4 en JAVA'),
('Injections SQL', 'veille', 'Failles de sécurité SQL');

--HobbyCompetencceLangage
insert into hobby(nom, type_hobby) values
('Footing', 'Hobby'),
('Travail en équipe', 'Compétence'),
('Java', 'Langage'),
('Javascript', 'Langage'),
('TypeScript', 'Langage'),
('HTML', 'Langage'),
('CSS', 'Langage'),
('Angular', 'Langage');

--Utilisateur hobbyCompetenceLangange
insert into utilisateur_hobby (utilisateurs_id, hobby) values
(1,'Footing'),
(1, 'CSS'),
(3, 'Travail en équipe'),
(4, 'Java'),
(1, 'Travail en équipe'),
(9, 'Javascript');

--Apprenant projets
insert into apprenant_projet (apprenant_id, projet_id) values
(1, 'Fil-rouge'),
(3, 'Fil-rouge'),
(4, 'Fil-rouge'),
(3, 'Puissance4'),
(4, 'Puissance4'),
(7, 'Nanotechnologie'),
(9, 'IA'),
(7, 'IA'),
(1, 'Injections SQL'),
(3, 'Injections SQL');
--Projet langages
insert into langage_projet (projet_id, langage_id) values
('Fil-rouge', 'Java'),
('Fil-rouge', 'CSS'),
('Fil-rouge', 'Javascript'),
('Puissance4', 'Java'),
('Fil-rouge', 'Angular');



