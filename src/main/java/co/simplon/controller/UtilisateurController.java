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
import org.springframework.web.bind.annotation.RestController;

import co.simplon.HttpResponse;
import co.simplon.model.HobbyCompetenceLangage;
import co.simplon.model.Photo;
import co.simplon.model.Utilisateur;
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.PhotoRepository;
import co.simplon.repository.UtilisateurRepository;

/**
 * Controlleur définissant les endpoints concernant l'entité  utilisateurs
 * @author Laëtitia, Sébastien et Cédric
 *
 */
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

	/**
	 * Obtenir un utilisateur par id
	 * @param L'id de l'apprenant en paramètre de la requète
	 * @return L'apprenant si il existe ou une erreur 404 et un message
	 */
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
		if (utilisateur.isPresent()) {
			return ResponseEntity.ok(utilisateur.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	/**
	 * Obtenir la liste de tous les utilisateurs
	 * @return La liste si elle n'est pas vide sinon une erreur 404 et un message
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {
		List<Utilisateur> users = (List<Utilisateur>) utilisateurRepository.findAll();
		if (users.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(users);
		}
	}

	/**
	 * Obtenir un utilisateurs par pseudo 
	 * @param Le pseudo en paramètre de la requète
	 * @return L'utilisateur si il existe sinon une erreur 404 et un message
	 */
	@GetMapping("/findByPseudo")
	public ResponseEntity<?> findByPseudo(@RequestParam String pseudo) {
		Optional<Utilisateur> utilisateur = utilisateurRepository.findByPseudo(pseudo);
		if (utilisateur.isPresent()) {
			return ResponseEntity.ok(utilisateur);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	
	/**
	 * Obtenir un utilisateurs par l'id d'une photo 
	 * @param l'id d'une photo en paramètre de la requète
	 * @return L'utilisateur si il existe sinon une erreur 404 et un message
	 */
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
	
	/**
	 * Obtenir un utilisateurs par Hobby/Compétence ou langage 
	 * @param Le nom d'un hobby en paramètre de la requète
	 * @return L'utilisateur si il existe sinon une erreur 404 et un message
	 */
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

	/**
	 * connect un utilisateur
	 * @param L'objet utilisateur dans le body de la requète
	 * @return L'objet crée ou une erreur 409 et un message 
	 * si le pseudo et le mot de passe sont correct 
	 */
	@PostMapping("/connect")
	public ResponseEntity<?> connection(@RequestBody Utilisateur utilisateur) {
		Optional<Utilisateur> user = utilisateurRepository.findByPseudo(utilisateur.getPseudo());
		if (user.isPresent()) {
			if (utilisateur.getMotDePasse().equals(user.get().getMotDePasse())) {
				return ResponseEntity.ok(user.get());
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accès refusé");
			}
		} else {
			return HttpResponse.NOT_FOUND;

		}
	}

	/**
	 * Obtenir un utilisateurs par nom 
	 * @param Le nom, complet ou incomplet d'un utilisateur en paramètre de la requète
	 * @return L'utilisateur si il existe sinon une erreur 404 
	 */
	@GetMapping("/findByNom")
	public ResponseEntity<?> findByNom(@RequestParam String nom) {
		List<Utilisateur> utilisateurs = utilisateurRepository.findByNom(nom);
		if (utilisateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(utilisateurs);
		}
	}

	/**
	 * Obtenir un utilisateurs par prenom 
	 * @param Le prenom, complet ou incomplet d'un utilisateur en paramètre de la requète
	 * @return L'utilisateur si il existe sinon une erreur 404 
	 */
	@GetMapping("/findByPrenom")
	public ResponseEntity<?> findByPrenom(@RequestParam String prenom) {
		List<Utilisateur> utilisateurs = utilisateurRepository.findByPrenom(prenom);
		if (utilisateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(utilisateurs);
		}
	}

	/**
	 * Obtenir un utilisateurs par nom ou prenom 
	 * @param Le nom ou un prenom, complet ou incomplet d'un utilisateur en paramètre de la requète
	 * @return L'utilisateur si il existe sinon une erreur 404 
	 */
	@GetMapping("/findByNomPrenom")
	public ResponseEntity<?> findByNomPrenom(@RequestParam String nomPrenom) {

		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		utilisateurs.addAll(utilisateurRepository.findByPrenom("%" + nomPrenom + "%"));
		List<Utilisateur> userNom = utilisateurRepository.findByNom("%" + nomPrenom + "%");
		
		//ajout des utilisateurs qui ne sont pas encore présent dans le résultat de finByPrenom
		int i;
		boolean flagPresent=false;
		for( Utilisateur currentUser: userNom)
		{
			for(i=0;i<utilisateurs.size();i++)
			{
				if(currentUser.getId() == utilisateurs.get(i).getId())
					flagPresent=true;
			}
			if(flagPresent==false)
				utilisateurs.add(currentUser);
			
		}
		

		if (utilisateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(utilisateurs);
		}
	}

	/**
	 * Vérifie si un utilisateur avec le pseudo donné existe
	 * @param Le pseudo d'un utilisateur en paramètre de la requète
	 * @return true si il existe sinon false
	 */
	@GetMapping("/pseudoExists")
	public boolean checkIfPseudoExists(@RequestParam String pseudo) {
		return utilisateurRepository.existsByPseudo(pseudo);
	}

	/**
	 * Vérifie si un utilisateur avec l'email donné existe
	 * @param l'email d'un utilisateur en paramètre de la requète
	 * @return true si il existe sinon false
	 */
	@GetMapping("/emailExists")
	public boolean checkIfEmailExists(@RequestParam String email) {
		return utilisateurRepository.existsByEmail(email);
	}

}
