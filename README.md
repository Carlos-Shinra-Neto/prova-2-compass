## Questao 1 #
```sql
create table produtos.produto (
	id int unsigned not null,
	nome varchar(255) not null,
	descricao varchar(1000),
	desconto decimal(5,2) default 0.0,
	data_inicial date default current_date,
	primary key(id)
);
```

## Questao 2 #
```sql
select p.* from produtos.produto p where p.data_inicial between '2020-01-01' and '2021-08-15';
```

## Questao 3 #
```sql
select p.* from produtos.produto p where p.data_inicial not between '2020-01-01' and '2021-08-15';
```

## Questao 4 #
```sql
create table produtos.nota_fiscal (
	numero int unsigned not null,
	fk_produto int unsigned not null,
	valor decimal(7,2) not null,
	primary key(numero),
	constraint produto_possui_nota_fiscal foreign key (fk_produto) references produtos.produto(id)
);
```

## Questao 5 #
```sql
select nf.numero from produtos.nota_fiscal nf
join produtos.produto p on p.id = nf.fk_produto
where p.id = 455644;
```

## Questao 6 #
```sql
alter table produtos.nota_fiscal change numero order_id int unsigned not null;
```

## Questao 7 #
```sql
update produtos.nota_fiscal set fk_produto = 243924 where fk_produto = 455644;
```

## Questao 8 #
```sql
alter table produtos.produto add primary key(id);
alter table produtos.nota_fiscal add primary key(order_id);
alter table produtos.nota_fiscal add constraint produto_possui_nota_fiscal foreign key(fk_produto) references produtos.produto(id);
```

## Questao 9 #
executar a classe com.produtos.ApplicationProduto

## Questao 10 #
```sql
create table produtos.funcionarios (
	id int unsigned auto_increment,
	nome varchar (255),
	sentimento enum ('feliz', 'chateado', 'neutro'),
	primary key(id)
);
```

executar a classe com.produtos.AplicacaoSentimento
