package co.simplon.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import co.simplon.model.Apprenant;
import co.simplon.model.Formateur;
import co.simplon.model.Projet;
import co.simplon.model.Promo;
import co.simplon.model.Utilisateur;
import co.simplon.repository.ProjetRepository;
import co.simplon.repository.PromoRepository;
import co.simplon.repository.UtilisateurRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UtilisateurController {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	ProjetRepository projetRepository;

	@Autowired
	PromoRepository promoRepository;

	private final ResponseEntity<String> NOT_FOUND = ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("<h3>Aucun résultat pour cette requète.</h3>");

	@PostMapping("/newApprenant")
	public Apprenant newApprenant(@RequestBody Apprenant apprenant) {
		return utilisateurRepository.save(apprenant);
	}

	@PostMapping("/newFormateur")
	public Formateur newFormateur(@RequestBody Formateur formateur, @RequestParam MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		return utilisateurRepository.save(formateur);
	}

	@DeleteMapping("/delete")
	public void deleteUser(@RequestParam int id) {
		utilisateurRepository.deleteById(id);
	}

	@PutMapping("/updateApprenant")
	public Apprenant updateApprenant(@RequestBody Apprenant modifApprenant) {
		return utilisateurRepository.save(modifApprenant);
	}

	@PutMapping("updateFormateur")
	public Formateur updateFormateur(@RequestBody Formateur modifFormateur) {
		return utilisateurRepository.save(modifFormateur);
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<Utilisateur> getAllUsers() {
		return utilisateurRepository.findAll();
	}

	@GetMapping("/byProjet")
	public ResponseEntity<?> searchByProjet(@RequestParam String nomProjet) {
		Optional<Projet> projet = projetRepository.findById(nomProjet);
		if (projet.isPresent()) {
			Projet p = projet.get();
			return ResponseEntity.ok(p.getApprenants());
		} else {
			return NOT_FOUND;
		}

	}

	@GetMapping("/byPromoApprenant")
	public ResponseEntity<?> searchByPromoApprenant(@RequestParam String nomPromo) {
		Optional<Promo> promo = promoRepository.findById(nomPromo);

		if (promo.isPresent()) {
			List<Apprenant> apprenants = utilisateurRepository.findByPromo(promo.get());
			if (!apprenants.isEmpty()) {
				return ResponseEntity.ok(apprenants);
			} else {
				return NOT_FOUND;
			}
		} else {
			return NOT_FOUND;
		}
	}

	@GetMapping("/byPromoFormateur")
	public ResponseEntity<?> searchByPromoFormateur(@RequestParam String nomPromo) {
		Optional<Promo> promo = promoRepository.findById(nomPromo);
		if (promo.isPresent()) {
			List<Formateur> formateurList = new ArrayList<>();
			for (Utilisateur user : utilisateurRepository.findAll()) {
				if (user instanceof Formateur)
					formateurList.add((Formateur) user);
			}
			Iterator<Formateur> iterator = formateurList.iterator();
			while (iterator.hasNext()) {
				if (!iterator.next().getPromos().contains(promo.get()))
					iterator.remove();
			}
			if (formateurList.isEmpty()) {
				return NOT_FOUND;
			} else {
				return ResponseEntity.ok(formateurList);
			}

		} else {
			return NOT_FOUND;
		}
	}


	

}
