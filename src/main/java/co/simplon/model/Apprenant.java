package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Apprenant extends Utilisateur {
	
	private String entiteAffectation;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Promo promo;
	@ManyToMany (mappedBy = "apprenants", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Projet> projets= new ArrayList<>();

}
