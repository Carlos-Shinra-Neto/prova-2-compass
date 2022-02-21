#drop database produtos;
create database produtos;

#QUESTÃO 1
#drop table produtos.produto;
create table produtos.produto (
	id int unsigned not null,
	nome varchar(255) not null,
	descricao varchar(1000),
	desconto decimal(5,2) default 0.0,
	data_inicial date default current_date,
	primary key(id)
);

#Alimentando tabela
insert into produtos.produto values
(1, 'rafael', 'sou eu', 0.00, '2020-05-05'),
(2, 'carlos', 'nao sou eu', 100.00, '2021-05-05'),
(3, 'pedro', 'nao sou eu', 55.55, '2021-01-01'),
(4, 'maria', 'nao sai', 30.00, '2022-05-05'),
(5, 'joao', 'nao sai', 10.00, '2019-05-05'),
(455644, 'teste consulta', 'sair na query de consulta', 98.23, null),
(243924, 'teste consulta 2', 'sair na update', 58.23, null);

#QUESTÃO 2
select p.* from produtos.produto p where p.data_inicial between '2020-01-01' and '2021-08-15';

#QUESTÃO 3
select p.* from produtos.produto p where p.data_inicial not between '2020-01-01' and '2021-08-15';

#drop table produtos.nota_fiscal;
#QUESTÃO 4
create table produtos.nota_fiscal (
	numero int unsigned not null,
	fk_produto int unsigned not null,
	valor decimal(7,2) not null,
	primary key(numero),
	constraint produto_possui_nota_fiscal foreign key (fk_produto) references produtos.produto(id)
);
#Alimentando tabela
insert into produtos.nota_fiscal values 
(1, 1, 1250.98),
(2, 3, 50.00),
(3, 4, 1550.68),
(4, 5, 1250.64),
(5, 2, 1250.25),
(6, 1, 1250.94),
(7, 455644, 437.87),
(8, 455644, 8563.11),
(9, 455644, 4578.22),
(10, 3, 1250.12);

#QUESTÃO 5
select nf.numero from produtos.nota_fiscal nf
join produtos.produto p on p.id = nf.fk_produto
where p.id = 455644;

#QUESTÃO 6
alter table produtos.nota_fiscal change numero order_id int unsigned not null;

#QUESTÃO 7
update produtos.nota_fiscal set fk_produto = 243924 where fk_produto = 455644;

#alter table produtos.nota_fiscal drop constraint produto_possui_nota_fiscal;
#alter table produtos.nota_fiscal drop primary key;
#alter table produtos.produto drop primary key;

#QUESTÃO 8
alter table produtos.produto add primary key(id);
alter table produtos.nota_fiscal add primary key(order_id);
alter table produtos.nota_fiscal add constraint produto_possui_nota_fiscal foreign key(fk_produto) references produtos.produto(id);

#drop table produtos.funcionarios;

#Questão 10
create table produtos.funcionarios (
id int unsigned auto_increment,
nome varchar (255),
sentimento enum ('feliz', 'chateado', 'neutro'),
primary key(id)
);