package co.simplon.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	private final List<HobbyCompetenceLangage> hobbies = new ArrayList<>();
	private final String BASE_URL = "/api/hobbies";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		hobby.setTypeHobby("Type");
		hobby.setNom("Hobby");
		hobbies.add(hobby);
	}
	
	@Test
	public void testFindByNom() throws Exception {

		when(this.hobbyRepository.findById("Hobby")).thenReturn(Optional.of(hobby));

		this.mockMvc.perform(get(BASE_URL + "/findByNom?nom=Hobby").contentType(JSON).accept(JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("nom").value("Hobby"));

		this.mockMvc.perform(get(BASE_URL + "/findByNom?nom=Mauvais").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());
	}
}
