package co.simplon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Apprenant;
import co.simplon.model.Promo;

public interface ApprenantRepository extends CrudRepository<Apprenant, Integer>{
	
	public Optional<Apprenant> findByPseudo(String pseudo);
	public List<Apprenant> findByPromo(Promo promo);

}
 