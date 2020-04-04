create database venda_de_titulos;

use venda_de_titulos;


create table Pessoas
(
  id        int         not null primary key AUTO_INCREMENT,
  nome      varchar(50) not null,
  data_nasc varchar(30) null,
  endereco  varchar(40) null,
  telefone  varchar(30)  not null,
  email     varchar(40) not null,
  RG        int         not null,
  CPF       int         not null
);

create table Associados
(
  associado_id   int         not null AUTO_INCREMENT,
  profissao      varchar(60) not null,
  foreign key (associado_id ) references pessoas(id)
);
select * from Pessoas;

   Select id, nome, data_nasc, endereco, telefone, email, rg, cpf, profissao
    from Pessoas p, Associados a
	where p.id = a.associado_id;


create table Funcionarios
(
  funcionario_id    int           not null AUTO_INCREMENT,
  usuario           varchar(30)   not null,
  senha             varchar(30)   not null,
  salario           decimal(10,2) not null,
  tipo              int           not null,
  foreign key (funcionario_id) references pessoas(id)
)

select * from Funcionarios;


   Select id, nome, data_nasc, endereco, telefone, email, rg, cpf, usuario, senha, salario, tipo
    from Pessoas p, Funcionarios f
	where p.id = f.funcionario_id;


create table Parentescos
(
  parentesco_id   int         not null primary key AUTO_INCREMENT,
  descricao       varchar(20) not null
);

create table Dependentes
(
  dependente_id  int not null,
  parentesco_id  int not null,
  associado_id   int not null,
  foreign key (dependente_id) references pessoas(id),
   foreign key (parentesco_id) references parentescos(parentesco_id),
  foreign key (associado_id)     references pessoas (id)
);

create table Categorias
(
  id        int         not null primary key AUTO_INCREMENT,
  descricao varchar(15) not null,
  valor     decimal(10,2)       not null
);



create table Contratos_Titulos
(
  id             int         not null primary key AUTO_INCREMENT,
  data_contrato  varchar(20) not null,
  data_cancel    varchar(20) null, 
  status         int         not null,
  associado_id   int         not null,
  funcionario_id int         not null,
  categorias_id int          not null,
  foreign key (associado_id) references pessoas(id),
  foreign key (funcionario_id) references pessoas(id),
  foreign key (categorias_id) references  categorias(id)
);

create table Mensalidades
(
  id          int         not null primary key AUTO_INCREMENT,
  preco       decimal(10,2)  null, --
  data_pgto   varchar(20) null,--
  data_venc   varchar(20) not null,
  valor       decimal(10,2)  null,--
  mes_ref     int         not null,
  contrato_id int         not null,
  foreign key (contrato_id)  references Contratos_Titulos(id)
);

create table Modalidades_Esportes
(
  id            int         not null primary key AUTO_INCREMENT,
  descricao     varchar(50) not null,
  categoria_id  int         not null,
   foreign key (categoria_id ) references categorias(id) 
);






-- sp_help Associados -- Comando para fazer o dicionario de dado

select * from Status;

select * from Associados a, Pessoas p where a.associado_id = p.id;

select * from Dependentes d, Pessoas p where d.dependente_id = p.id;

select * from Funcionarios f,  Pessoas p where f.funcionario_id= p.id;

select * from Parentescos;

select * from Contratos_Titulos;

select * from Mensalidades;

select parentesco_id, descricao from Parentescos p where p.parentesco_id =1;

------------------------------------------------

-- ASSOCIADO        
                
 select DISTINCT(A.associado_id),B.nome, B.CPF, CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade 
  from Associados A
  JOIN Pessoas B On B.id = A.associado_id
  JOIN Contratos_Titulos C on B.id = C.associado_id
  JOIN Mensalidades D ON C.id = D.contrato_id
  GROUP BY A.associado_id,B.nome, B.CPF, D.data_pgto, D.data_venc;
  
  --------------------------------------------------------
  
-- DEPENDENTE
  

  
      select DISTINCT(A.dependente_id),B.nome, B.CPF, 
      CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade 
         from Dependentes A 
         JOIN Pessoas B On B.id = A.dependente_id 
			JOIN Associados  E on E.associado_id = A.associado_id  
			join Pessoas      r on  E.associado_id = r.id   
			JOIN Contratos_Titulos C on r.id  = C.associado_id 
                JOIN Mensalidades D ON C.id = D.contrato_id 
               GROUP BY A.dependente_id,B.nome, B.CPF, D.data_pgto, D.data_venc ;
               
             
