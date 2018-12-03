Create Database Estoque

use Estoque

Create Table Clientes 
(
	Cod_cliente int primary key,
	Nome_cliente varchar(30)
)

create table Fornecedor
(
	Cod_fornecedor int primary key,
	Nome_fornecedor varchar(30)
)

create table Produto
(
	Cod_produto int primary key,
	Nome_produto varchar(30),
	Posicao varchar(50),
	Valor_produto decimal(10,2),
	Qtde_produto int,
	Tempo_reposicao int
)


create table Compra
(
	Cod_compra int primary key,
	Cod_fornecedor int foreign key references Fornecedor (Cod_fornecedor),
	Cod_produto int foreign key references Produto (Cod_produto),
	Qtde_comprada int,
	Valor_compra decimal(10,2),
	Data_compra datetime
)


create table Venda
(
	Cod_venda int primary key,
	Cod_fornecedor int foreign key references Fornecedor (Cod_fornecedor),
	Cod_produto int foreign key references Produto (Cod_produto),
	Qtde_vendida int,
	Valor_venda decimal(10,2),
	Data_venda datetime
)

create trigger trg_VenderProduto on Venda
for INSERT
as  
	update Produto set Qtde_produto -= (select Qtde_vendida from inserted) where Cod_produto = (select Cod_produto from inserted)

create trigger trg_ComprarProduto on Compra
for insert
as
	update Produto set Qtde_produto += (select Qtde_comprada from inserted) where Cod_produto = (select Cod_produto from inserted)