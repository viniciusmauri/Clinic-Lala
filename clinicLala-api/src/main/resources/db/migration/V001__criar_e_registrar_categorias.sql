CREATE TABLE IF NOT EXISTS categoria(
	cod BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	INSERT INTO categoria (nome) values ('Massagens');
	INSERT INTO categoria (nome) values ('Estetica');
	INSERT INTO categoria (nome) values ('Outros');