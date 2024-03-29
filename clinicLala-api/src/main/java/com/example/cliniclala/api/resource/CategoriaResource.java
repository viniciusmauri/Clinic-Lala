package com.example.cliniclala.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliniclala.api.event.RecursoCriadoEvent;
import com.example.cliniclala.api.model.Categoria;
import com.example.cliniclala.api.repository.CategoriaRepository;

/**
 * @author Vinic
 *
 */

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ApplicationEventPublisher publicar;

	@GetMapping
	public List<Categoria> Listar() {
		return categoriaRepository.findAll();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> criar(@Valid @RequestBody 
			Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);

		publicar.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCod()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);

	}

	@GetMapping("/cod")
	public Object buscarPeloCodigo(@PathVariable Long cod) {
		return this.categoriaRepository.findById(cod).map(categoria -> ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}

}
