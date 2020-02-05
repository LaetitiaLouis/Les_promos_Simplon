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
import org.springframework.test.web.servlet.MockMvc;

import co.simplon.model.Promo;
import co.simplon.repository.PromoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PromoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PromoRepository promoRepository;

	private final Promo promo = new Promo();

	private final String BASE_URL = "/api/promos";

	@BeforeEach
	public void setUp() {
		promo.setNom("LP4");
	}

	@Test
	public void testGetAll() throws Exception {
		when(promoRepository.findAll()).thenReturn(List.of(promo));

		mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].nom").value("LP4"));

		when(promoRepository.findAll()).thenReturn(new ArrayList<>());

		mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isNotFound());

	}

	@Test
	public void testFindById() throws Exception {
		when(promoRepository.findById("LP4")).thenReturn(Optional.of(promo));

		mockMvc.perform(get(BASE_URL + "/findById?nom=LP4")).andExpect(status().isOk())
				.andExpect(jsonPath("nom").value("LP4"));
		
		mockMvc.perform(get(BASE_URL + "/findById?nom=LP6")).andExpect(status().isNotFound());
	}

}
