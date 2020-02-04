package co.simplon.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private LocalDate dateDeNaissance;
	private String presentation;
	private String commentaires;
	@Column(unique = true)
	private String pseudo;
	@Column(unique = true)
	private String email;
	private String motDePasse;
	@Column(columnDefinition = "varchar(255) default 'http://localhost:8080/api/photos/download/avatar.png'")
	private String avatarUrl;
	
	@ManyToOne
	private Role role;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Photo> photos = new ArrayList<>();

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "utilisateur_hobby", inverseJoinColumns = @JoinColumn(name = "hobby"))
	private List<HobbyCompetenceLangage> hobbies = new ArrayList<>();
}
