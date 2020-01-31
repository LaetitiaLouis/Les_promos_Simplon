package co.simplon.repository;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.simplon.model.Utilisateur;

@DataJpaTest
public class UtilisateurRepositoryTest {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testFindByPseudo() {
		Utilisateur user = new Utilisateur();
		user.setPseudo("Testeur");

		Utilisateur testUser = testEntityManager.persistFlushFind(user);
		Utilisateur userResult = this.utilisateurRepository.findByPseudo("Testeur").get();

		assertThat(userResult.getPseudo()).isEqualTo(testUser.getPseudo());

	}

	@Test
	public void testFindByNom() {
		Utilisateur user = new Utilisateur();
		Utilisateur user2 = new Utilisateur();

		user.setNom("Cedric");
		user2.setNom("Eric");

		Utilisateur testUser = testEntityManager.persistFlushFind(user);
		Utilisateur testUser2 = testEntityManager.persistFlushFind(user2);
		List<Utilisateur> userResult = this.utilisateurRepository.findByNom("Cedric");
		assertThat(userResult.size()).isEqualTo(1);
		assertThat(userResult.get(0).getNom()).isEqualTo(testUser.getNom());

		List<Utilisateur> userResult2 = this.utilisateurRepository.findByNom("ric");
		assertThat(userResult2.size()).isEqualTo(2);
		if (userResult2.get(0).getNom().equals("Eric")) {
			assertThat(userResult2.get(0).getNom()).isEqualTo(testUser2.getNom());
			assertThat(userResult2.get(1).getNom()).isEqualTo(testUser.getNom());
		}
		else {
			assertThat(userResult2.get(0).getNom()).isEqualTo(testUser.getNom());
			assertThat(userResult2.get(1).getNom()).isEqualTo(testUser2.getNom());	
		}
		
	}
	@Test
	public void testFindByPrenom() {
		Utilisateur user = new Utilisateur();
		Utilisateur user2 = new Utilisateur();

		user.setPrenom("Bastien");
		user2.setPrenom("Sebastien");

		Utilisateur testUser = testEntityManager.persistFlushFind(user);
		Utilisateur testUser2 = testEntityManager.persistFlushFind(user2);
		List<Utilisateur> userResult = this.utilisateurRepository.findByPrenom("Sebastien");
		assertThat(userResult.size()).isEqualTo(1);
		assertThat(userResult.get(0).getPrenom()).isEqualTo(testUser2.getPrenom());

		
		List<Utilisateur> userResult2 = this.utilisateurRepository.findByPrenom("astien");
		assertThat(userResult2.size()).isEqualTo(2);
		if (userResult2.get(0).getPrenom().equals("Sebastien")) {
			assertThat(userResult2.get(0).getPrenom()).isEqualTo(testUser2.getPrenom());
			assertThat(userResult2.get(1).getPrenom()).isEqualTo(testUser.getPrenom());
		}
		else {
			assertThat(userResult2.get(0).getPrenom()).isEqualTo(testUser.getPrenom());
			assertThat(userResult2.get(1).getPrenom()).isEqualTo(testUser2.getPrenom());	
		}
		
	}
}
