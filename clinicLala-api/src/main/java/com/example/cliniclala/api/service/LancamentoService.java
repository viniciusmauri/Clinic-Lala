package com.example.cliniclala.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cliniclala.api.model.Lancamento;
import com.example.cliniclala.api.model.Pessoa;
import com.example.cliniclala.api.repository.LancamentoRepository;
import com.example.cliniclala.api.repository.PessoaRepository;
import com.example.cliniclala.api.service.exception.PessoaInexistenteOuInativaException;

public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(Lancamento lancamento) {	
		if (lancamento.getPessoa().getCod() == null) {
			throw new PessoaInexistenteOuInativaException();
		}
			return lancamentoRepository.save(lancamento);
	}

}
