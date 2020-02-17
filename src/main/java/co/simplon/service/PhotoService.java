package co.simplon.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.model.Photo;

/**
 * Service de gestion des fichiers photos
 * @author Laëtitia, Sébastien et Cédric 
 *
 */
@Service
public class PhotoService {

	// Séparateur de dossier (multi os)
	private final String SEPAR = File.separator;
	
	// Dossier de sauvegardes des photos
	private final String UPLOAD_DIR = System.getProperty("user.home") + SEPAR + "uploads" + SEPAR;

	/**
	 * Sauvegarder le fichier photo sur le disque dur
	 * @param Le fichier à sauvegarder
	 * @param L'objet photo 
	 * @return Le nom de la photo sur le disque dur
	 * @throws Exception si il y a problème d'écriture
	 */
	public String save(MultipartFile file, Photo photo) throws Exception {
		File folders = new File(UPLOAD_DIR);
		folders.mkdirs(); // creer le dossier uploads si il n'exise pas
		String nomPhoto = photo.getNom().replaceAll("[^A-Za-z0-9àáâãäåçèéêëìíîïðòóôõöùúûüýÿ]", "_"); // remplacer les caractères spéciaux mais conserver les accents
		String fileName = photo.getId() + "_" + nomPhoto + ".jpg";
		Path copyLocation = Paths.get(UPLOAD_DIR + fileName);
		Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING); // sauvegarde

		return fileName;
	}

	/**
	 * Obtenir un fichier du disque dur
	 * @param Le nom du fichier
	 * @return Le fichier souhaité sous forme d'objet Resource
	 * @throws MalformedURLException
	 */
	public Resource get(String filename) throws MalformedURLException {
		return new UrlResource("file:" + UPLOAD_DIR + filename);
	}

	/**
	 * Supprimer un fichier du disque dur
	 * @param La photo à supprimer
	 * @param L'url du endpoint de download des photos 
	 */
	public void delete(Photo photo, String photosEndpoint)  {

		try {
			String filename = UPLOAD_DIR + photo.getImageUrl().replace(photosEndpoint, ""); // récupération du nom de fichier 
			File file = new File(filename);
			if(file.exists()) {
				file.delete(); // suppression
			} else {
				System.out.println("Photo inexistante");
			}

		} catch (Exception e) {
			return;
		}

	}
}