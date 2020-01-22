package co.simplon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Promo {
	@Id
	private String nom;
	private String anneeFin;
	private String specialite;
	
	@OneToMany
	@JsonIgnore
	private List<Apprenant> apprenants;
	
	@ManyToMany
	@JsonIgnore
	private List<Formateur> formateurs;
}
