# sales_analysis
Este projeto tem por objetivo processar lotes de arquivos sobre dados de vendas disponibilizados em um diretório padrão e, produzir relatórios sobre a analise dos dados para cada arquivo lido.

## O relatório produzido possui as seguintes informações
* Quantidade de clientes no arquivo de entrada
* Quantidade de vendedores no arquivo de entrada
* ID da venda mais cara
* O pior vendedor

## Diretórios padrões dos arquivos
Entrada: HOMEPATH/data/in

Saída: HOMEPATH/data/out

## Gerenciador de dependencias e ferramenta de build
* Gradle 6.4.1

## Versão do Java
* Necessário JDK 11 instalado.

## Bibliotecas externas utilizadas
* org.projectlombok:lombok:1.18.10 - Biblioteca Java utilizada para redução de código fonte nos projetos(https://projectlombok.org/)

## Solução adotada para o projeto
Ao iniciar a execução do sistema, caso as pastas HOMEPATH/data/in e HOMEPATH/data/out não existam, as mesmas serão criadas.

Para o monitoramente do diretório foi utilizada a interface públic Watch Service do pacote java.nio.file, 
que funciona como um monitor de eventos em um diretório específico. O Watch Service (https://docs.oracle.com/javase/7/docs/api/java/nio/file/WatchService.html) 
permite que sejam sinalizados os tipos de eventos que devem ser monitorados e, para esta solução, usei o seguinte:
* StandardWatchEventKinds.ENTRY_CREATE - Gera um evento a cada criação de um novo arquivo no diretório monitorado.

Desta forma, a cada inclusão de um arquivo no diretório de entrada padrão monitorado, é disparado o processamento do arquivo de entrada 
e geração do relatório de saída.

## Tratamento de exceção
* Caso haja linhas em branco no arquivo de entrada, as mesmas serão despresadas.
* Caso não haja Vendedores, Clientes ou Vendas no arquivo de entrada, o arquivo de saída será gerado com valores zerados.

## Exemplo de arquivo de entrada
### Dados do vendedor
Os dados do vendedor possuem o identificador 001 e seguem o seguinte formato:

001çCPFçNameçSalary

### Dados do cliente
Os dados do cliente possuem o identificador 002 e seguem o seguinte formato:

002çCNPJçNameçBusiness Area

### Dados de venda
Os dados de venda possuem o identificador 003 e seguem o seguinte formato:

003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

### Exemplo de conteúdo total do arquivo:
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```

## Exemplo de arquivo de saida:
```
Total of Customers: 2
Total of Sellers: 2
Greatest Sale Id: 10
Worst Salesman Name: Paulo
```

## Comando para execução
```
.\gradlew bootRun
```
