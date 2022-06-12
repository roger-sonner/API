# 1-API-Rest-Spring

## O application.properties esta pré configurado para MySQL 
## Criar o Bando de dados manualmente (CREATE DATABASE primeiraapi;)



## Instruções
### CLIENTES
#### Para Get de todos os clientes usar a URl de modelo abaixo:
http://localhost:8080/clientes
**************************************************************************************************
#### Para Get de determinado o cliente usar a URl de modelo abaixo:
http://localhost:8080/clientes/1
**************************************************************************************************
#### Para Post de Clientes usar o modelo de JSON abaixo
{
    "codigo":"3",
    "nome":"Cliente Inserido via Postman 3"
}
http://localhost:8080/cliente
**************************************************************************************************
#### Para Put de Clientes usar o modelo de JSON abaixo
{
    "nome":"Cliente Atualizado via Postman 3"
}
Obs: Incluir na URL o ID do cliente a ser alterado - Exemplo: URL: http://localhost:8080/clientes/7
**************************************************************************************************
#### Para Delete de clientes usar a URl de modelo abaixo:
http://localhost:8080/clientes/7
Obs: Incluir na URL o ID do cliente a ser deletado - Exemplo: URL: http://localhost:8080/cliente/7
**************************************************************************************************
### PRODUTOS
#### Para Get de todos os produtos usar a URl de modelo abaixo:
URL: http://localhost:8080/produtos
**************************************************************************************************
#### Para Get de determinado produto usar a URl de modelo abaixo:
URL: http://localhost:8080/produtos/1
**************************************************************************************************
#### Para Post de Produto usar o modelo de JSON abaixo
{
   "codigo": 123456,
    "descricao":"Produto Inserido via Postman",
    "valorUnitario": 9.00
}
URL: http://localhost:8080/produtos
**************************************************************************************************
#### Para Put de Produto usar o modelo de JSON abaixo
{
    "descricao":"Produto Alterado via Postman",
    "valorUnitario": 99.00
}

Obs: Incluir na URL o ID do produto a ser alterado - Exemplo: URL: http://localhost:8080/produtos/7
**************************************************************************************************
#### Para Delete de produtos usar a URl de modelo abaixo:
http://localhost:8080/produtos/7
Obs: Incluir na URL o ID do produto a ser deletado - Exemplo: URL: http://localhost:8080/produtos/7
O produto não será deletado caso existe referência na tabela de Itens de Notas Fiscais
**************************************************************************************************
### NOTAS FISCAIS
#### Para Get de todas as notas fiscais usar a URl de modelo abaixo:
URL: http://localhost:8080/notasFiscais
**************************************************************************************************
#### Para Get de uma determinada nota fiscal usar a URl de modelo abaixo:
URL: http://localhost:8080/notasFiscais/1
**************************************************************************************************
#### Para Post de Nota Fiscal usar o modelo de JSON abaixo
{
    "data":"2022-06-09",
    "numero":123456,
    "clienteId":1
}
URL: http://localhost:8080/notasFiscais
**************************************************************************************************
#### Para Put de Nota Fiscal usar o modelo de JSON abaixo
{
    "data":"2022-01-01",
    "clienteId":3
}

Obs: Incluir na URL o ID da Nota Fiscal a ser alterada - Exemplo: URL: http://localhost:8080/notasFiscais/1
**************************************************************************************************
#### Para Delete de Nota Fiscal usar a URl de modelo abaixo:

http://localhost:8080/notasFiscais/7

Obs: 
1) Incluir na URL o ID da Nota Fiscal a ser deletada - Exemplo: URL: http://localhost:8080/notasFiscais/6
2) Os produtos constantes na tabela de itens de notas fiscais, também serão deletados
**************************************************************************************************
### ITENS DE NOTAS FISCAIS
#### Para Post de Itens de Notas Fiscais usar o modelo de JSON abaixo 
{
    "produtoId": 8,
    "notaFiscalId": 1,
    "quantidade": 5.0,
    "sequencial": 4
}
URL: http://localhost:8080/itensNotaFiscal

Lógica implementada:
 
1) O produto deve estar cadastrado e a nota fiscal também.
2) O campo valor total é calculado com base na quantidade informada vezes o valor unitário do 
produto constante na tabela de produtos.
3) Caso a quantidade informada seja <= 0 ou null, será atribuida a quantidade = 1.
**************************************************************************************************
#### Para Put de Itens de Notas Fiscais usar o modelo de JSON abaixo
{
    "itemNotaFiscalId": 1,
    "quantidade": 3000,
    "sequencial": 1,
    "produtoId": 1   
}
URL: http://localhost:8080/itensNotaFiscal

Lógica implementada:

1) Não é permito alterar a nota fiscal de origem do item
2) Caso não seja informado o produto ou este não esteja cadastrado, será mantido o produto original
3) Caso não seja informado o sequencial ou este seja <= 0 , será mantido o sequencial original
4) Caso não seja informada a quantidade ou esta seja <= 0 , será mantido o quantidade original
5) O campo valor total é calculado com base na quantidade informada vezes o valor unitário do 
produto constante na tabela de produtos.

**************************************************************************************************
#### Para Delete de Itens de Notas Fiscais usar a URl de modelo abaixo:

http://localhost:8080/itensNotaFiscal/6

Obs: 
1) Incluir na URL o ID da Nota Fiscal a ser deletada - Exemplo: URL: http://localhost:8080/itensNotaFiscal/6
**************************************************************************************************

