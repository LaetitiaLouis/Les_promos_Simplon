package co.simplon.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.model.Photo;
import co.simplon.repository.PhotoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PhotoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PhotoRepository photoRepository;
	private Photo photo = new Photo();
	private final String BASE_URL = "/api/photos";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		photo.setCategorie("evenement");
		photo.setId(1);
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

	@Test
	public void testUpdate() throws Exception {
		photo.setCategorie("UpdateEvenement");

		when(photoRepository.save(photo)).thenReturn(photo);
		when(photoRepository.findById(1)).thenReturn(Optional.of(photo));

		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isCreated());
		// .andResult(jsonPath("evenement").value("UpdatedEvenement"));

		photo.setId(2);
		mockMvc.perform(put(BASE_URL + "/update").contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testNew() throws Exception {
		when(photoRepository.save(photo)).thenReturn(photo);
		when(photoRepository.findById(1)).thenReturn(Optional.of(photo));
		photo.setId(2);

		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isCreated());

		photo.setId(1);
		mockMvc.perform(
				post(BASE_URL + "/new").accept(JSON).contentType(JSON).content(objectMapper.writeValueAsString(photo)))
				.andExpect(status().isConflict());
	}
}
