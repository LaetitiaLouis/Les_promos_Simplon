package co.simplon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.model.Utilisateur;


public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
	
	public Optional<Utilisateur> findByPseudo(String pseudo);
	@Query("select u from Utilisateur u where prenom LIKE CONCAT('%',:prenom,'%')")
	public List<Utilisateur> findByPrenom(@Param("prenom") String prenom);
	@Query("select u from Utilisateur u where nom LIKE CONCAT('%',:nom,'%')")
	public List<Utilisateur> findByNom(@Param("nom") String nom);
	}
