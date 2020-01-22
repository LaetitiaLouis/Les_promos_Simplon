package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Apprenant extends Utilisateur {
	
	private String entiteAffectation;
	
	@OneToOne
	private Promo promo;
	@OneToMany
	@JoinTable(joinColumns = {@JoinColumn(name="apprenant_id")})
	private List<Projet> projets= new ArrayList<>();
}
