
-- Role
INSERT INTO role (libelle, admin) VALUES 
('administrateur', true), 
('utilisateur', false);


-- Utilisateurs
INSERT INTO utilisateur (nom, prenom, date_de_naissance, presentation, commentaires, email, mot_de_passe, pseudo, role_id, avatar_url)  VALUES 
( 'maunier', 'cédric', '1983/05/05','Passionné par l\'informatique et la tech depuis toujours, j\'ai décidé de me reconvertir dans le développement: je me suis donc formé en autodidacte aux principaux langages web: HTML, CSS, JAVASCRIPT sur différentes plateforme d\'apprentissage(ex: FreeCodeCamp, YouTube ...).
J\'héberge certains de mes projets sur Github et suis aujourd\'hui à même de concevoir tous types d\'applications Web avec ReactJS que je maîtrise depuis quelques mois.Je suis aussi à l\'aise avec les serveurs Linux que j\'utilise quotidiennement car la curiosité et mon côté geek m\'ont poussé à comprendre leur fonctionnement au fil du temps.
Je me sens aujourd\'hui prêt à entrer dans le monde professionnel et transformer ma passion en métier. C\'est pourquoi je viens de créer ma micro\-entreprise afin de d\'obtenir mes premières missions en tant que freelance.','Simple commentaire', 'cedric.maunier.lp4@gmail.com','changeme','drikc', 1, 'http://localhost:8080/api/photos/download/1_Cédric.jpg' ),
( 'tobelem', 'josselin','1984/12/01','formateur émérite','Commentaire de Josselin', 'jtobelem@simplon.co','password','jtobelem', 2, 'http://localhost:8080/api/photos/download/14_Josselin.jpg'),
( 'louis', 'laëtitia', '1979/06/12','véritable bordelaise','Commentaire de Laëtitia', 'laetitia.louis.lp4@gmail.com','motdepasse','léti', 1, 'http://localhost:8080/api/photos/download/2_Laëtitia.jpg'),
( 'Merchadou', 'Sébastien', '1984/02/20','cuisine','commentaire panda', 'merchadou.sebastien@gmail.com','mdpadmin','pandi', 1,'http://localhost:8080/api/photos/download/3_Sébastien.jpg' ),
( 'Bouget', 'Philippe','1984/12/01','formateur émérite','Commentaire de Philippe', 'philippe@simplon.co','password','phil',2, 'http://localhost:8080/api/photos/download/15_Philippe.jpg'),
( 'StarJS', 'StarNode', '1979/06/12','véritable bcodeur','Commentaire de StarJS', 'StarJs.lp4@gmail.com','motdepasse','starjs', 1, 'http://localhost:8080/api/photos/download/4_François.jpg'),
( 'balcon', 'sophie', '1985/01/17','passioné de moto','Simple commentaire', 'sophie.balconlp4@gmail.com','changeme','sbalcon', 1, 'http://localhost:8080/api/photos/download/5_Sophie.jpg'),
( 'bensaid', 'Anissa','1989/12/01','Chef de projet','Commentaire d\'anissa', 'abensaid@simplon.co','password','abensaid', 2, 'http://localhost:8080/api/photos/download/13_Anissa.jpg'),
( 'unknown', 'Maureen', '1979/06/12','tettetetetessssttt','Commentaireeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee', 'commentaire.lp4@gmail.com','motdepasse','commentaire', 1, 'http://localhost:8080/api/photos/download/12_Maureen.jpg');

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
('Cédric','profil', '2020/01/22', 'http://localhost:8080/api/photos/download/1_Cédric.jpg',1),
('Laetitia','profil', '2020/01/22', 'http://localhost:8080/api/photos/download/2_Laëtitia.jpg',3),
('Sébastien','profil', '2020/01/22', 'http://localhost:8080/api/photos/download/3_Sébastien.jpg',4),
('François','profil', '2020/01/22', 'http://localhost:8080/api/photos/download/4_François.jpg',6),
('Sophie','profil', '2020/01/22', 'http://localhost:8080/api/photos/download/5_Sophie.jpg',7),
('La P4','groupe', '2020/01/22', 'http://localhost:8080/api/photos/download/6_La_P4.jpg',9),
('Inauguration','groupe', '2020/01/22', 'http://localhost:8080/api/photos/download/7_Le_jour_du_ministre.jpg',5),
('Tous au bar','groupe', '2020/01/22', 'http://localhost:8080/api/photos/download/8_Tous_au_bar.jpg',2),
('Café & Stylo','travail', '2020/01/04', 'http://localhost:8080/api/photos/download/9_Café_&_Stylo.jpg',3),
('Outil de travail', 'travail', '2020/01/02', 'http://localhost:8080/api/photos/download/10_Outil_de_travail.jpg', 2),
('Le nez dedans', 'travail', '2020/01/05','http://localhost:8080/api/photos/download/11_Le_nez_dedans.jpg', 2),
('Maureen', 'profil', '2020/01/05','http://localhost:8080/api/photos/download/12_Maureen.jpg', 9),
('Anissa', 'profil', '2020/01/05','http://localhost:8080/api/photos/download/13_Anissa.jpg', 8),
('Josselin', 'profil', '2020/02/05', 'http://localhost:8080/api/photos/download/14_Josselin.jpg',2),
('Philippe', 'profil', '2020/02/05', 'http://localhost:8080/api/photos/download/15_Philippe.jpg',5);

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



