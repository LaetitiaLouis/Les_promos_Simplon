package co.simplon.repository;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
}
