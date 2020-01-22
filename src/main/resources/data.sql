--Roles
INSERT INTO role (libelle, admin) values 
('administrateur', true), 
('utilisateur',FALSE);


-- Utilisateurs 
INSERT INTO utilisateur ( nom, prenom, date_de_naissance, presentation, commentaires, avatar_url, email, pseudo,mot_de_passe, role_id)  VALUES 
( 'maunier', 'cédric', '1983/05/05','passioné de dev','Simple commentaire','users.jpeg',  'cedric.maunier.lp4@gmail.com','drikc','changeme', 1),
( 'tobelem', 'josselin','1984/12/01','formateur émérite','Commentaire de Josselin','users.jpeg', 'jtobelem@simplon.co','jtobelem','password', 1);

--Apprenants
INSERT INTO apprenant (entite_affectation, utilisateur_id) VALUES ('Gradignan', 1);


-- Formateurs

INSERT INTO formateur (utilisateur_id) VALUES ('Propriété test', 2);

--Photos
INSERT into photo (nom, categorie, date_photo, image_url,utilisateur_id) values 
('Photo Cédric','evenement', '2020/01/22', 'image.jpg',1),
('Galette des rois','Convivialité', '2020/01/04', 'image.jpg',1);

--Lien Utilisateur/photos
insert into utilisateur_photos (utilisateur_id, photos_id) values 
(1,1),
(1,2);

