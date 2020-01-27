package co.simplon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponse {
	public final static ResponseEntity<String> NOT_FOUND = ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("Aucun résultat pour cette requète.");
}
