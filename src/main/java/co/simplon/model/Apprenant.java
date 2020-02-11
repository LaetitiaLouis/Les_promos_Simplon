package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Apprenant extends Utilisateur {

	private String entiteAffectation;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Promo promo;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "apprenant_projet", joinColumns = @JoinColumn(name = "apprenant_id"), inverseJoinColumns = @JoinColumn(name = "projet_id"))
	private List<Projet> projets = new ArrayList<>();

}
