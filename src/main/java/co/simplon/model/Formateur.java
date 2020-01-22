package co.simplon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

@PrimaryKeyJoinColumn(name = "utilisateur_id")
public class Formateur extends Utilisateur {

	@ManyToMany(mappedBy = "formateurs")
	private List<Promo> promos = new ArrayList<>();
}
