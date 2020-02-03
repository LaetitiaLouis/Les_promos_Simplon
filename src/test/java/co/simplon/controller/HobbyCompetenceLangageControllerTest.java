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

import co.simplon.model.HobbyCompetenceLangage;
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.ProjetRepository;
import co.simplon.repository.UtilisateurRepository;;

@SpringBootTest
@AutoConfigureMockMvc
public class HobbyCompetenceLangageControllerTest {

	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	HobbyCompetenceLangageRepository hobbyRepository;

	@MockBean
	UtilisateurRepository utilisateurRepository;

	@MockBean
	ProjetRepository projetRepository;

	private final HobbyCompetenceLangage hobby = new HobbyCompetenceLangage();
	private final String BASE_URL = "/api/hobbies";

	@BeforeEach
	public void setUp() {
		hobby.setNom("Hobby");
	}

	@Test
	public void testFindByNom() throws Exception {

		when(this.hobbyRepository.findById("Hobby")).thenReturn(Optional.of(hobby));

		this.mockMvc.perform(get(BASE_URL + "/findByNom?nom=Hobby")).andExpect(status().isOk())
				.andExpect(jsonPath("nom").value("Hobby"));

		this.mockMvc.perform(get(BASE_URL + "/findByNom?nom=Mauvais")).andExpect(status().isNotFound());
	}
}
