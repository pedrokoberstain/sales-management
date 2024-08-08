CREATE TABLE categoria
(
    codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome   VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

INSERT INTO categoria (nome) VALUES ('Tecnologia');
INSERT INTO categoria (nome) VALUES ('Acessorios para veiculos');
INSERT INTO categoria (nome) VALUES ('Esporte e Lazer');
INSERT INTO categoria (nome) VALUES ('Casa e Eletrodomesticos');
INSERT INTO categoria (nome) VALUES ('Joias e Relogios');