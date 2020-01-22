package co.simplon.repository;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Apprenant;
import co.simplon.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	
	public Iterable<Utilisateur> findByNom(String nom);
	public Iterable<Utilisateur> findByPrenom(String prenom);
	public Iterable<Apprenant> findByPromo(String promo);
}
