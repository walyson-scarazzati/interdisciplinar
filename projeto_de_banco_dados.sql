CREATE DATABASE IF NOT EXISTS venda_de_titulos 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

USE venda_de_titulos;

-- Table: Pessoas
CREATE TABLE Pessoas (
  id        INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  nome      VARCHAR(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  data_nasc DATE         NULL,
  endereco  VARCHAR(40)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  telefone  VARCHAR(30)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  email     VARCHAR(40)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  rg        VARCHAR(15)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  cpf       VARCHAR(11)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  CONSTRAINT UNIQUE (rg),
  CONSTRAINT UNIQUE (cpf),
  CONSTRAINT UNIQUE (email),
  CONSTRAINT unique_cpf_rg_email UNIQUE (cpf, rg, email)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Associados
CREATE TABLE Associados (
  associado_id INT  NOT NULL PRIMARY KEY,
  profissao    VARCHAR(60)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  FOREIGN KEY (associado_id) REFERENCES Pessoas(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Funcionarios
CREATE TABLE Funcionarios (
  funcionario_id    INT  NOT NULL PRIMARY KEY,
  usuario           VARCHAR(30)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  senha             VARCHAR(255)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  salario           DECIMAL(10,2) NOT NULL,
  tipo              INT  NOT NULL,
  tentativas_falhas INT NOT NULL DEFAULT 0,
  bloqueado         TINYINT(1) NOT NULL DEFAULT 0,
  FOREIGN KEY (funcionario_id) REFERENCES Pessoas(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Parentescos
CREATE TABLE Parentescos (
  parentesco_id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  descricao     VARCHAR(20)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Dependentes
CREATE TABLE Dependentes (
  dependente_id INT  NOT NULL PRIMARY KEY,
  parentesco_id INT  NOT NULL,
  associado_id  INT  NOT NULL,
  FOREIGN KEY (dependente_id) REFERENCES Pessoas(id),
  FOREIGN KEY (parentesco_id) REFERENCES Parentescos(parentesco_id),
  FOREIGN KEY (associado_id)  REFERENCES Pessoas(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Categorias
CREATE TABLE Categorias (
  id          INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  descricao   VARCHAR(15)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  valor       DECIMAL(10,2) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Contratos_Titulos
CREATE TABLE Contratos_Titulos (
  id             INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data_contrato  DATE         NOT NULL,
  data_cancel    DATE         NULL,
  status         INT  NOT NULL,
  associado_id   INT  NOT NULL,
  funcionario_id INT  NOT NULL,
  categorias_id  INT  NOT NULL,
  FOREIGN KEY (associado_id)   REFERENCES Pessoas(id),
  FOREIGN KEY (funcionario_id) REFERENCES Pessoas(id),
  FOREIGN KEY (categorias_id)  REFERENCES Categorias(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Mensalidades
CREATE TABLE Mensalidades (
  id               INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  preco            DECIMAL(10,2) NULL,
  data_pgto        DATE          NULL,
  data_venc        DATE          NOT NULL,
  valor            DECIMAL(10,2) NULL,
  mes_ref          INT   NOT NULL,
  contrato_id      INT   NOT NULL,
  forma_pagamento  VARCHAR(20)   NULL,          -- DINHEIRO, CARTAO ou CHEQUE
  valor_recebido   DECIMAL(10,2) NULL,          -- quantia informada pelo cliente (pagamento em dinheiro)
  troco            DECIMAL(10,2) NULL,          -- troco devido ao cliente
  juros_aplicado   TINYINT(1)    NOT NULL DEFAULT 0,
  FOREIGN KEY (contrato_id) REFERENCES Contratos_Titulos(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Log_Vendas (R1.3 - registro de venda no log completada)
CREATE TABLE Log_Vendas (
  id             INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  contrato_id    INT  NOT NULL,
  associado_id   INT  NOT NULL,
  funcionario_id INT  NOT NULL,
  data_hora      DATETIME NOT NULL,
  descricao      VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  FOREIGN KEY (contrato_id)    REFERENCES Contratos_Titulos(id),
  FOREIGN KEY (associado_id)   REFERENCES Pessoas(id),
  FOREIGN KEY (funcionario_id) REFERENCES Pessoas(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Contas_Receber (R2.6 - pagamentos com cartão a receber da operadora)
CREATE TABLE Contas_Receber (
  id             INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mensalidade_id INT  NOT NULL,
  valor          DECIMAL(10,2) NOT NULL,
  data_registro  DATETIME NOT NULL,
  status         VARCHAR(20) NOT NULL DEFAULT 'PENDENTE', -- PENDENTE ou RECEBIDO
  FOREIGN KEY (mensalidade_id) REFERENCES Mensalidades(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Table: Modalidades_Esportes
CREATE TABLE Modalidades_Esportes (
  id            INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  descricao     VARCHAR(50)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  categoria_id  INT  NOT NULL,
  FOREIGN KEY (categoria_id) REFERENCES Categorias(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Inserting data
INSERT INTO Pessoas (nome, data_nasc, endereco, telefone, email, RG, CPF)
VALUES
('Ana Felisberto', '1988-10-02', 'Rua: Ipiranga', '32146894', 'ana_felisberto@hotmail.com', '48786451-7', '40599520808'),
('Beatriz Graciosa', '1977-01-10', 'Rua: Salvador', '32185943', 'beatriz_graciosa@hotmail.com', '50421211-9', '11400191444'),
('Karina Formosa','1957-01-10','Rua: Dos poetas','32198392','karina_formosa@hotmail.com','30421595-1','61211418110'),
('Débora Linda', '1964-09-10', 'Rua: Potirendaba', '32947297', 'debora_linda@hotmail.com', '11015201-14', '61100000818'),
('Eliana Cristina', '1985-05-09', 'Rua: Votuporanga', '32483018', 'eliana_cristina@hotmail.com', '28462411-X', '10100995122'),
('Fernanda Cristal', '1989-07-05', 'Rua: Brigadeiro', '32194029', 'fernanda_cristal@hotmail.com', '22548244-9', '84522506928'),
('Gisele Esmeralda', '1987-04-08', 'Rua: Santa Efigenia', '32198384', 'gisele_esmeralda@hotmail.com', '76751244-9', '72679931912'),
('Pedro Flavio', '2009-10-10', 'Rua: Brasilia', '997143269', 'pedro_flavio@hotmail.com', '77291431-8', '80269841917'),
('Camila Rosa', '2007-09-01', 'Rua: México', '981116119', 'camila_rosa@hotmail.com', '99212129-4', '20196124819'),
('Micaela Pérola', '2005-03-05', 'Rua: Mirassol', '988551129', 'micaela_perola@hotmail.com', '18716121-1', '86979727877');

-- Insert into Associados
INSERT INTO Associados (associado_id, profissao) 
VALUES (1, 'Dentista'), (2, 'Mecânica'), (3, 'Advogada'), (4, 'Professora');

-- Insert into Funcionarios
INSERT INTO Funcionarios (funcionario_id, usuario, senha, salario, tipo)
VALUES (5, 'Eliana', '65536:1wJEMfrA22DRiVP5wLhUjg==:Ha6DqgWWmbscayuqSWAOIL+/ZPs=', 2000.00, 1), (6, 'Fernanda', '65536:juOyCMRSg4K++pbsPsYiTA==:SQcqCzeMh6IuuJmVQFRofh4k3y0=', 2100.00, 1), (7, 'Gisele', '65536:WCm85nAWFmRlGhjVYUeIag==:whRjiwj/vRiWEj3d+GCu2iDNh+8=', 1900.00, 1);
-- Senhas de exemplo (texto puro, apenas para referência do desenvolvedor): Eliana=xyz, Fernanda=pqk, Gisele=wkj

-- Insert into Parentescos
INSERT INTO Parentescos (descricao) 
VALUES ('Pai'), ('Tio'), ('Mãe'), ('Tia');

-- Insert into Dependentes
INSERT INTO Dependentes (dependente_id, parentesco_id, associado_id) 
VALUES (8, 3, 2), (9, 3, 3), (10, 3, 4);

-- Insert into Categorias
INSERT INTO Categorias (descricao, valor) 
VALUES ('Patrono', 400.00), ('Mensal', 40.00), ('Convite', 15.00);

-- Insert into Contratos_Titulos
INSERT INTO Contratos_Titulos (data_contrato, data_cancel, status, associado_id, funcionario_id, categorias_id) 
VALUES ('2010-10-10', NULL, 1, 1, 5, 1), ('2001-05-20', NULL, 1, 2, 6, 2), ('2011-01-01', NULL, 1, 3, 7, 3);

-- Insert into Mensalidades
INSERT INTO Mensalidades (preco, data_pgto, data_venc, valor, mes_ref, contrato_id) 
VALUES (40.00, '2011-01-05', '2011-01-10', 40.00, 1, 1), 
       (40.00, '2011-02-05', '2011-02-10', 40.00, 2, 1),
       (40.00, '2011-03-05', '2011-03-10', 40.00, 3, 2),
       (40.00, '2011-04-05', '2011-04-10', 40.00, 4, 2),
       (40.00, '2011-05-05', '2011-05-10', 40.00, 5, 3);

-- Insert into Modalidades_Esportes
INSERT INTO Modalidades_Esportes (descricao, categoria_id)
VALUES ('Futebol', 1), ('Basquete', 1), ('Vôlei', 2);

