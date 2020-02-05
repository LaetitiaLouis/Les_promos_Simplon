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

import co.simplon.model.HobbyCompetenceLangage;
import co.simplon.model.Projet;
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.ProjetRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProjetRepository projetRepository;
	@MockBean
	private HobbyCompetenceLangageRepository hobbyRepository;

	private final Projet projet = new Projet();
	private final String BASE_URL = "/api/projets";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		projet.setNom("soutenance");
	}

	@Test
	public void testGetAllProjets() throws Exception {
		when(this.projetRepository.findAll()).thenReturn(List.of(projet));

		this.mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].nom").value("soutenance"));

		when(this.projetRepository.findAll()).thenReturn(new ArrayList<>());
		this.mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isNotFound());
	}

	@Test
	public void testNew() throws Exception {
		when(projetRepository.save(projet)).thenReturn(projet);
		when(projetRepository.findById("soutenance")).thenReturn(Optional.of(projet));
		projet.setNom("inexistant");

		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isCreated());

		projet.setNom("soutenance");
		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isConflict());
	}

	@Test
	public void testUpdate() throws Exception {

		when(projetRepository.save(projet)).thenReturn(projet);
		when(projetRepository.findById("soutenance")).thenReturn(Optional.of(projet));

		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isCreated());
		// .andResult(jsonPath("soutenance").value("UpdatedNom"));

		projet.setNom("updateSoutenance");
		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(projet)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testFindById() throws Exception {

		when(this.projetRepository.findById("soutenance")).thenReturn(Optional.of(projet));

		this.mockMvc.perform(get(BASE_URL + "/findById?nom=soutenance")).andExpect(status().isOk())
				.andExpect(jsonPath("nom").value(projet.getNom()));

		this.mockMvc.perform(get(BASE_URL + "/findById?nom=joker")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindByLangage() throws Exception {
		HobbyCompetenceLangage langage = new HobbyCompetenceLangage();
		langage.setProjets(List.of(projet));
		when(this.hobbyRepository.findById("java")).thenReturn(Optional.of(langage));

		this.mockMvc.perform(get(BASE_URL + "/findByLangage?langage=java")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].nom").value(projet.getNom()));
		langage.setProjets(new ArrayList<>());
		this.mockMvc.perform(get(BASE_URL + "/findByLangage?langage=inconnu")).andExpect(status().isNotFound());
		this.mockMvc.perform(get(BASE_URL + "/findByLangage?langage=java")).andExpect(status().isNotFound());
	}
}
