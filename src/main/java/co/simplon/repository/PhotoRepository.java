package co.simplon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Photo;
import co.simplon.model.Utilisateur;

public interface PhotoRepository extends CrudRepository<Photo, Integer>{
	public List<Photo> findByUtilisateur(Utilisateur utilisateur);
	public List<Photo> findByCategorie(String categorie);

}
