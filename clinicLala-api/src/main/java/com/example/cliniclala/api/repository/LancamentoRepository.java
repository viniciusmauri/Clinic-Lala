package com.example.cliniclala.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cliniclala.api.model.Lancamento;
import com.example.cliniclala.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
