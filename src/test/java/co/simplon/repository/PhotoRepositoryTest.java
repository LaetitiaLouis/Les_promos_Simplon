package co.simplon.repository;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
		int i=0;
		
		photo.setCategorie("Evenement");
		photo2.setCategorie("Evenement");
		photo3.setCategorie("Profil");

		testEntityManager.persistFlushFind(photo);
		testEntityManager.persistFlushFind(photo2);
		testEntityManager.persistFlushFind(photo3);
		
		List<Photo> photoResult = this.photoRepository.findByCategorie("Evenement");
		assertThat(photoResult.size()).isEqualTo(2);
		for(Photo currentPhoto:photoResult) {
			assertThat(photoResult.get(i).getCategorie()).isEqualTo(currentPhoto.getCategorie());
			i++;
		}
		
	}
	
	@Test
	public void testFindByUtilisateur() {
		Photo photo=new Photo();
		Photo photo2=new Photo();
		Photo photo3=new Photo();
		int i=0;
		Utilisateur user1=new Utilisateur();
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
		for(Photo currentPhoto:photoResult) {
			assertThat(currentPhoto.getUtilisateur().getId()).isEqualTo(user1.getId());
			i++;
		}
		
	}
}
