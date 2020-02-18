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
import org.springframework.web.bind.annotation.RestController;

import co.simplon.HttpResponse;
import co.simplon.model.Apprenant;
import co.simplon.model.Projet;
import co.simplon.model.Promo;
import co.simplon.repository.ApprenantRepository;
import co.simplon.repository.ProjetRepository;
import co.simplon.repository.PromoRepository;
import co.simplon.repository.RoleRepository;


/**
 * Controlleur définissant les endpoints concernant l'entité Apprenant
 * @author Laëtitia, Sébastien et Cédric
 *
 */
@RestController
@RequestMapping("/api/apprenants")
@CrossOrigin("http://localhost:4200")
public class ApprenantController {

	@Autowired
	ApprenantRepository apprenantRepository;

	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	PromoRepository promoRepository;

	@Autowired
	RoleRepository roleRepository;
	
	/**
	 * Enregistrer un nouvel apprenant
	 * @param L'objet apprenant dans le body de la requète
	 * @return L'objet crée ou une erreur 409 et un message 
	 * si le pseudo existe déjà
	 */
	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody Apprenant apprenant) {
		apprenant.setRole(roleRepository.findById(2).get());
		Optional<Apprenant> maybeApprenant = apprenantRepository.findByPseudo(apprenant.getPseudo());
		if (maybeApprenant.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce pseudo est déjà utilisé");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(apprenant);
		}
	}

	/**
	 * Obtenir la liste de tous les apprenants
	 * @return La liste si elle n'est pas vide sinon une erreur 404 et un message
	 */
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<Apprenant> apprenants = (List<Apprenant>) apprenantRepository.findAll();
		if (apprenants.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(apprenants);
		}
	}
	
	/**
	 * Obtenir un apprenant par id
	 * @param L'id de l'apprenant en paramètre de la requète
	 * @return L'apprenant si il existe ou une erreur 404 et un message
	 */
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Apprenant> apprenant = apprenantRepository.findById(id);
		if (apprenant.isPresent()) {
			return ResponseEntity.ok(apprenant.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Obtenir un apprenant par pseudo 
	 * @param Le pseudo en paramètre de la requète
	 * @return L'apprenant si il existe sinon une erreur 404 et un message
	 */
	@GetMapping("/findByPseudo")
	public ResponseEntity<?> findByPseudo(@RequestParam String pseudo) {
		Optional<Apprenant> apprenant = apprenantRepository.findByPseudo(pseudo);
		if (apprenant.isPresent()) {
			return ResponseEntity.ok(apprenant.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

	/**
	 * Obtenir un apprenant par promo
	 * @param Le nom de la promo en paramètre de la requète
	 * @return L'apprenant si il existe sinon une erreur 404 et un message
	 */
	@GetMapping("/findByPromo")
	public ResponseEntity<?> findbyPromo(@RequestParam String promo) {
		Optional<Promo> p = promoRepository.findById(promo);
		if (p.isPresent()) {
			List<Apprenant> apprenants = p.get().getApprenants();

			if (apprenants.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(apprenants);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	/**
	 * Obtenir un apprenant par projet
	 * @param Le nom du projet en paramètre de la requète
	 * @return Un apprenant si il existe sinon une erreur 404 et un message
	 */
	@GetMapping("/findByProjet")
	public ResponseEntity<?> findByProjet(@RequestParam String projet) {
		Optional<Projet> p = projetRepository.findById(projet);
		if (p.isPresent()) {
			List<Apprenant> apprenants = p.get().getApprenants();
			if (apprenants.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(apprenants);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	/**
	 * Modifier un apprenant 
	 * @param L'objet apprenant modifié dans le body de la requète
	 * @return L'objet apprenant modifié ou une erreur 404 et un message
	 * si l'apprenant n'a pas été trouvé en base de donnée
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Apprenant apprenant) {
		Optional<Apprenant> maybeApprenant = apprenantRepository.findById(apprenant.getId());
		if (maybeApprenant.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body((apprenantRepository.save(apprenant)));
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

}
