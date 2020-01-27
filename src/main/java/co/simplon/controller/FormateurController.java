package co.simplon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/formateurs")
@CrossOrigin("http://localhost:4200")
public class FormateurController {

	@Autowired
	FormateurRepository formateurRepository;

	@Autowired
	PromoRepository promoRepository;

	@PostMapping("/new")
	public @ResponseBody Formateur create(@RequestBody Formateur formateur) {
		return formateurRepository.save(formateur);
	}

	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<Formateur> formateurs = (List<Formateur>) formateurRepository.findAll();
		if (formateurs.isEmpty()) {
			return HttpResponse.NOT_FOUND;
		} else {
			return ResponseEntity.ok(formateurs);
		}
	}

	@GetMapping("/findByPseudo")
	public ResponseEntity<?> findByPseudo(@RequestParam String pseudo) {
		Optional<Formateur> formateur = formateurRepository.findByPseudo(pseudo);
		if (formateur.isPresent()) {
			return ResponseEntity.ok(formateur);
		} else {
			return HttpResponse.NOT_FOUND;
		}
	}

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

	@PutMapping("/update")
	public @ResponseBody Formateur update(@RequestBody Formateur formateur) {
		return formateurRepository.save(formateur);
	}
}
