# Desafio de Programação - Itaú Unibanco

Este projeto é a solução para o **Desafio de Programação do Itaú Unibanco**, que consiste em desenvolver uma API REST para gerenciar transações e calcular estatísticas em tempo real. A aplicação foi desenvolvida em **Java** utilizando **Spring Boot**, seguindo todas as restrições técnicas e implementando todos os endpoints exigidos, além de todos os desafios extras propostos.

## Sobre o Projeto

A API REST permite o recebimento de transações, o cálculo de estatísticas das transações realizadas nos últimos 60 segundos (ou período configurável) e a limpeza dos dados armazenados em memória. O projeto foi estruturado em camadas (Controller, Service, Model, Exception) para garantir organização, manutenibilidade e escalabilidade. Além disso, foram implementados testes automatizados, containerização com Docker, documentação detalhada e outras funcionalidades extras.

### Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (com as seguintes dependências):
  - `spring-boot-starter-web`
  - `spring-boot-starter-actuator` (para observabilidade)
  - `spring-boot-starter-validation` (para validação de entrada)
  - `spring-boot-devtools` (para desenvolvimento)
  - `spring-boot-starter-test` (para testes automatizados)
  - `springdoc-openapi-starter-webmvc-ui` (para documentação da API com OpenAPI/Swagger)
  - `lombok` (para redução de boilerplate)
- **Docker** (para containerização)
- **Maven** (para gerenciamento de dependências)

### Estrutura do Projeto

O projeto segue uma arquitetura em camadas, com as seguintes responsabilidades:

- **Controller**: Gerencia as requisições HTTP e delega as operações às camadas de serviço.
- **Service**: Contém a lógica de negócio, incluindo validação e cálculo de estatísticas.
- **Model**: Define as entidades (Transação e Estatística) com validações, eu usei DTOs.
- **Exception**: Gerencia erros personalizados com respostas claras para o cliente.

Os dados são armazenados em memória, conforme exigido, utilizando estruturas thread-safe para garantir consistência em ambientes concorrentes.

## Endpoints da API

A API implementa os três endpoints obrigatórios conforme especificado no desafio:

1. **POST /transacao**
   - Recebe uma transação no formato JSON:
     ```json
     {
       "valor": 123.45,
       "dataHora": "2020-08-07T12:34:56.789-03:00"
     }
     ```
   - Validações:
     - Campos `valor` e `dataHora` obrigatórios.
     - Valor deve ser maior ou igual a zero.
     - Data/hora não pode ser futura.
   - Respostas:
     - `201 Created`: Transação válida e registrada.
     - `422 Unprocessable Entity`: Transação inválida.
     - `400 Bad Request`: JSON malformado.

2. **DELETE /transacao**
   - Limpa todas as transações armazenadas em memória.
   - Resposta:
     - `200 OK`: Dados apagados com sucesso.

3. **GET /estatistica**
   - Retorna estatísticas das transações dos últimos 60 segundos (ou período configurável):
     ```json
     {
       "count": 10,
       "sum": 1234.56,
       "avg": 123.456,
       "min": 12.34,
       "max": 123.56
     }
     ```
   - Resposta:
     - `200 OK`: Estatísticas calculadas (valores zero se não houver transações).

## Funcionalidades Extras Implementadas

Todas as 12 funcionalidades extras propostas foram implementadas:

1. **Testes Automatizados**: Testes unitários e funcionais para as camadas Controller e Service, cobrindo cenários felizes e de erro, utilizando `spring-boot-starter-test` e JUnit.
2. **Containerização**: Dockerfile configurado para empacotar a aplicação em um container leve, baseado em uma imagem OpenJDK.
3. **Logs**: Implementação de logs detalhados com SLF4J e Logback, incluindo informações sobre requisições, validações e erros.
4. **Observabilidade**: Endpoint `/actuator/health` configurado para verificação da saúde da aplicação.
5. **Performance**: Medição do tempo de cálculo das estatísticas, com logs informando a duração de cada operação.
6. **Tratamento de Erros**: Exceções personalizadas com mensagens claras retornadas ao cliente em caso de erros (por exemplo, validações falhas).
7. **Documentação da API**: Swagger/OpenAPI disponível em `/swagger-ui.html` para exploração interativa dos endpoints.
8. **Documentação do Sistema**: Este README detalha como construir, executar e testar a aplicação.
9. **Configurações**: Período de estatísticas configurável via propriedade `app.stats.window-seconds` (padrão: 60 segundos).

## Como Executar a Aplicação

### Pré-requisitos

- **Java 17** ou superior
- **Maven 3.8+**
- **Docker** (opcional, para containerização)

### Passos para Execução Local

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. Compile e execute a aplicação com Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Acesse a API em `http://localhost:8080`. Endpoints disponíveis:
   - POST: `http://localhost:8080/transacao`
   - DELETE: `http://localhost:8080/transacao`
   - GET: `http://localhost:8080/estatistica`
   - Swagger: `http://localhost:8080/swagger-ui.html`
   - Healthcheck: `http://localhost:8080/actuator/health`

### Execução com Docker

1. Construa a imagem Docker:
   ```bash
   docker build -t desafio-itau .
   ```

2. Execute o container:
   ```bash
   docker run -p 8080:8080 desafio-itau
   ```

## Testes Automatizados

Para executar os testes unitários e funcionais:

```bash
mvn test
```

Os testes cobrem:
- Validações de entrada no endpoint POST /transacao.
- Cálculo correto das estatísticas no endpoint GET /estatistica.
- Limpeza de dados no endpoint DELETE /transacao.
- Cenários de erro (JSON inválido, transações futuras, etc.).

## Contribuições

Este projeto foi desenvolvido como parte de um desafio técnico e não está aberto para contribuições externas no momento. No entanto, sinta-se à vontade para forká-lo e adaptá-lo para seus próprios estudos!

## Licença

Este projeto é fornecido sem uma licença específica, conforme exigido pelo desafio (disponibilizado publicamente apenas para avaliação). Após a avaliação, o repositório poderá ser tornado privado.