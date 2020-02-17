package co.simplon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

/**
 * Controlleur définissant les endpoints concernant l'entité Photo 
 * ainsi que les téléchargements/uploads de fichiers photo
 * @author Laëtitia, Sébastien et Cédric
 *
 */
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

	private final String PHOTOS_URL = "http://localhost:8080/api/photos/download/";

	/**
	 * Enregistre une photo sur le disque dur
	 * @param Le fichier photo à enregistrer en paramètre de la requète
	 * @param L'id de la photo en paramètre de la requète
	 * @param L'id de l'utilisateur en paramètre de la requète
	 * @return L'objet photo si l'enregistrement s'est bien déroulé 
	 * sinon un message et une erreur 500
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> saveImage(@RequestParam MultipartFile file, @RequestParam int photoId,
			@RequestParam int userId) {

		try {
			Utilisateur user = utilisateurRepository.findById(userId).get();
			Photo photo = photoRepository.findById(photoId).get();
			photo.setUtilisateur(user);

			if (photo.getCategorie().equals("profil"))
				photo.setNom(user.getPrenom() + " " + user.getNom());

			String filename = photoService.save(file, photo);
			photo.setImageUrl(PHOTOS_URL + filename);

			if (photo.getCategorie().equals("profil")) {
				photoRepository.delete(photoRepository.findByImageUrl(user.getAvatarUrl()).get());
				user.setAvatarUrl(photo.getImageUrl());
				utilisateurRepository.save(user);
				photoService.delete(photo, PHOTOS_URL);
			}

			photoRepository.save(photo);
			return ResponseEntity.ok(photo);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Impossible d'enregistrer l'image");
		}

	}

	/**
	 * Obtenir un objet photo par id 
	 * @param L'id de la photo en paramètre de la requète 
	 * @return La photo si elle existe sinon un message et une erreur 404
	 */
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Photo> photo = photoRepository.findById(id);
		if (photo.isPresent()) {
			return ResponseEntity.ok(photo.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	
	/**
	 * Obtenir les photos d'un utilisateur
	 * @param L'id de l'utilisateur en paramètre de la requète 
	 * @return Les photos de l'utilisateur si il existe et si 
	 * il en possède et sinon un message et une erreur 404
	 */
	@GetMapping("/findByUserId")
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
	
	/**
	 * Obtenir la liste des photos d'une catégorie
	 * @param Le nom de la catégorie en paramètre de la requète
	 * @return La liste des photos si elle n'est pas vide sinon un message et une erreur 404
	 */
	@GetMapping("/findByCategorie")
	public ResponseEntity<?> findByCategorie(@RequestParam String categorie) {
		List<Photo> photos = photoRepository.findByCategorie(categorie);
		if (photos.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(photos);
		}
	}

	/**
	 * Télécharger un fichier image 
	 * @param Le nom du fichier
	 * @return Le fichier si il existe sinon un message et une erreur 404
	 */
	@GetMapping("/download/{filename}")
	public ResponseEntity<?> getImage(@PathVariable String filename) {
		try {
			Resource file = photoService.get(filename);
			return ResponseEntity.ok(file);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune photo correspondante");
		}
	}

	/**
	 * Modifier un objet photo
	 * @param Le nouvel objet photo dans le body de la requète
	 * @return L'objet photo modifié si il existe sinon un message et une erreur 404
	 */
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@RequestBody Photo photo) {
		Optional<Photo> maybePhoto = photoRepository.findById(photo.getId());
		if (maybePhoto.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(photoRepository.save(photo));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette photo n'existe pas");
		}
	}

	/**
	 * Enregistrer un objet photo
	 * @param L'objet photo dans le body de la requète 
	 * @return L'objet photo crée
	 */
	@PostMapping("/new")
	public @ResponseBody ResponseEntity<?> create(@RequestBody Photo photo) {
		return ResponseEntity.ok(photoRepository.save(photo));
	}

	/**
	 * Obtenir la liste de toutes les photos
	 * @return La liste des photos si elle n'est pas vide sinon un message et une erreur 404
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<Photo> photos = (List<Photo>) photoRepository.findAll();
		if (photos.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(photos);
		}
	}

	/**
	 * Supprimer une photo par id
	 * @param L'id de la photo à supprimer 
	 * @return Un message de confirmation
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deletePhoto(@RequestParam int id) {
		Photo photo = photoRepository.findById(id).get();
		Utilisateur user = photo.getUtilisateur();
		if (photo.getCategorie().equals("profil")) {
			user.setAvatarUrl(PHOTOS_URL + "avatar.png");
			utilisateurRepository.save(user);
		}
		this.photoService.delete(photo, PHOTOS_URL);
		this.photoRepository.deleteById(photo.getId());
		return ResponseEntity.ok("Photo supprimée");
	}
}
