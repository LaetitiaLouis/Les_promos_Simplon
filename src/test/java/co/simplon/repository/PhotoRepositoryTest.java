package co.simplon.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.simplon.model.Photo;
import co.simplon.model.Utilisateur;

@DataJpaTest
public class PhotoRepositoryTest {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	

	@Test
	public void testFindByCategorie() {
		Photo photo=new Photo();
		Photo photo2=new Photo();
		Photo photo3=new Photo();
		
		photo.setCategorie("Evenement");
		photo2.setCategorie("Evenement");
		photo3.setCategorie("Profil");

		testEntityManager.persistFlushFind(photo);
		testEntityManager.persistFlushFind(photo2);
		testEntityManager.persistFlushFind(photo3);
		
		List<Photo> photoResult = this.photoRepository.findByCategorie("Evenement");
		assertThat(photoResult.size()).isEqualTo(2);
		for(Photo currentPhoto: photoResult) {
			assertThat("Evenement").isEqualTo(currentPhoto.getCategorie());
		}
		
	}
	
	@Test
	public void testFindByUtilisateur() {
		Photo photo=new Photo();
		Photo photo2=new Photo();
		Photo photo3=new Photo();

		Utilisateur user1=new Utilisateur();
		user1.setPseudo("Pseudo");
		Utilisateur user2=new Utilisateur();
		
		photo.setUtilisateur(user1);
		photo2.setUtilisateur(user2);
		photo3.setUtilisateur(user1);

		testEntityManager.persistFlushFind(user1);
		testEntityManager.persistFlushFind(user2);
		testEntityManager.persistFlushFind(photo);
		testEntityManager.persistFlushFind(photo2);
		testEntityManager.persistFlushFind(photo3);
		
		List<Photo> photoResult = this.photoRepository.findByUtilisateur(user1);
		assertThat(photoResult.size()).isEqualTo(2);
		
		for(Photo currentPhoto: photoResult) {
			assertThat("Pseudo").isEqualTo(currentPhoto.getUtilisateur().getPseudo());
		}
		
	}
}
