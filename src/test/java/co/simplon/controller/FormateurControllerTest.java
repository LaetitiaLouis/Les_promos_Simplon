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

import co.simplon.model.Formateur;
import co.simplon.model.Promo;
import co.simplon.repository.FormateurRepository;
import co.simplon.repository.PromoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FormateurControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FormateurRepository formateurRepository;

	@MockBean
	private PromoRepository promoRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private final Formateur formateur = new Formateur();
	private final String BASE_URL = "/api/formateurs";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		formateur.setPseudo("Pseudo");
		formateur.setId(1);
	}

	@Test
	public void testNew() throws Exception {
		when(formateurRepository.save(formateur)).thenReturn(formateur);
		when(formateurRepository.findByPseudo("Pseudo")).thenReturn(Optional.of(formateur));
		formateur.setPseudo("innexistant");

		mockMvc.perform(post(BASE_URL + "/new").accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(formateur))).andExpect(status().isCreated());

		formateur.setPseudo("Pseudo");
		mockMvc.perform(post(BASE_URL + "/new").accept(JSON).contentType(JSON)
				.content(objectMapper.writeValueAsString(formateur))).andExpect(status().isConflict());
	}

	@Test
	public void testFindById() throws Exception {
		when(formateurRepository.findById(1)).thenReturn(Optional.of(formateur));

		mockMvc.perform(get(BASE_URL + "/findById?id=1")).andExpect(status().isOk())
				.andExpect(jsonPath("pseudo").value("Pseudo"));

		mockMvc.perform(get(BASE_URL + "/findById?id=2")).andExpect(status().isNotFound());
	}

	@Test
	public void testGetAll() throws Exception {
		when(formateurRepository.findAll()).thenReturn(List.of(formateur));

		mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));

		when(formateurRepository.findAll()).thenReturn(new ArrayList<>());

		mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindByPseudo() throws Exception {
		when(formateurRepository.findByPseudo("Pseudo")).thenReturn(Optional.of(formateur));

		mockMvc.perform(get(BASE_URL + "/findByPseudo?pseudo=Pseudo")).andExpect(status().isOk())
				.andExpect(jsonPath("pseudo").value("Pseudo"));

		mockMvc.perform(get(BASE_URL + "/findByPseudo?pseudo=Inconnu")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindByPromo() throws Exception {
		Promo promo = new Promo();
		promo.setNom("LP4");
		promo.setFormateurs(List.of(formateur));
		when(promoRepository.findById("LP4")).thenReturn(Optional.of(promo));

		mockMvc.perform(get(BASE_URL + "/findByPromo?promo=LP4")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));

		mockMvc.perform(get(BASE_URL + "/findByPromo?promo=LP3")).andExpect(status().isNotFound());
		
		promo.setFormateurs(new ArrayList<>());
		mockMvc.perform(get(BASE_URL + "/findByPromo?promo=LP4")).andExpect(status().isNotFound());
	}

	@Test
	public void testUpdate() throws Exception {
		formateur.setPseudo("UpdatedPseudo");
		when(formateurRepository.save(formateur)).thenReturn(formateur);
		when(formateurRepository.findById(1)).thenReturn(Optional.of(formateur));

		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(formateur)))
				.andExpect(status().isCreated());
				//.andResult(jsonPath("pseudo").value("UpdatedPseudo"));
		
		
		formateur.setId(2);
		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(formateur)))
				.andExpect(status().isNotFound());

	}

}
