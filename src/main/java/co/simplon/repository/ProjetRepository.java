package co.simplon.repository;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Projet;

public interface ProjetRepository extends CrudRepository<Projet, String>{
	
	public Iterable<Projet> findByNom(String nom);
	
	

}
