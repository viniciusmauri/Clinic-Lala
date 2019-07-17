CREATE TABLE IF NOT EXISTS pessoa(
	cod BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(20),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	cpf VARCHAR(11) NOT NULL,
	ativo BOOLEAN NOT NULL
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	
	INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, cpf, ativo) 
	values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto','SP','57895265944', true);
	
	