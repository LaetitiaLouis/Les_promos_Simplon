package co.simplon.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import co.simplon.model.Projet;
import co.simplon.repository.ProjetRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjetRepository projetRepository;

	private Projet projet;
	private final String BASE_URL = "/api/projets";

	@BeforeEach
	public void setUp() {
		this.projet = new Projet();
		projet.setNom("soutenance");
	}

	@Test
	public void findOne() throws Exception {

		when(this.projetRepository.findById("soutenance")).thenReturn(Optional.of(projet));

		// 1er test qui devrait trouver un obet
		this.mockMvc.perform(get(BASE_URL + "findById?nom=soutenance")).andExpect(status().isOk())
				.andExpect(jsonPath("nom").value(projet.getNom()));

		// 2nd test qui devrait ne pas trouver d'objet
		this.mockMvc.perform(get(BASE_URL + "/findById?nom=joker")).andExpect(status().isNotFound());
	}
}
