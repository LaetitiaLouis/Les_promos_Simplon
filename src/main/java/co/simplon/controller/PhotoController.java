package co.simplon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.model.Utilisateur;
import co.simplon.repository.UtilisateurRepository;
import co.simplon.service.PhotoService;

@CrossOrigin("*")
@RestController
public class PhotoController {

	@Autowired
	PhotoService photoService;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam boolean profile, @RequestParam MultipartFile file,
			@RequestParam int id) {
		try {
			String avatarUrl = photoService.upload(file, id, profile);
			Utilisateur user = utilisateurRepository.findById(id).get();
			user.setAvatarUrl("http://localhost:8080/images/" + avatarUrl);
			utilisateurRepository.save(user);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'erreur': 'Impossible d'enregistrer l'image'}");
		}

	}

	@GetMapping("/images/{filename}")
	public ResponseEntity<?> getImage(@PathVariable String filename) {
		try {
			Resource file = photoService.getFile(filename);
			return ResponseEntity.ok(file);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune photo correspondante");
		}

	}

}
