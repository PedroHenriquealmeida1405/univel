CREATE DATABASE univel4a;
USE univel4a;


CREATE TABLE usuarios (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome_usuario VARCHAR(50) NOT NULL,
    senha_hash VARCHAR(64) NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(1) CHECK (tipo IN('U', 'A')) DEFAULT 'U',
    ativo VARCHAR(1) CHECK (ativo IN('T', 'F')) DEFAULT 'T'
    );
    
INSERT INTO usuarios VALUES(1, 'pedro ', '123456789',current_date(),'A','T');
    

