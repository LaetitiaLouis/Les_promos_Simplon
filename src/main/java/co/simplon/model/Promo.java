package co.simplon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Promo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String nom;
	private String anneeFin;
	private String specialite;
	@OneToMany
	private List<Apprenant> apprenants;
	@ManyToMany
	private List<Formateur> formateurs;
}
