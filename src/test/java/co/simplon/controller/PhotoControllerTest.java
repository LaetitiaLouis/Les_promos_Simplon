package co.simplon.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import co.simplon.model.Photo;
import co.simplon.repository.PhotoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PhotoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PhotoRepository photoRepository;
	private Photo photo = new Photo();
	private final String BASE_URL = "/api/photos";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		photo.setCategorie("evenement");
	}

	@Test
	public void testFindById() throws Exception {

		when(this.photoRepository.findById(1)).thenReturn(Optional.of(photo));

		this.mockMvc.perform(get(BASE_URL + "/findById?id=1")).andExpect(status().isOk())
				.andExpect(jsonPath("categorie").value("evenement"));

		this.mockMvc.perform(get(BASE_URL + "/findById?id=2")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindByCategorie() throws Exception {

		when(this.photoRepository.findByCategorie("evenement")).thenReturn(List.of(photo));

		this.mockMvc.perform(get(BASE_URL + "/findByCategorie?categorie=evenement")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].categorie").value(photo.getCategorie()));

		this.mockMvc.perform(get(BASE_URL + "/findByCategorie?categorie=profil")).andExpect(status().isNotFound());
	}
}
