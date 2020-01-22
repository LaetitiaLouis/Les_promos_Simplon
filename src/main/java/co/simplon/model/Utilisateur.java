package co.simplon.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private LocalDate dateDeNaissance;
	private String presentation;
	private String commentaires;
	private String avatarUrl;
	private String email;
	private String pseudo;
	private String motDePasse;
	@OneToOne																				
	@JoinColumn(name="role_id")
	private Role role;
	@OneToMany
	private List<Photo> photos;
	@OneToMany
	private List<HobbyCompetenceLangage> hobbyCompetenceLangage;
	}
