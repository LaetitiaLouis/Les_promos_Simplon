package co.simplon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	private String type;
	private String descriptif;
	@OneToMany
	private List<HobbyCompetenceLangage> langages;
}
