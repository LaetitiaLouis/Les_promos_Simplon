package co.simplon.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.model.Apprenant;
import co.simplon.model.Projet;
import co.simplon.model.Promo;
import co.simplon.repository.ApprenantRepository;
import co.simplon.repository.ProjetRepository;
import co.simplon.repository.PromoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ApprenantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ApprenantRepository apprenantRepository;

	@MockBean
	private PromoRepository promoRepository;

	@MockBean
	private ProjetRepository projetRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private final Apprenant apprenant = new Apprenant();
	private final String BASE_URL = "/api/apprenants";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		apprenant.setId(1);
		apprenant.setPseudo("Pseudo");
	}

	@Test
	public void testNew() throws Exception {
		when(apprenantRepository.save(apprenant)).thenReturn(apprenant);

		mockMvc.perform(post(BASE_URL + "/new").contentType(JSON).content(objectMapper.writeValueAsString(apprenant)))
				.andExpect(status().isCreated());

		when(apprenantRepository.findByPseudo(apprenant.getPseudo())).thenReturn(Optional.of(apprenant));
		mockMvc.perform(post(BASE_URL + "/new").contentType(JSON).content(objectMapper.writeValueAsString(apprenant)))
				.andExpect(status().isConflict());
	}

	@Test
	public void testGetAll() throws Exception {
		when(apprenantRepository.findAll()).thenReturn(List.of(apprenant));

		mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));

		when(apprenantRepository.findAll()).thenReturn(new ArrayList<>());

		mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindById() throws Exception {
		when(apprenantRepository.findById(1)).thenReturn(Optional.of(apprenant));

		mockMvc.perform(get(BASE_URL + "/findById?id=1")).andExpect(status().isOk())
				.andExpect(jsonPath("pseudo").value("Pseudo"));
		mockMvc.perform(get(BASE_URL + "/findById?id=2")).andExpect(status().isNotFound());

	}

	@Test
	public void testFindByPseudo() throws Exception {
		when(apprenantRepository.findByPseudo("Pseudo")).thenReturn(Optional.of(apprenant));

		mockMvc.perform(get(BASE_URL + "/findByPseudo?pseudo=Pseudo")).andExpect(status().isOk())
				.andExpect(jsonPath("pseudo").value("Pseudo"));

		mockMvc.perform(get(BASE_URL + "/findByPseudo?pseudo=Bad")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindByPromo() throws Exception {
		Promo promo = new Promo();
		promo.setNom("LP4");
		promo.setApprenants(List.of(apprenant));
		when(promoRepository.findById("LP4")).thenReturn(Optional.of(promo));

		mockMvc.perform(get(BASE_URL + "/findByPromo?promo=LP4")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));

		mockMvc.perform(get(BASE_URL + "/findByPromo?promo=LP3")).andExpect(status().isNotFound());

		promo.setApprenants(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/findByPromo?promo=LP4")).andExpect(status().isNotFound());

	}

	@Test
	public void testFindByProjet() throws Exception {
		Projet projet = new Projet();
		projet.setNom("Projet");
		projet.setApprenants(List.of(apprenant));
		when(projetRepository.findById("Projet")).thenReturn(Optional.of(projet));

		mockMvc.perform(get(BASE_URL + "/findByProjet?projet=Projet")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));

		mockMvc.perform(get(BASE_URL + "/findByProjet?projet=PasBon")).andExpect(status().isNotFound());

		projet.setApprenants(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/findByProjet?projet=Projet")).andExpect(status().isNotFound());

	}

	@Test
	public void testUpdate() throws Exception {
		when(apprenantRepository.findById(1)).thenReturn(Optional.of(apprenant));
		when(apprenantRepository.save(apprenant)).thenReturn(apprenant);

		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(apprenant)))
				.andExpect(status().isCreated());

		apprenant.setId(2);
		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(apprenant)))
				.andExpect(status().isNotFound());

	}
}
