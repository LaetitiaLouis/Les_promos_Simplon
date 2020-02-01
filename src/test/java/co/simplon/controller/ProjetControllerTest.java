package co.simplon.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

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
	MockMvc mockMvc;
	
	@MockBean
	ProjetRepository projetRepository;
	
//	@Test
//	public void addNew() throws Exception {
//		Projet projetWithId = new Projet();
//		projetWithId.setId("nom");

//		when(this.projetRepository.save(new Projet(any()))).thenReturn(projetWithId);
//
//		ResultActions result = this.mockMvc.perform(post("/projets/add?nom=veille"));
//		
//		result.andExpect(status().isOk())
//		.andExpect(jsonPath("id").value(projetWithId.getTypeProjet()));
//	}
	
	@Test
	public void findOne() throws Exception {
		Projet projet = new Projet();
		projet.setNom("soutenance");

		when(this.projetRepository.findById("soutenance")).thenReturn(Optional.of(projet));

		// 1er test qui devrait trouver un obet
		this.mockMvc.perform(get("api/projets/findById?id=soutenance"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("nom").value(projet.getNom()));
		
		// 2nd test qui devrait ne pas trouver d'objet
		this.mockMvc.perform(get("api/projets/findById?id=joker"))
		.andExpect(status().isNotFound());
	}
}
