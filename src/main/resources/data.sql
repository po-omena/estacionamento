INSERT INTO MODELO(modelo_nome, modelo_ano, modelo_versao) VALUES ('GOL', '2014', 'HATCH');
INSERT INTO MODELO(modelo_nome, modelo_ano, modelo_versao) VALUES ('PICASSO','2011','SUV');
INSERT INTO MODELO(modelo_nome, modelo_ano, modelo_versao) VALUES ('JEEP RENEGADE','2014','SUV');
INSERT INTO MODELO(modelo_nome, modelo_ano, modelo_versao) VALUES ('CAMARO GT','2018','LUXO');
INSERT INTO MODELO(modelo_nome, modelo_ano, modelo_versao) VALUES ('SAVEIRO','1997','PICAPE');

/*ENDERECO*/
INSERT INTO ENDERECO(BAIRRO,CEP,CIDADE,COMPLEMENTO,NUMERO,RUA) VALUES('SANTA MONICA','38412-333','UBERLANDIA','CASA','456','RUA TESTE Nº1');
INSERT INTO ENDERECO(BAIRRO,CEP,CIDADE,COMPLEMENTO,NUMERO,RUA) VALUES('JARDIM ALTAMIRA','47412-533','ARAGUARI','AP 302','1034','RUA DAS PALMEIRAS');
INSERT INTO ENDERECO(BAIRRO,CEP,CIDADE,COMPLEMENTO,NUMERO,RUA) VALUES('BAIRRO ALTO','98312-003','UBERLANDIA','CASA','3842','RUA RIACHUELO');
INSERT INTO ENDERECO(BAIRRO,CEP,CIDADE,COMPLEMENTO,NUMERO,RUA) VALUES('QUADRA 110 SUL','88135-543','UBERLANDIA','CASA','97','ALAMEDA 101');
INSERT INTO ENDERECO(BAIRRO,CEP,CIDADE,COMPLEMENTO,NUMERO,RUA) VALUES('IRACEMA','54874-093','GOIANIA','CASA','980','RUA SANTO MARINHO');
INSERT INTO ENDERECO(BAIRRO,CEP,CIDADE,COMPLEMENTO,NUMERO,RUA) VALUES('TIBERY','38461-432','UBERLANDIA','EMPRESARIAL','1000','RUA SEGISMUNDO PEREIRA');
INSERT INTO ENDERECO(BAIRRO,CEP,CIDADE,COMPLEMENTO,NUMERO,RUA) VALUES('SANTA MONICA','38461-432','UBERLANDIA','EMPRESARIAL','347','RUA JOAO NAVES DE AVILA');

/*CLIENTE*/
INSERT INTO CLIENTE(CPF,NOME,TELEFONE,ENDERECO_ID) VALUES('77824753093','ROGERIO SANTOS','(44) 67076-4726','1');
INSERT INTO CLIENTE(CPF,NOME,TELEFONE,ENDERECO_ID) VALUES('67730510022','ISABELA PRADO','(22) 53740-2188','2');
INSERT INTO CLIENTE(CPF,NOME,TELEFONE,ENDERECO_ID) VALUES('77824753093','LUCAS ALEIXO','(11) 42005-1767','3');
INSERT INTO CLIENTE(CPF,NOME,TELEFONE,ENDERECO_ID) VALUES('46785709008','GABRIEL PEREIRA','(83) 41202-8337','4');
INSERT INTO CLIENTE(CPF,NOME,TELEFONE,ENDERECO_ID) VALUES('14939223000','FLORENCA GOULART','(58) 79759-2610','5');
/*CARRO*/
INSERT INTO CARRO(MARCA,PLACA,CLIENTE_ID,MODELO_ID) VALUES('MARCA 1','XKJ-9482','1','1');
INSERT INTO CARRO(MARCA,PLACA,CLIENTE_ID,MODELO_ID) VALUES('MARCA 2','ZSU-3758','2','2');
INSERT INTO CARRO(MARCA,PLACA,CLIENTE_ID,MODELO_ID) VALUES('MARCA 3','GMT-1964','3','3');
INSERT INTO CARRO(MARCA,PLACA,CLIENTE_ID,MODELO_ID) VALUES('MARCA 4','FPQ-4821','4','4');
INSERT INTO CARRO(MARCA,PLACA,CLIENTE_ID,MODELO_ID) VALUES('MARCA 5','NIV-3456','5','5');
/*ESTACIONAMENTO*/
INSERT INTO ESTACIONAMENTO(CAIXA,ENDERECO_ID) VALUES('0','6');
INSERT INTO ESTACIONAMENTO(CAIXA,ENDERECO_ID) VALUES('0','7');
/*TICKET*/
INSERT INTO TICKET(HORARIO_ENTRADA,STATUS,VALOR,CARRO_ID,FILIAL_ID) VALUES((select now()),'ABERTO',"0",'1','1');
INSERT INTO TICKET(HORARIO_ENTRADA,STATUS,VALOR,CARRO_ID,FILIAL_ID) VALUES((select now()),'ABERTO','0','2','1');
INSERT INTO TICKET(HORARIO_ENTRADA,STATUS,VALOR,CARRO_ID,FILIAL_ID) VALUES((select now()),'ABERTO','0','1','2');
INSERT INTO TICKET(HORARIO_ENTRADA,STATUS,VALOR,CARRO_ID,FILIAL_ID) VALUES((select now()),'ABERTO','0','3','1');
INSERT INTO TICKET(HORARIO_ENTRADA,STATUS,VALOR,CARRO_ID,FILIAL_ID) VALUES((select now()),'ABERTO','0','4','1');
INSERT INTO TICKET(HORARIO_ENTRADA,STATUS,VALOR,CARRO_ID,FILIAL_ID) VALUES((select now()),'ABERTO','0','5','1');