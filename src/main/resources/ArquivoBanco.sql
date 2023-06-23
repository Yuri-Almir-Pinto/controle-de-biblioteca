DROP TABLE IF EXISTS tb_livros;
DROP TABLE IF EXISTS tb_autores;
DROP TABLE IF EXISTS tb_usuarios;

CREATE TABLE tb_autores (
id int AUTO_INCREMENT PRIMARY KEY,
nome varchar(80)
);

CREATE TABLE tb_livros (
id int AUTO_INCREMENT PRIMARY KEY,
nome varchar(200),
dataLancamento date,
autores int,
estado varchar(20),
FOREIGN KEY(autores) REFERENCES tb_Autores (id)
);

CREATE TABLE tb_usuarios (
id int AUTO_INCREMENT PRIMARY KEY,
login varchar(50) unique,
senha varchar(50),
tipo varchar(20)
);

insert into tb_autores (nome) values ('Chandra');
insert into tb_autores (nome) values ('Uta');
insert into tb_autores (nome) values ('Claudette');
insert into tb_autores (nome) values ('Pamelina');
insert into tb_autores (nome) values ('Codi');
insert into tb_autores (nome) values ('Bram');
insert into tb_autores (nome) values ('Willette');
insert into tb_autores (nome) values ('Austine');
insert into tb_autores (nome) values ('Conn');
insert into tb_autores (nome) values ('Marci');
insert into tb_autores (nome) values ('Abbe');
insert into tb_autores (nome) values ('Orsa');
insert into tb_autores (nome) values ('Candida');
insert into tb_autores (nome) values ('Dodie');
insert into tb_autores (nome) values ('Corbett');
insert into tb_autores (nome) values ('Rheba');
insert into tb_autores (nome) values ('Bevvy');
insert into tb_autores (nome) values ('Aristotle');
insert into tb_autores (nome) values ('Mae');
insert into tb_autores (nome) values ('Walsh');

insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Hobbit', '1981-08-28', 1, 'EMPRESTADO');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Catcher in the Rye', '2006-05-08', 2, 'EMPRESTADO');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('Pride and Prejudice', '2014-09-11', 3, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Lord of the Rings', '2012-11-10', 4, 'EMPRESTADO');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('Brave New World', '2018-04-04', 5, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('Animal Farm', '1989-06-10', 6, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('Animal Farm', '1999-12-31', 7, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Lord of the Rings', '1982-11-24', 8, 'EMPRESTADO');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('Brave New World', '2004-05-19', 9, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Great Gatsby', '1986-07-08', 10, 'INDISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('1984', '2022-04-11', 11, 'EMPRESTADO');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('To Kill a Mockingbird', '1982-08-11', 12, 'INDISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('Pride and Prejudice', '2000-11-18', 13, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Catcher in the Rye', '2015-03-17', 14, 'EMPRESTADO');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Catcher in the Rye', '2012-08-23', 15, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('1984', '2019-08-05', 16, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Great Gatsby', '2017-06-29', 17, 'INDISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('Pride and Prejudice', '1999-10-15', 18, 'INDISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('To Kill a Mockingbird', '1988-09-14', 19, 'DISPONIVEL');
insert into tb_livros (nome, dataLancamento, autores, estado) values ('The Great Gatsby', '1992-05-20', 20, 'INDISPONIVEL');

insert into tb_usuarios (login, senha, tipo) values ('admin', 'admin', 'ADMINISTRADOR');

