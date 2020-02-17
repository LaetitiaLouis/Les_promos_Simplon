package co.simplon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import co.simplon.model.Apprenant;
import co.simplon.model.HobbyCompetenceLangage;
import co.simplon.model.Projet;
import co.simplon.repository.ApprenantRepository;
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.ProjetRepository;

/**
 * Controlleur définissant les endpoints concernant l'entité Projet
 * @author Laëtitia, Sébastien et Cédric
 *
 */
@RestController
@RequestMapping("/api/projets")
@CrossOrigin("http://localhost:4200")
public class ProjetController {

	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	ApprenantRepository apprenantRepository;

	@Autowired
	HobbyCompetenceLangageRepository hobbyRepository;

	/**
	 * Obtenir la liste de tous les projets
	 * @return La liste de tout les projets si elle n'est pas vide sinon un message et une erreur 404
	 */
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<Projet> projets = (List<Projet>) projetRepository.findAll();
		if (projets.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(projets);
		}
	}

	/**
	 * Modifier un projet   
	 * @param L'objet projet modifié en paramètre de la requète 
	 * @return Le projet modifié si il existe sinon un message et une erreur 404
	 */
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@RequestBody Projet projet) {
		Optional<Projet> maybeProjet = projetRepository.findById(projet.getNom());
		if (maybeProjet.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(projetRepository.save(projet));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce projet n'existe pas");
		}
	}

	/**
	 * Enregistrer un projet
	 * @param Le projet dans le body de la requète
	 * @return Le projet crée si il n'existe pas déjà sinon un message et une erreur 404
	 */
	@PostMapping("/new")
	public @ResponseBody ResponseEntity<?> create(@RequestBody Projet projet) {
		Optional<Projet> maybeProjet = projetRepository.findById(projet.getNom());
		if (maybeProjet.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce projet existe déjà");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(projetRepository.save(projet));
		}

	}

	/**
	 * Trouver un projet par nom
	 * @param Le nom du projet en paramètre de la requète
	 * @return Le projet si il existe sinon un messsage et une erreur 404
	 */
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam String nom) {
		Optional<Projet> projet = projetRepository.findById(nom);
		if (projet.isPresent()) {
			return ResponseEntity.ok(projet.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Obtenir les projets utilisant un langage donné
	 * @param Le nom du langage 
	 * @return Une liste de projets si elle n'est pas vide et 
	 * que le langage existe sinon un message et une erreur 404
	 */
	@GetMapping("/findByLangage")
	public ResponseEntity<?> findByLangage(@RequestParam String langage) {
		Optional<HobbyCompetenceLangage> lang = hobbyRepository.findById(langage);
		if (lang.isPresent()) {
			List<Projet> projets = lang.get().getProjets();
			if (projets.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(projets);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Obtenir les projets d'un apprenant
	 * @param L'id de l'apprenant en paramètre de la requète
	 * @return Une liste de projets si elle n'est pas vide et 
	 * que l'utilisateur existe sinon un message et une erreur 404
	 */
	@GetMapping("/findByIdApprenant")
	public ResponseEntity<?> findByIdApprenant(@RequestParam int idApprenant) {
		Optional<Apprenant> app = apprenantRepository.findById(idApprenant);
		if (app.isPresent()) {
			List<Projet> projets = app.get().getProjets();
			if (projets.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(projets);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

}
