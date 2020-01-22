package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@OneToMany
	private List<HobbyCompetenceLangage> langages = new ArrayList<>();
}
