--Roles
INSERT INTO role (libelle, admin) values 
('administrateur', true), 
('utilisateur',FALSE);


-- Utilisateurs 
INSERT INTO utilisateur ( nom, prenom, date_de_naissance, presentation, commentaires, avatar_url, email, pseudo,mot_de_passe, role_id)  VALUES 
( 'maunier', 'cédric', '1983/05/05','passioné de dev','Simple commentaire','users.jpeg',  'cedric.maunier.lp4@gmail.com','drikc','changeme', 1),
( 'tobelem', 'josselin','1984/12/01','formateur émérite','Commentaire de Josselin','users.jpeg', 'jtobelem@simplon.co','jtobelem','password', 1);


--Promo
insert into promo (nom, annee_fin, specialite) values 
('LP4', '2020', 'Dev Web'),
('LP3', '2019', 'Dev Web');

--Apprenants
INSERT INTO apprenant (entite_affectation, utilisateur_id, promo_nom) VALUES ('Gradignan', 1,'LP4');


-- Formateurs
INSERT INTO formateur (utilisateur_id) VALUES (2);

--Photos
INSERT into photo (nom, categorie, date_photo, image_url,utilisateur_id) values 
('Photo Cédric','evenement', '2020/01/22', 'image.jpg',1),
('Galette des rois','Convivialité', '2020/01/04', 'image.jpg',1);

--Lien Utilisateur/photos
insert into utilisateur_photos (utilisateur_id, photos_id) values 
(1,1),
(1,2);



--Promo apprenant
insert into promo_apprenants(promo_nom, apprenant_id) values
('LP4', 1);

--Promo formateur
insert into promo_formateurs(promo_nom, formateur_id) values
('LP4', 2),
('LP3', 2);

--Projet
insert into projet (nom, type_projet, descriptif) values
('Fil-rouge', 'projet', 'Projet Fullstack collectif'),
('Injections SQL', 'veille', 'Failles de sécurité SQL');

--HobbyCompetencceLangage
insert into hobby(nom, type_hobby) values
('Footing', 'Hobby'),
('Travail en équipe', 'Compétence'),
('Java', 'Langage');

--Utilisateur hobbyCompetenceLangange
insert into utilisateur_hobby (utilisateur_id, hobby) values
(1,'Footing'),
(1, 'Java');




