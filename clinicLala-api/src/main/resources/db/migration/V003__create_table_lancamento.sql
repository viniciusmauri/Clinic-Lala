CREATE TABLE IF NOT EXISTS lancamento(
	cod BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL, 
	data_vencimento DATE NOT NULL, 
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	cod_categoria BIGINT(20) NOT NULL,
	cod_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (cod_categoria) REFERENCES categoria(cod),
	FOREIGN KEY (cod_pessoa) REFERENCES pessoa(cod)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, cod_categoria, cod_pessoa) values ('Salário mensal', '2019-06-10', null, 7500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);