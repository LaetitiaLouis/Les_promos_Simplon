package co.simplon.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.model.HobbyCompetenceLangage;
import co.simplon.model.Photo;
import co.simplon.model.Utilisateur;
import co.simplon.repository.HobbyCompetenceLangageRepository;
import co.simplon.repository.PhotoRepository;
import co.simplon.repository.UtilisateurRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UtilisateurRepository utilisateurRepository;

	@MockBean
	PhotoRepository photoRepository;

	@MockBean
	HobbyCompetenceLangageRepository hobbyRepository;

	@Autowired
	private ObjectMapper objectMapper;

	JacksonTester<Utilisateur> userJacksonTester;

//	@BeforeEach
//	public void setUp() {
//		JacksonTester.initFields(this, objectMapper);
//	}

	private final Utilisateur user = new Utilisateur();
	private final List<Utilisateur> users = new ArrayList<>();
	private final String BASE_URL = "/api/utilisateurs";
	private final MediaType JSON = MediaType.APPLICATION_JSON;

	@BeforeEach
	public void setUp() {
		user.setPseudo("Pseudo");
		user.setMotDePasse("Password");
		users.add(user);
		JacksonTester.initFields(user, objectMapper);
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(BASE_URL + "/delete?id=1").contentType(JSON).accept(JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void testFindById() throws Exception {

		when(this.utilisateurRepository.findById(1)).thenReturn(Optional.of(user));

		this.mockMvc.perform(get(BASE_URL + "/findById?id=1").contentType(JSON).accept(JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("pseudo").value("Pseudo"));

		this.mockMvc.perform(get(BASE_URL + "/findById?id=2").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testGetAllUsers() throws Exception {
		List<Utilisateur> userList = new ArrayList<>();
		userList.add(user);
		when(this.utilisateurRepository.findAll()).thenReturn(userList);

		this.mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));
		userList.clear();
		this.mockMvc.perform(get(BASE_URL + "/all")).andExpect(status().isNotFound());
	}

	@Test
	public void testFindByPseudo() throws Exception {
		when(this.utilisateurRepository.findByPseudo("Pseudo")).thenReturn(Optional.of(user));

		this.mockMvc.perform(get(BASE_URL + "/findByPseudo?pseudo=Pseudo").contentType(JSON).accept(JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("pseudo").value(user.getPseudo()));

		this.mockMvc.perform(get(BASE_URL + "/findByPseudo?pseudo=BadPseudo").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testFindByPhoto() throws Exception {
		Photo photo = new Photo();
		photo.setUtilisateur(user);
		when(this.photoRepository.findById(1)).thenReturn(Optional.of(photo));

		this.mockMvc.perform(get(BASE_URL + "/findByPhoto?id=1").contentType(JSON).accept(JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("pseudo").value("Pseudo"));

		this.mockMvc.perform(get(BASE_URL + "/findByPhoto?id=32").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testFindByHobby() throws Exception {
		HobbyCompetenceLangage hobby = new HobbyCompetenceLangage();
		hobby.setUtilisateurs(users);
		when(this.hobbyRepository.findById("sport")).thenReturn(Optional.of(hobby));

		this.mockMvc.perform(get(BASE_URL + "/findByHobby?hobby=sport").contentType(JSON).accept(JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));

		this.mockMvc.perform(get(BASE_URL + "/findByHobby?hobby=couture").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testConnect() throws Exception {
		when(utilisateurRepository.findByPseudo("Pseudo")).thenReturn(Optional.of(user));

		this.mockMvc
				.perform(post(BASE_URL + "/connect").contentType(JSON).content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andExpect(jsonPath("pseudo").value("Pseudo"));

	}

	@Test
	public void testFindByNom() throws Exception {
		when(utilisateurRepository.findByNom("GoodName")).thenReturn(users);

		this.mockMvc.perform(get(BASE_URL + "/findByNom?nom=GoodName").contentType(JSON).accept(JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));
		this.mockMvc.perform(get(BASE_URL + "/findByNom?nom=BadName").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());

	}

	@Test
	public void testFindByPrenom() throws Exception {
		when(utilisateurRepository.findByPrenom("GoodName")).thenReturn(users);

		this.mockMvc.perform(get(BASE_URL + "/findByPrenom?prenom=GoodName").contentType(JSON).accept(JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));
		this.mockMvc.perform(get(BASE_URL + "/findByPrenom?prenom=BadName").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());

	}

	@Test
	public void testFindByNomPrenom() throws Exception {
		when(utilisateurRepository.findByNom("%GoodName%")).thenReturn(users);
		when(utilisateurRepository.findByPrenom("%GoodName%")).thenReturn(users);

		this.mockMvc.perform(get(BASE_URL + "/findByNomPrenom?nomPrenom=GoodName").contentType(JSON).accept(JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.[0].pseudo").value("Pseudo"));
		this.mockMvc.perform(get(BASE_URL + "/findByNomPrenom?nomPrenom=BadName").contentType(JSON).accept(JSON))
				.andExpect(status().isNotFound());

	}

}
