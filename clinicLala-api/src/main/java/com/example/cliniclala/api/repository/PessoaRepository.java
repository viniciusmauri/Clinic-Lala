package com.example.cliniclala.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cliniclala.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
