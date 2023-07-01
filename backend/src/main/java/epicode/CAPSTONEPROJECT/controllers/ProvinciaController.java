package epicode.CAPSTONEPROJECT.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epicode.CAPSTONEPROJECT.entities.Provincia;
import epicode.CAPSTONEPROJECT.services.ProvinciaService;

@RestController
@RequestMapping("/api/province")
public class ProvinciaController {

	private final ProvinciaService provinciaService;

	@Autowired
	public ProvinciaController(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}

	@GetMapping
	public List<Provincia> getAllProvince() {
		return provinciaService.getAllProvince();
	}

	@GetMapping("/{id}")
	public Provincia getProvinciaById(@PathVariable UUID id) {
		return provinciaService.getProvinciaById(id);
	}

	@PostMapping
	public Provincia createProvincia(@RequestBody Provincia provincia) {
		return provinciaService.createProvincia(provincia);
	}

	@PutMapping("/{id}")
	public Provincia updateProvincia(@PathVariable UUID id, @RequestBody Provincia provincia) {
		return provinciaService.updateProvincia(id, provincia);
	}

	@DeleteMapping("/{id}")
	public void deleteProvincia(@PathVariable UUID id) {
		provinciaService.deleteProvincia(id);
	}
}
