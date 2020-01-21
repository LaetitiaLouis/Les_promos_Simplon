package co.simplon.repository;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

}
