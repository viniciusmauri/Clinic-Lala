/**
 * 
 */
package com.example.cliniclala.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliniclala.api.model.Categoria;
import com.example.cliniclala.api.repository.CategoriaRepository;

/**
 * @author Vinic
 *
 */

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> Listar(){
		return categoriaRepository.findAll();
		
	}

}
