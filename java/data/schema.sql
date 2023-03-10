CREATE TABLE usuario (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	nome_completo VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	apelido VARCHAR(50),
	data_nascimento DATE NOT NULL,
	senha VARCHAR(128) NOT NULL,
	imagem_perfil VARCHAR(8000),
	ativo BOOLEAN NOT NULL DEFAULT true
);
ALTER TABLE usuario ADD CONSTRAINT pk_usuario PRIMARY KEY (id);
ALTER TABLE usuario ADD CONSTRAINT uk_usuario_email UNIQUE (email);


CREATE TABLE solicitacao (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	usuario_id BIGINT NOT NULL,
	amigo_id BIGINT NOT NULL
);
ALTER TABLE solicitacao ADD CONSTRAINT pk_solicitacao PRIMARY KEY (id);
ALTER TABLE solicitacao ADD CONSTRAINT uk_solicitacao_usuario_amigo UNIQUE (usuario_id, amigo_id);
ALTER TABLE solicitacao ADD CONSTRAINT fk_solicitacao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;
ALTER TABLE solicitacao ADD CONSTRAINT fk_solicitacao_amigo FOREIGN KEY (amigo_id) REFERENCES usuario;


CREATE TABLE amizade (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	usuario_id BIGINT NOT NULL,
	amigo_id BIGINT NOT NULL
);
ALTER TABLE amizade ADD CONSTRAINT pk_amizade PRIMARY KEY (id);
ALTER TABLE amizade ADD CONSTRAINT uk_amizade_usuario_amigo UNIQUE (usuario_id, amigo_id);
ALTER TABLE amizade ADD CONSTRAINT fk_amizade_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;
ALTER TABLE amizade ADD CONSTRAINT fk_amizade_amigo FOREIGN KEY (amigo_id) REFERENCES usuario;


CREATE TABLE gato (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	nome VARCHAR(255) NOT NULL,
	cor VARCHAR(50) NOT NULL,
	apelido VARCHAR(50),
	imagem VARCHAR(8000),
	dono_id BIGINT NOT NULL
);

ALTER TABLE gato ADD CONSTRAINT pk_gato PRIMARY KEY (id);
ALTER TABLE gato ADD CONSTRAINT fk_gato_dono FOREIGN KEY (dono_id) REFERENCES usuario;


CREATE TABLE publicacao (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	titulo VARCHAR(100) NOT NULL,
	mensagem VARCHAR(1000) NOT NULL,
	imagem VARCHAR(8000),
	curtidas BIGINT NOT NULL DEFAULT 0,
	visualizacao VARCHAR(7) NOT NULL,
	data_publicacao DATE,
	autor_id BIGINT NOT NULL
);

ALTER TABLE publicacao ADD CONSTRAINT pk_publicacao PRIMARY KEY (id);
ALTER TABLE publicacao ADD CONSTRAINT fk_publicacao_autor FOREIGN KEY (autor_id) REFERENCES usuario;
ALTER TABLE publicacao ADD CONSTRAINT ck_publicacao_visualizacao CHECK (visualizacao IN ('PUBLICO', 'PRIVADO'));


CREATE TABLE comentario (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	mensagem VARCHAR(500) NOT NULL,
	autor_id BIGINT NOT NULL,
	publicacao_id BIGINT NOT NULL
);

ALTER TABLE comentario ADD CONSTRAINT pk_comentario PRIMARY KEY (id);
ALTER TABLE comentario ADD CONSTRAINT fk_comentario_autor FOREIGN KEY (autor_id) REFERENCES usuario;
ALTER TABLE comentario ADD CONSTRAINT fk_comentario_publicacao FOREIGN KEY (publicacao_id) REFERENCES publicacao;


CREATE TABLE permissao (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	funcao VARCHAR(100) NOT NULL,
	usuario_id BIGINT NOT NULL
);
ALTER TABLE permissao ADD CONSTRAINT pk_permissao PRIMARY KEY (id);
ALTER TABLE permissao ADD CONSTRAINT uk_permissao UNIQUE (funcao, usuario_id);
ALTER TABLE permissao ADD CONSTRAINT fk_permissao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;