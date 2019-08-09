package com.example.cliniclala.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.cliniclala.api.model.Lancamento;
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
	
	public Lancamento atualizar(Long cod, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoPeloCod(cod);
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "cod");
		return this.lancamentoRepository.save(lancamentoSalvo);
	}

	private Lancamento buscarLancamentoPeloCod(Long cod) {
		Lancamento lancamentoSalvo = this.lancamentoRepository.findById(cod)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		if(lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
	
		return lancamentoSalvo;
	}

}
