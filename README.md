### README

# Projeto: Sistema de Transações Bancárias com Controle de Concorrência

## Visão Geral

Este projeto implementa um sistema de transações bancárias usando o framework Spring Boot. O objetivo principal é resolver o problema de seção crítica ao realizar operações em contas bancárias, utilizando uma técnica de mutex para garantir que as operações concorrentes sejam seguras e consistentes.

## Estrutura do Projeto

O projeto segue a estrutura padrão de um aplicativo Spring Boot, com as seguintes pastas principais:

- `model`: Contém as classes de modelo, como `Conta` e `Transacao`.
- `repository`: Contém as interfaces de repositório para acesso ao banco de dados.
- `service`: Contém a lógica de negócios, incluindo o serviço de conta e transação.
- `controller`: Contém os controladores REST para expor os endpoints da API.

## Arquivo `ContaService.java`

### Descrição

A classe `ContaService` é responsável por gerenciar as operações em contas bancárias, incluindo depósitos, saques e criação de transações. Esta classe utiliza um `HashMap` estático para armazenar locks específicos para cada transação, garantindo que as operações concorrentes sejam executadas de forma segura.

### Métodos Principais

#### `criarTransacao`

Este método cria uma nova transação em uma conta bancária específica. Ele garante que a operação de criação de transação seja executada de forma segura, sincronizando o acesso à conta específica.

```java
@Transactional
public ContaDtoOut criarTransacao(TransacaoDtoIn dto, Long idConta) {
    DadosTransacaoDTO dadosTransacao = new DadosTransacaoDTO(dto.getTipoTransacao(), idConta);
    synchronized (getLock(dadosTransacao)){
        Transacao transacao = genericMapper.toObject(dto, Transacao.class);
        Conta conta = contaRepository.findById(idConta).orElseThrow();
        transacao.setConta(conta);
        transacao.setDataTransacao(LocalDateTime.now());
        transacaoRepository.save(transacao);
        return genericMapper.toObject(conta, ContaDtoOut.class);
    }
}
```

#### `getLock`

Este método retorna um objeto lock para uma determinada transação. Ele utiliza o `computeIfAbsent` para criar um novo lock se não existir um lock para a transação especificada.

```java
private static synchronized Object getLock(DadosTransacaoDTO dadosTransacao) {
    return lock.computeIfAbsent(dadosTransacao, v -> new Object());
}
```

## Como Funciona

1. **Controle de Concorrência:**
   - O método `getLock` garante que cada transação tenha um objeto lock exclusivo, utilizando um `HashMap` estático.
   - Ao sincronizar no objeto lock retornado por `getLock`, garantimos que apenas uma thread possa executar a seção crítica para uma transação específica.

2. **Transações Seguras:**
   - O método `criarTransacao` é anotado com `@Transactional`, garantindo que a operação seja atômica e consistente.
   - Utilizando `synchronized` no lock específico da transação, evitamos condições de corrida e garantimos a integridade dos dados.

## Como Executar

### Pré-requisitos

- Java 8 ou superior
- Maven ou Gradle
- Spring Boot

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-transacoes-bancarias.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd sistema-transacoes-bancarias
   ```
3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
   ou
   ```bash
   ./gradlew bootRun
   ```

### Endpoints

- `GET /conta/extrato/{id}`: Consultar Extrato de uma conta.
- `POST /conta`: Criar uma conta.
- `POST /conta/transacao/{idConta}`: Cria uma nova transação em uma conta específica.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias e correções.

