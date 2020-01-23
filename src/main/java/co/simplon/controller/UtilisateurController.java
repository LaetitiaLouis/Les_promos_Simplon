package co.simplon.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.model.Apprenant;
import co.simplon.model.Formateur;
import co.simplon.model.Projet;
import co.simplon.model.Utilisateur;
import co.simplon.repository.ProjetRepository;
import co.simplon.repository.UtilisateurRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UtilisateurController {

	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	ProjetRepository projetRepository;

	@PostMapping("/newApprenant")
	public Apprenant newApprenant(@RequestBody Apprenant apprenant) {
		return utilisateurRepository.save(apprenant);
	}
	
	@PostMapping("/newFormateur")
	public Formateur newFormateur(@RequestBody Formateur formateur) {
		return utilisateurRepository.save(formateur);
	}
	
	@DeleteMapping("/delete")
	public void deleteUser(@RequestParam int id) {
		utilisateurRepository.deleteById(id);
	}
	
	@PutMapping("/updateApprenant")
	public Utilisateur updateApprenant(@RequestBody Apprenant modifApprenant) {
		 return utilisateurRepository.save(modifApprenant);
	}
	
	@GetMapping("/all")
	public @ResponseBody Iterable<Utilisateur> test() {
		return utilisateurRepository.findAll();
	}

	@GetMapping("/byProjet")
	public ResponseEntity<?> testProj(@RequestParam String id) {
		Optional<Projet> projet = projetRepository.findById(id);
		if (projet.isPresent()) {
			Projet p  = projet.get() ;
			return ResponseEntity.ok(p.getApprenants());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
}
