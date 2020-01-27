package co.simplon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.simplon.model.HobbyCompetenceLangage;

public interface HobbyCompetenceLangageRepository extends CrudRepository <HobbyCompetenceLangage, String> {
	
	public List<HobbyCompetenceLangage> findByTypeHobby(String type);

}
