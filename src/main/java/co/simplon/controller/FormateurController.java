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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.HttpResponse;
import co.simplon.model.Formateur;
import co.simplon.model.Promo;
import co.simplon.repository.FormateurRepository;
import co.simplon.repository.PromoRepository;
import co.simplon.repository.RoleRepository;


/**
 * Controlleur définissant les endpoints concernant l'entité Formateur
 * @author Laëtitia, Sébastien et Cédric
 *
 */
@RestController
@RequestMapping("/api/formateurs")
@CrossOrigin("http://localhost:4200")
public class FormateurController {

	@Autowired
	FormateurRepository formateurRepository;

	@Autowired
	PromoRepository promoRepository;
	
	@Autowired
	RoleRepository roleRepository;

	/**
	 * Enregistrer un nouveau formmateur
	 * @param L'objet formateur dans le body de la requète
	 * @return L'objet crée ou une erreur 409 si le pseudo existe déjà
	 */
	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody Formateur formateur) {
		formateur.setRole(roleRepository.findById(2).get());
		Optional<Formateur> maybeFormateur = formateurRepository.findByPseudo(formateur.getPseudo());
		if (maybeFormateur.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce pseudo existe déjà");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(formateurRepository.save(formateur));
		}

	}
	/**
	 * Obtenir un formateur par id
	 * @param L'id du formateur en paramètre de la requète
	 * @return Le formateur si il existe sinon un message et une erreur 404
	 */
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam int id) {
		Optional<Formateur> formateur = formateurRepository.findById(id);
		if (formateur.isPresent()) {
			return ResponseEntity.ok(formateur);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	/**
	 * Obtenir la liste de tout les formateurs
	 * @return La liste des formateurs si elle n'est pas vide 
	 * sinon un message et une erreur 404
	 */
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<Formateur> formateurs = (List<Formateur>) formateurRepository.findAll();
		if (formateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(formateurs);
		}
	}
	/**
	 * Obtenir un formateur par pseudo
	 * @param Le pseudo du formateur en paramètre de la requète
	 * @return Le formateur si il existe sinon un message et une erreur 404 
	 */
	@GetMapping("/findByPseudo")
	public ResponseEntity<?> findByPseudo(@RequestParam String pseudo) {
		Optional<Formateur> formateur = formateurRepository.findByPseudo(pseudo);
		if (formateur.isPresent()) {
			return ResponseEntity.ok(formateur);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	/**
	 * Obtenir un formateur par promo
	 * @param Le nom de la promo en paramètre de la requète
	 * @return Le formateur si il existe sinon un message et une erreur 404
	 */
	@GetMapping("/findByPromo")
	public ResponseEntity<?> findByPromo(@RequestParam String promo) {
		Optional<Promo> p = promoRepository.findById(promo);
		if (p.isPresent()) {
			List<Formateur> formateurs = p.get().getFormateurs();
			if (formateurs.isEmpty()) {
				return HttpResponse.NOT_FOUND;
			} else {
				return ResponseEntity.ok(formateurs);
			}
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
	/**
	 * Modifier un formateur
	 * @param L'objet formateur dans le body de la requète
	 * @return L'objet formateur modifié ou une erreur 404 
	 * et un message si il n'a pas été trouvé en base de donnée
	 */
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@RequestBody Formateur formateur) {
		Optional<Formateur> maybeFormateur = formateurRepository.findById(formateur.getId());
		if (maybeFormateur.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(formateurRepository.save(formateur));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce formateur n'existe pas");
		}
	}
	
}
