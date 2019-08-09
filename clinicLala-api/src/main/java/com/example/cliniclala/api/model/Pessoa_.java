package com.example.cliniclala.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile SingularAttribute<Pessoa, Long> cpf;
	public static volatile SingularAttribute<Pessoa, Long> cod;
	public static volatile SingularAttribute<Pessoa, String> nome;

	public static final String ATIVO = "ativo";
	public static final String ENDERECO = "endereco";
	public static final String CPF = "cpf";
	public static final String COD = "cod";
	public static final String NOME = "nome";

}

