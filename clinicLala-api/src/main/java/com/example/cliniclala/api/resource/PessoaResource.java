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
import com.example.cliniclala.api.model.Pessoa;
import com.example.cliniclala.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publicar;

	@GetMapping
	public List<Pessoa> Listar() {
		return pessoaRepository.findAll();
	}

	@PostMapping @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, 
			HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publicar.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCod()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@GetMapping("/{cod}")
	public Object buscarPeloCodigo(@PathVariable Long cod) {
		return this.pessoaRepository.findById(cod)
				.map(pessoa -> ResponseEntity.ok(pessoa))
				.orElse(ResponseEntity.notFound().build());
	}
}
