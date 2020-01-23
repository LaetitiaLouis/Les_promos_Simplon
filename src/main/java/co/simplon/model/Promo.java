package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@OneToMany(mappedBy="promo")
	@JsonBackReference
	private List<Apprenant> apprenants = new ArrayList<>();
	
	@ManyToMany(mappedBy = "promos")
	@JsonBackReference
	private List<Formateur> formateurs = new ArrayList<>() ;
}
