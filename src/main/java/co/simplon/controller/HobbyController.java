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

	@PostMapping("/new")
	public @ResponseBody HobbyCompetenceLangage create(@RequestBody HobbyCompetenceLangage hobby) {
		return hobbyCompetenceLangageRepository.save(hobby);
	}

	@PutMapping("/update")
	public @ResponseBody HobbyCompetenceLangage update(@RequestBody HobbyCompetenceLangage hobby) {
		return hobbyCompetenceLangageRepository.save(hobby);
	}

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

	@GetMapping("/findByUtilisateur")
	public ResponseEntity<?> findByUtilisateur(@RequestParam Integer utilisateur) {
		Optional<Utilisateur> user = utilisateurRepository.findById(utilisateur);
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

	@GetMapping("/findByNom")
	public ResponseEntity<?> findByNom(@RequestParam String nom) {
		Optional<HobbyCompetenceLangage> hobby = hobbyCompetenceLangageRepository.findById(nom);
		if (hobby.isPresent()) {
			return ResponseEntity.ok(hobby.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	@GetMapping("/findByProjet")
	public ResponseEntity<?> findByProjet(@RequestParam String projet){
		Optional<Projet> project = projetRepository.findById(projet);
		if(project.isPresent()) {
			List<HobbyCompetenceLangage> langages = project.get().getLangages();
			if(langages.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(langages);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

}
