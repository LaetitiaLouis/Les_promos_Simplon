package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	
	@ManyToMany (mappedBy = "projets", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<HobbyCompetenceLangage> langages = new ArrayList<>();
	
	@ManyToMany(mappedBy = "projets", cascade = CascadeType.MERGE)
	private List<Apprenant> apprenants = new ArrayList<>();
}
