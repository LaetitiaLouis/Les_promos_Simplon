package co.simplon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.HttpResponse;
import co.simplon.model.Photo;
import co.simplon.model.Utilisateur;
import co.simplon.repository.PhotoRepository;
import co.simplon.repository.UtilisateurRepository;
import co.simplon.service.PhotoService;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin("http://localhost:4200")
public class PhotoController {

	@Autowired
	PhotoService photoService;

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@PostMapping("/upload")
	public ResponseEntity<String> saveImage(@RequestParam MultipartFile file, @RequestParam Photo photo) {
		try {
			String filename = photoService.save(file, photo);
			photo.setImageUrl("http://localhost:8080/api/photos/download/" + filename);
			photoRepository.save(photo);
			return ResponseEntity.ok("Photo enregistrée");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Impossible d'enregistrer l'image");
		}

	}
	
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id){
		Optional<Photo> photo = photoRepository.findById(id);
		if(photo.isPresent()) {
			return ResponseEntity.ok(photo.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	
	@GetMapping("/findByUser")
	public ResponseEntity<?> findByUser(@RequestParam int id) {
		Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
		if (utilisateur.isPresent()) {
			List<Photo> photos = utilisateur.get().getPhotos();
			if (photos.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(photos);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	@GetMapping("/findByCategorie")
	public ResponseEntity<?> findByCategorie(@RequestParam String categorie) {
		List<Photo> photos = photoRepository.findByCategorie(categorie);
		if (photos.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(photos);
		}
	}

	@GetMapping("/download/{filename}")
	public ResponseEntity<?> getImage(@PathVariable String filename) {
		try {
			Resource file = photoService.getFile(filename);
			return ResponseEntity.ok(file);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune photo correspondante");
		}

	}

	@PutMapping("/update")
	public @ResponseBody Photo update(@RequestBody Photo photo) {
		return photoRepository.save(photo);
	}

}
