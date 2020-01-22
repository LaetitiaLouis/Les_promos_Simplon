package co.simplon.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Apprenant extends Utilisateur {
	
	private String entiteAffectation;
	@OneToOne
	private Promo promo;

}
