package co.simplon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.HttpResponse;
import co.simplon.model.Promo;
import co.simplon.repository.PromoRepository;

@RestController
@RequestMapping("/api/promos")
@CrossOrigin("http://localhost:4200")
public class PromoController {

	@Autowired
	PromoRepository promoRepository;

	/**
	 * Obtenir la liste de toutes les promos
	 * @return La liste si elle n'est pas vide sinon une erreur 404 et un message
	 */
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<Promo> promos = (List<Promo>) promoRepository.findAll();
		if (promos.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(promos);
		}
	}
	
	/**
	 * Obtenir une promo par son nom
	 * @param L'id de la promo en paramètre de la requète
	 * @return La promo si il existe ou une erreur 404
	 */
	@GetMapping("/findById")
	public ResponseEntity<?> findById(@RequestParam String nom) {
		Optional<Promo> promo = promoRepository.findById(nom);
		if (promo.isPresent()) {
			return ResponseEntity.ok(promo.get());
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}
}
