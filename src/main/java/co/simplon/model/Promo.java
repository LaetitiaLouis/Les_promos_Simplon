package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	

	@JsonIgnore
	@OneToMany (cascade = CascadeType.ALL) @JoinTable( inverseJoinColumns = {@JoinColumn(name="apprenant_id")})
	private List<Apprenant> apprenants = new ArrayList<>();
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(inverseJoinColumns = { @JoinColumn(name = "formateur_id") })
	private List<Formateur> formateurs = new ArrayList<>() ;
}
