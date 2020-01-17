insert into fil_rouge.role ( libelle, administrateur) values ('Apprenant', true), ('Formateur', true), ('Apprenant', false);



insert into fil_rouge.utilisateur (Nom, Prenom, Date_De_Naissance, Presentation, Commentaires_Conseils, Avatar, id_role)values
('LOUIS', 'Laetitia', '1975-09-26', 'Je m\'appelle Laetita', 'Ne faites pas ça', 1, 3),
('MERCHADOU', 'Sébastien', '1990-01-01', 'Je suis un panda', 'It\'s a trap !!!!', 1, 3 ),
('TOBELEM', 'Josselin', '1980-09-12', 'Formateur à Simplon Montreuil', 'La promo 4 est la meilleur', 1, 2),
('BOUGET', 'Philippe', '1970-09-12', 'Formateur à Simplon Montreuil', 'La promo 4 est la meilleur', 1, 2),
('MAUNIER', 'Cédric', '1982-03-14',  'Jeune Papa introverti', 'Parle moins fort', 1, 3),
('DEFOORT', 'Maureen', '1996-12-07', 'Je suis une gloutonne', 'Je connais tous les Kebabs et Otacos', 1, 1);

insert into fil_rouge.photo ( nom, categorie, date, id_User) values ('Laetitia', 'profil', '2020-01-09', 1),
                                                          ('Sebastien', 'profil', '2020-01-09', 2),
                                                          ('Josselin', 'profil', '2020-01-09', 3);

insert into fil_rouge.promo (nom_promo, annee_Fin, specialite) values ('promo3', '2019', 'developpeur web et web mobile'),
                                                          ('promo4', '2020', 'developpeur web et web mobile');

insert into fil_rouge.projet ( nom, type, descriptif) values ('projet trombi', 'site web', 'Site représentant les differentes promo simplon'),
                                                          ('game in love', 'site de rencontre', 'site de rencontre pour les gamers'),
                                                          ('NL site', 'site web', 'Site web de dessins en batiments');
insert into fil_rouge.hobbies (nom_hobbies) values ('Running'), ('Dessin'), ('Danse'), ('Théatre'), ('Cuisine'),('Manger');
insert into fil_rouge.competences (nom_competences) values ('SQL'), ('JAVA'), ('HTML'), ('JavaScript'), ('Angular'), ('CSS'), ('SpringBoot');
