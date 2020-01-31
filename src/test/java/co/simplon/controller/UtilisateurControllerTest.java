package co.simplon.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import co.simplon.model.Utilisateur;
import co.simplon.repository.UtilisateurRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UtilisateurRepository utilisateurRepository;
	
	@Test
	public void testGetAllUsers() throws Exception {
		// Mock
		Utilisateur user = new Utilisateur();
		user.setPseudo("Pseudo");
		List<Utilisateur> userList = new ArrayList<>();
		userList.add(user);
		when(this.utilisateurRepository.findAll()).thenReturn(userList);
		
		// Test
		this.mockMvc.perform(get("/api/utilisateurs/all"))
		.andExpect(status().isOk());
		
	}
	
	
	
}
