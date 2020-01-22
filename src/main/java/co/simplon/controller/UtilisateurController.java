package co.simplon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.model.Utilisateur;
import co.simplon.repository.UtilisateurRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UtilisateurController {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@GetMapping("/all")
	public @ResponseBody Iterable<Utilisateur> test() {
		return utilisateurRepository.findAll();
	}
}
