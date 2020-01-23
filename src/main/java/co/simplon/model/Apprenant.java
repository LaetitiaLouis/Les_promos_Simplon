package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Apprenant extends Utilisateur {

	private String entiteAffectation;

	@JsonManagedReference
	@OneToOne
	@JoinTable(name = "apprenant_promo" , joinColumns = @JoinColumn(name = "apprenant_id"), inverseJoinColumns = @JoinColumn(name = "promo_id"))
	private Promo promo;

	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "apprenant_projet", joinColumns = @JoinColumn(name = "apprenant_id"), inverseJoinColumns = @JoinColumn(name = "projet_id"))
	private List<Projet> projets = new ArrayList<>();

}
