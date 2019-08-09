package com.example.cliniclala.api.repository.lancamento;

import java.util.List;

import com.example.cliniclala.api.model.Lancamento;
import com.example.cliniclala.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
