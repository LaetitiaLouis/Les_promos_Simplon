package co.simplon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.HttpResponse;
import co.simplon.model.HobbyCompetenceLangage;
import co.simplon.model.Projet;
import co.simplon.model.Utilisateur;
import co.simplon.repository.ApprenantRepository;
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.ProjetRepository;
import co.simplon.repository.UtilisateurRepository;

/**
 * Controlleur définissant les endpoints concernant l'entité HobbyCompetenceLangage
 * @author Laëtitia, Sébastien et Cédric
 *
 */
@RestController
@RequestMapping("/api/hobbies")
@CrossOrigin("http://localhost:4200")
public class HobbyController {

	@Autowired
	HobbyCompetenceLangageRepository hobbyCompetenceLangageRepository;
	@Autowired
	ApprenantRepository apprenantRepository;
	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	/**
	 * Enregistrement d'un nouveau hobby
	 * @param Le hobby dans le body de la requète
	 * @return L'objet hobby crée
	 */
	@PostMapping("/new")
	public @ResponseBody HobbyCompetenceLangage create(@RequestBody HobbyCompetenceLangage hobby) {
		return hobbyCompetenceLangageRepository.save(hobby);
	}
	
	/**
	 * Modifier un hobby
	 * @param Le nouveau hobby dans le body de la requète 
	 * @return L'objet hobby modifié
	 */
	@PutMapping("/update")
	public @ResponseBody HobbyCompetenceLangage update(@RequestBody HobbyCompetenceLangage hobby) {
		return hobbyCompetenceLangageRepository.save(hobby);
	}
	
	/**
	 * Obtenir la liste de tout les hobbies
	 * @return La liste des hobbies si elle n'est pas vide sinon un message et une erreur 404
	 */
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<HobbyCompetenceLangage> hobbies = (List<HobbyCompetenceLangage>) hobbyCompetenceLangageRepository
				.findAll();
		if (hobbies.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(hobbies);
		}
	}
	
	/**
	 * Obtenir les hobbies d'un utilisateur
	 * @param L'id de l'utilisateur en paramètre de la requète
	 * @return Une liste des hobbies de l'utilisateur si l'utilisateur 
	 * existe et qu'il a bien des hobbies sinon une erreur 404 et un message
	 */
	@GetMapping("/findByUtilisateur")
	public ResponseEntity<?> findByUtilisateur(@RequestParam int id) {
		Optional<Utilisateur> user = utilisateurRepository.findById(id);
		if (user.isPresent()) {
			List<HobbyCompetenceLangage> hobbies = user.get().getHobbies();
			if (hobbies.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(hobbies);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	
	/**
	 * Obtenir un hobby par nomm
	 * @param Le nom du hobby en paramètre de la requète
	 * @return Le hobby si il existe sinon une erreur 404 et un message
	 */
	@GetMapping("/findByNom")
	public ResponseEntity<?> findByNom(@RequestParam String nom) {
		Optional<HobbyCompetenceLangage> hobby = hobbyCompetenceLangageRepository.findById(nom);
		if (hobby.isPresent()) {
			return ResponseEntity.ok(hobby.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	
	/**
	 * Obtenir les langages d'un projet
	 * @param Le nom du projet en paramètre de la requète
	 * @return La liste des langages du projet si elle n'est pas vide 
	 * et si le projet existe sinon un message et une erreur 404
	 */
	@GetMapping("/findByProjet")
	public ResponseEntity<?> findByProjet(@RequestParam String projet) {
		Optional<Projet> project = projetRepository.findById(projet);
		if (project.isPresent()) {
			List<HobbyCompetenceLangage> langages = project.get().getLangages();
			if (langages.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(langages);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

}
