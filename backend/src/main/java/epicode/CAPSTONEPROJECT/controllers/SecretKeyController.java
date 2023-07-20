package epicode.CAPSTONEPROJECT.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecretKeyController {

	@Value("${spring.application.jwt.secret}")
	private String secretKey;

	@GetMapping("/secret-key")
	public ResponseEntity<String> getSecretKey() {
		return ResponseEntity.ok(secretKey);
	}

}
