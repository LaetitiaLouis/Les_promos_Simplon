package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Projet {

	@Id
	private String nom;
	private String typeProjet;
	private String descriptif;
	@ManyToMany (mappedBy = "projets", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<HobbyCompetenceLangage> langages = new ArrayList<>();
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(inverseJoinColumns = { @JoinColumn(name = "apprenant_id")})
	private List<Apprenant> apprenants = new ArrayList<>();
}
