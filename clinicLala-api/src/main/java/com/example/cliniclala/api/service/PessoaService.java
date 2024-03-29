package com.example.cliniclala.api.service;

import com.example.cliniclala.api.model.Pessoa;
import com.example.cliniclala.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

		
	public Pessoa atualizar(Long cod, Pessoa pessoa) {
			Pessoa pessoaSalva = buscarPessoaPeloCod(cod);
			 
			BeanUtils.copyProperties(pessoa, pessoaSalva, "cod");
			return this.pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long cod, Boolean ativo) {

		Pessoa pessoaSalva = buscarPessoaPeloCod(cod);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	public Pessoa buscarPessoaPeloCod(Long cod) {
		Pessoa pessoaSalva = this.pessoaRepository.findById(cod)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		if(pessoaSalva == null) {
		throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
