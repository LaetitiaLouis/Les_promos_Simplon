package co.simplon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.HttpResponse;
import co.simplon.model.HobbyCompetenceLangage;
import co.simplon.model.Photo;
import co.simplon.model.Utilisateur;
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.PhotoRepository;
import co.simplon.repository.UtilisateurRepository;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin("http://localhost:4200")
public class UtilisateurController {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	HobbyCompetenceLangageRepository hobbyCompetenceLangageRepository;

	@DeleteMapping("/delete")
	public void deleteUser(@RequestParam int id) {
		utilisateurRepository.deleteById(id);
	}

	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
		if (utilisateur.isPresent()) {
			return ResponseEntity.ok(utilisateur.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<Utilisateur> getAllUsers() {
		return utilisateurRepository.findAll();
	}

	@GetMapping("/findByPseudo")
	public ResponseEntity<?> findByPseudo(@RequestParam String pseudo) {
		Optional<Utilisateur> utilisateur = utilisateurRepository.findByPseudo(pseudo);
		if (utilisateur.isPresent()) {
			return ResponseEntity.ok(utilisateur);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	@GetMapping("/findByPhoto")
	public ResponseEntity<?> findByPhoto(@RequestParam int id) {
		Optional<Photo> photo = photoRepository.findById(id);
		if (photo.isPresent()) {
			Utilisateur utilisateur = photo.get().getUtilisateur();
			return ResponseEntity.ok(utilisateur);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	@GetMapping("/findByHobby")
	public ResponseEntity<?> findByHobby(@RequestParam String hobby) {
		Optional<HobbyCompetenceLangage> h = hobbyCompetenceLangageRepository.findById(hobby);
		if (h.isPresent()) {
			List<Utilisateur> utilisateurs = h.get().getUtilisateurs();
			if (utilisateurs.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(utilisateurs);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	@PostMapping("/connect")
	public ResponseEntity<?> connection(@RequestBody Utilisateur utilisateur) {
		Optional<Utilisateur> user = utilisateurRepository.findByPseudo(utilisateur.getPseudo());
		if (user.isPresent()) {
			if (utilisateur.getMotDePasse().equals(user.get().getMotDePasse())) {
				return ResponseEntity.ok(user.get());
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé");
			}
		} else {
			return HttpResponse.NOT_FOUND;

		}
	}
	
	
	@GetMapping("/findByNom")
	public ResponseEntity<?> findByNom(@RequestParam String nom){
		List<Utilisateur> utilisateurs = utilisateurRepository.findByNom(nom);
		if(utilisateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(utilisateurs);
		}
	}

	@GetMapping("/findByPrenom")
	public ResponseEntity<?> findByPrenom(@RequestParam String prenom){
		List<Utilisateur> utilisateurs = utilisateurRepository.findByPrenom(prenom);
		if(utilisateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(utilisateurs);
		}	
	}
	
	
	@GetMapping("/findByNomPrenom")
	public ResponseEntity<?> findByNomPrenom (@RequestParam String nomPrenom) {

		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		utilisateurs.addAll(utilisateurRepository.findByPrenom("%"+nomPrenom+"%"));
		utilisateurs.addAll(utilisateurRepository.findByNom("%"+nomPrenom+"%"));
		
		if(utilisateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(utilisateurs);
		}	
	}	

}
