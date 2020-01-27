package co.simplon.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.Formateur;

public interface FormateurRepository extends CrudRepository<Formateur, Integer> {

	public Optional<Formateur> findByPseudo(String pseudo);

}
