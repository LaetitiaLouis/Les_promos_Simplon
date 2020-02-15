package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "hobby")
public class HobbyCompetenceLangage {
	
	@Id
	private String nom;
	private String typeHobby;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "hobbies", cascade = CascadeType.MERGE)
	private List<Utilisateur> utilisateurs = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "langage_projet", joinColumns = @JoinColumn(name = "langage_id"), inverseJoinColumns = @JoinColumn(name = "projet_id"))
	private List<Projet> projets = new ArrayList<>(); 

}
