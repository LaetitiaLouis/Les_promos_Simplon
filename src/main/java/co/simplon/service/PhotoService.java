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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoService {

	private final String SEPAR = File.separator;
	private final String UPLOAD_DIR = System.getProperty("user.home") + SEPAR + "uploads" + SEPAR;

	public String upload(MultipartFile file, int id, boolean profile) throws Exception {
		return uploadFile(file, id, profile);

	}

	private String uploadFile(MultipartFile file, int id, boolean profile) throws Exception {
		File folders = new File(UPLOAD_DIR);
		folders.mkdirs();

		String fileName = profile ? "profile_" + id : "galerie_" + id;
		fileName += "_" + StringUtils.cleanPath(file.getOriginalFilename());

		Path copyLocation = Paths.get(UPLOAD_DIR + fileName);

		Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		return fileName;
	}

	public Resource getFile(String filename) throws MalformedURLException {
		return new UrlResource("file:" + UPLOAD_DIR + filename);
	}
}