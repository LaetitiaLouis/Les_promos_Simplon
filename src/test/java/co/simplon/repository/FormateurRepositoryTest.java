package co.simplon.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.simplon.model.Formateur;

@DataJpaTest
public class FormateurRepositoryTest {
	
	@Autowired
	private FormateurRepository utilisateurRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
		
	@Test
	public void testFindByPseudo() {
		Formateur user = new Formateur();
		user.setPseudo("Testeur");
		
		Formateur testUser = testEntityManager.persistFlushFind(user);
		Formateur userResult = this.utilisateurRepository.findByPseudo("Testeur").get();
		
		assertThat(userResult.getPseudo()).isEqualTo(testUser.getPseudo());
			
	}
	
}