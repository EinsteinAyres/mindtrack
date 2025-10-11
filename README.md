# 🧠 **user-service: Microsserviço de Gestão de Usuários (MINDTRACK)**

O **user-service** é o microsserviço fundamental do projeto **MINDTRACK**.
Sua responsabilidade é exclusiva: **gerenciar o ciclo de vida completo dos usuários**, incluindo **cadastro**, **autenticação (futura)**, **busca** e **manipulação de perfis**.

Este módulo é o **pilar central** da aplicação, pois **todos os outros microsserviços dependerão dele** para identificar e validar os estudantes.

---

## 🧱 **Arquitetura e Fundamentos**

O microsserviço é construído seguindo a **Arquitetura de 3 Camadas (Model-View-Controller)**, adaptada para REST, e utiliza princípios de **Domain-Driven Design (DDD)**, onde a lógica de negócio reside na camada de **Serviço**.

### 🔹 1. Camadas Principais

| **Camada**     | **Responsabilidade**                                                                                         | **Tecnologias-Chave**                                   |
| -------------- | ------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------- |
| **Controller** | Recebe requisições HTTP (*Porteiro*). Delega tarefas e retorna a resposta formatada (Status Code e DTO).     | `@RestController`, `@RequestMapping`, `ResponseEntity`  |
| **Service**    | Contém a regra de negócio (*Gerente*). Valida dados, usa o Mapper para conversão e orquestra a persistência. | `@Service`, Injeção de Dependências, `RuntimeException` |
| **Repository** | Interface de comunicação com o banco de dados (*Arquivista*). Usa métodos mágicos do Spring Data JPA.        | `JpaRepository`, `@Repository`                          |

---

### 🔹 2. Objetos de Mapeamento (DTOs e Entidade)

| **Objeto**          | **Propósito**                                                                                 | **Camada** |
| ------------------- | --------------------------------------------------------------------------------------------- | ---------- |
| **User**            | Representa a tabela no banco de dados (`@Entity`). Contém todos os campos, incluindo a senha. | `domain`   |
| **UserRequestDto**  | Recebe dados do cliente (payload de POST/PUT).                                                | `dto`      |
| **UserResponseDto** | Retorna dados ao cliente, **sem expor a senha**.                                              | `dto`      |
| **UserMapper**      | Converte entre DTO e Entidade, mantendo o `Service` limpo.                                    | `mapper`   |

---

## ⚙️ **Tecnologias Utilizadas (MVP)**

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3+
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados (Dev/Testes):** H2 Database (em memória)
* **Ferramenta:** Lombok (para getters/setters)

---

## 📌 **Endpoints da API (CRUD Completo)**

O `user-service` expõe os seguintes endpoints REST sob a URL base:

```
/api/v1/users
```

| **Operação**       | **Verbo HTTP** | **URI**                         | **Descrição**                                  |
| ------------------ | -------------- | ------------------------------- | ---------------------------------------------- |
| **Criar**          | `POST`         | `/api/v1/users`                 | Cadastra um novo usuário no sistema.           |
| **Buscar (ID)**    | `GET`          | `/api/v1/users/{id}`            | Busca um usuário pelo ID.                      |
| **Buscar (Email)** | `GET`          | `/api/v1/users/email?email=...` | Busca um usuário pelo endereço de e-mail.      |
| **Atualizar**      | `PUT`          | `/api/v1/users/{id}`            | Atualiza todos os dados de um usuário pelo ID. |
| **Deletar**        | `DELETE`       | `/api/v1/users/{id}`            | Remove um usuário do banco de dados.           |

---

## 💡 **Detalhes de Implementação**

### 🔸 Injeção de Dependências

O princípio **Inversão de Controle (IoC)** do Spring é aplicado em todas as camadas:

* `UserController` injeta `UserService` via construtor.
* `UserService` injeta `UserRepository` e `UserMapper` via construtor.

✅ Essa prática garante **baixo acoplamento** e **alto grau de testabilidade**.

---

### 🔸 JpaRepository e Buscas

O `UserRepository` demonstra o poder das **Query Methods** do Spring Data:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // O Spring gera a Query SQL automaticamente!
}
```

---

### 🔸 Tratamento de Erros e Optional

Para garantir robustez, os métodos de busca utilizam `Optional<T>` retornado pelo `JpaRepository`:

```java
User userEncontrado = userRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
```

O uso de `orElseThrow()` assegura que, se o dado não for encontrado, uma exceção seja lançada —
permitindo que o `Controller` trate o erro (no futuro retornando **HTTP 404 - Not Found**).

---

## 🚀 **Próximos Passos**

Este é um **excelente ponto de partida**.
A próxima etapa será **lidar com múltiplas requisições simultâneas** e **retornar listas de dados de forma eficiente**, evoluindo para **consultas paginadas e filtradas**.
