#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Table: Role
#------------------------------------------------------------

CREATE TABLE Role(
        id             Int  Auto_increment  NOT NULL ,
        libelle        Varchar (50) NOT NULL ,
        administrateur Bool NOT NULL
	,CONSTRAINT Role_PK PRIMARY KEY (id)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        id_User               Int NOT NULL ,
        nom                   Varchar (50) NOT NULL ,
        prenom                Varchar (50) NOT NULL ,
        date_De_Naissance     Date NOT NULL ,
        presentation          Text NOT NULL ,
        commentaires_Conseils Text NOT NULL ,
        avatar                Varchar (50) NOT NULL,
        id                    Int NOT NULL
	,CONSTRAINT Utilisateur_PK PRIMARY KEY (id_User)

	,CONSTRAINT Utilisateur_Role_FK FOREIGN KEY (id) REFERENCES Role(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Formateur
#------------------------------------------------------------

CREATE TABLE Formateur(
        id_User               Int NOT NULL ,
        id                    Int NOT NULL ,
        nom                   Varchar (50) NOT NULL ,
        prenom                Varchar (50) NOT NULL ,
        date_De_Naissance     Date NOT NULL ,
        presentation          Text NOT NULL ,
        commentaires_Conseils Text NOT NULL ,
        avatar                Varchar (50) NOT NULL
	,CONSTRAINT Formateur_PK PRIMARY KEY (id_User,id)

	,CONSTRAINT Formateur_Utilisateur_FK FOREIGN KEY (id_User) REFERENCES Utilisateur(id_User)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Photo
#------------------------------------------------------------

CREATE TABLE Photo(
        id        Int NOT NULL ,
        nom       Varchar (50) NOT NULL ,
        categorie Varchar (50) NOT NULL ,
        date      Date NOT NULL ,
        id_User   Int NOT NULL
	,CONSTRAINT Photo_PK PRIMARY KEY (id)

	,CONSTRAINT Photo_Utilisateur_FK FOREIGN KEY (id_User) REFERENCES Utilisateur(id_User)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Projet
#------------------------------------------------------------

CREATE TABLE Projet(
        id         Int  Auto_increment  NOT NULL ,
        nom        Varchar (50) NOT NULL ,
        type       Varchar (50) NOT NULL ,
        descriptif Longtext NOT NULL
	,CONSTRAINT Projet_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Hobbies
#------------------------------------------------------------

CREATE TABLE Hobbies(
        nom Varchar (50) NOT NULL
	,CONSTRAINT Hobbies_PK PRIMARY KEY (nom)
)ENGINE=InnoDB;



#------------------------------------------------------------
# Table: Promo
#------------------------------------------------------------

CREATE TABLE Promo(
        nom        Varchar (50) NOT NULL ,
        annee_Fin  Year NOT NULL ,
        specialite Varchar (50) NOT NULL
	,CONSTRAINT Promo_PK PRIMARY KEY (nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Apprenant
#------------------------------------------------------------

CREATE TABLE Apprenant(
        id_User               Int NOT NULL ,
        id                    Int NOT NULL ,
        entite_Affectation    Varchar (50) NOT NULL ,
        nom_Utilisateur       Varchar (50) NOT NULL ,
        prenom                Varchar (50) NOT NULL ,
        date_De_Naissance     Date NOT NULL ,
        presentation          Text NOT NULL ,
        commentaires_Conseils Text NOT NULL ,
        avatar                Varchar (50) NOT NULL ,
        nom                   Varchar (50) NOT NULL
	,CONSTRAINT Apprenant_PK PRIMARY KEY (id_User,id)

	,CONSTRAINT Apprenant_Utilisateur_FK FOREIGN KEY (id_User) REFERENCES Utilisateur(id_User)
	,CONSTRAINT Apprenant_Promo0_FK FOREIGN KEY (nom) REFERENCES Promo(nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Competences
#------------------------------------------------------------

CREATE TABLE Competences(
        nom Varchar (50) NOT NULL
	,CONSTRAINT Competences_PK PRIMARY KEY (nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Concerne
#------------------------------------------------------------

CREATE TABLE Concerne(
        id_User Int NOT NULL ,
        id      Int NOT NULL ,
        nom     Varchar (50) NOT NULL
	,CONSTRAINT Concerne_PK PRIMARY KEY (id_User,id,nom)

	,CONSTRAINT Concerne_Formateur_FK FOREIGN KEY (id_User,id) REFERENCES Formateur(id_User,id)
	,CONSTRAINT Concerne_Promo0_FK FOREIGN KEY (nom) REFERENCES Promo(nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Participe
#------------------------------------------------------------

CREATE TABLE Participe(
        id_User   Int NOT NULL ,
        id        Int NOT NULL ,
        id_Projet Int NOT NULL
	,CONSTRAINT Participe_PK PRIMARY KEY (id_User,id,id_Projet)

	,CONSTRAINT Participe_Apprenant_FK FOREIGN KEY (id_User,id) REFERENCES Apprenant(id_User,id)
	,CONSTRAINT Participe_Projet0_FK FOREIGN KEY (id_Projet) REFERENCES Projet(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Enseigne
#------------------------------------------------------------

CREATE TABLE Enseigne(
        nom     Varchar (50) NOT NULL ,
        id_User Int NOT NULL ,
        id      Int NOT NULL
	,CONSTRAINT Enseigne_PK PRIMARY KEY (nom,id_User,id)

	,CONSTRAINT Enseigne_Competences_FK FOREIGN KEY (nom) REFERENCES Competences(nom)
	,CONSTRAINT Enseigne_Formateur0_FK FOREIGN KEY (id_User,id) REFERENCES Formateur(id_User,id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Detient
#------------------------------------------------------------

CREATE TABLE Detient(
        nom     Varchar (50) NOT NULL ,
        id_User Int NOT NULL ,
        id      Int NOT NULL
	,CONSTRAINT Detient_PK PRIMARY KEY (nom,id_User,id)

	,CONSTRAINT Detient_Competences_FK FOREIGN KEY (nom) REFERENCES Competences(nom)
	,CONSTRAINT Detient_Apprenant0_FK FOREIGN KEY (id_User,id) REFERENCES Apprenant(id_User,id)
)ENGINE=InnoDB;

