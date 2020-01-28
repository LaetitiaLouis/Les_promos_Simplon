package co.simplon.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	
	public Optional<Utilisateur> findByPseudo(String pseudo);

}
