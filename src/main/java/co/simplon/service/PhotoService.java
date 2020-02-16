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

@Service
public class PhotoService {

	private final String SEPAR = File.separator;
	private final String UPLOAD_DIR = System.getProperty("user.home") + SEPAR + "uploads" + SEPAR;

	public String save(MultipartFile file, Photo photo) throws Exception {
		File folders = new File(UPLOAD_DIR);
		folders.mkdirs();
		String nomPhoto = photo.getNom().replaceAll("[^A-Za-z0-9àáâãäåçèéêëìíîïðòóôõöùúûüýÿ]", "_");
		String fileName = photo.getId() + "_" + nomPhoto + ".jpg";
		Path copyLocation = Paths.get(UPLOAD_DIR + fileName);
		Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

		return fileName;
	}

	public Resource get(String filename) throws MalformedURLException {
		return new UrlResource("file:" + UPLOAD_DIR + filename);
	}

	public void delete(Photo photo, String photoUrl)  {

		try {
			String filename = UPLOAD_DIR + photo.getImageUrl().replace(photoUrl, "");
			File file = new File(filename);
			if(file.exists()) {
				file.delete();
			} else {
				System.out.println("Photo inexistante");
			}

		} catch (Exception e) {
			return;
		}

	}
}