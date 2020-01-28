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
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.ProjetRepository;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin("http://localhost:4200")
public class ProjetController {

	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	HobbyCompetenceLangageRepository hobbyRepository;

	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<Projet> projets = (List<Projet>) projetRepository.findAll();
		if (projets.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(projets);
		}
	}

	@PutMapping("/update")
	public @ResponseBody Projet update(@RequestBody Projet projet) {
		return projetRepository.save(projet);
	}

	@PostMapping("/new")
	public @ResponseBody Projet create(@RequestBody Projet projet) {
		return projetRepository.save(projet);
	}

	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam String nom) {
		Optional<Projet> projet = projetRepository.findById(nom);
		if (projet.isPresent()) {
			return ResponseEntity.ok(projet.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

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

}
