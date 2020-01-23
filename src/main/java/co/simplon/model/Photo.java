package co.simplon.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String categorie;
	private LocalDate datePhoto;
	private String imageUrl;
	
	@ManyToOne
	private Utilisateur utilisateur;
}
