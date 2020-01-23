package co.simplon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Apprenant;
import co.simplon.model.Promo;
import co.simplon.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	
	public List<Utilisateur> findByNom(String nom);
	public List<Utilisateur> findByPrenom(String prenom);
	public List<Apprenant> findByPromo(Promo promo);
}
