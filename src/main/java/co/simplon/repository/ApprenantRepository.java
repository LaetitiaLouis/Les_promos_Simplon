package co.simplon.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Apprenant;

public interface ApprenantRepository extends CrudRepository<Apprenant, Integer>{
	
	public Optional<Apprenant> findByPseudo(String pseudo);
}
 