# 🧠 **MINDTRACK: Sistema Inteligente de Gestão e Otimização de Estudos**

O **MINDTRACK** é um sistema modular e inteligente de **produtividade e gestão de estudos**.
Baseado em **neurociência** e **princípios de foco**, utiliza **arquitetura de microsserviços** em **Java/Spring Boot** para oferecer ferramentas avançadas como:

* 🕒 **Pomodoro Personalizável**
* 🧩 **Análise Cognitiva**
* 🔁 **Integração com Repetição Espaçada (SRS)**

---

## 📈 **Status Atual**

**Fase 1: MVP — Fundamentos de Microsserviços e Backend**

> ✅ Conclusão do **user-service**.

---

## 🎯 **Propósito e Visão**

O **objetivo principal** do MINDTRACK é **combater a procrastinação e a sobrecarga de informação**, oferecendo:

* Micro-objetivos claros
* Rastreamento de foco em tempo real
* Ferramentas de otimização de aprendizado (IA e perfis cognitivos)

É um projeto que **une produtividade real** com **desenvolvimento full-stack profissional**.

---

## 🧱 **Arquitetura de Microsserviços**

O MINDTRACK é construído como um **conjunto de microsserviços independentes** que se comunicam via **APIs REST** e, futuramente, por **Mensageria (Kafka/RabbitMQ)**.

Essa abordagem garante:

* **Escalabilidade**
* **Resiliência**
* **Atualizações independentes**

---

### ⚙️ **Microsserviços Essenciais (Fase 1 e Futuro)**

| **Microsserviço**     | **Responsabilidade Principal**                            | **Status**               |
| --------------------- | --------------------------------------------------------- | ------------------------ |
| **user-service**      | Gerenciamento de Usuários, Perfis e Autenticação (JWT).   | ✅ MVP Concluído          |
| **study-service**     | CRUD de Matérias, Tarefas, Metas e Planejamento.          | 🔜 Próximo a Implementar |
| **pomodoro-service**  | API para controle de sessões, histórico de foco e pausas. | ⏳ Futuro                 |
| **stats-service**     | Dashboard, métricas, relatórios e estatísticas.           | ⏳ Futuro                 |
| **gateway-service**   | API Gateway, Roteamento e Segurança centralizada.         | ⏳ Futuro                 |
| **config-service**    | Centralização das configurações (Config Server).          | ⏳ Futuro                 |
| **discovery-service** | Registro e Descoberta de Serviços (Eureka).               | ⏳ Futuro                 |

---

## 🚀 **Plano de Evolução do Projeto (Fases)**

O projeto é dividido em **quatro fases** de aprendizado e desenvolvimento, com foco em **micro-objetivos alcançáveis**.

| **Fase**                      | **Objetivo Principal**                                                                 | **Aprendizados-Chave**                                                 |
| ----------------------------- | -------------------------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| **1: MVP e Fundamentos**      | Consolidar Java Spring, Persistência e CRUDs básicos.                                  | Spring Boot, JPA, H2/PostgreSQL, DTOs, Injeção de Dependências.        |
| **2: Produtividade Avançada** | Notas Enriquecidas, Pomodoro Personalizável, Gamificação e Upload de Arquivos.         | WebFlux (Opcional), Uploads, Cache.                                    |
| **3: Inteligência e IA**      | Sistema de Repetição Espaçada (SRS), Análise Comportamental e IA (Resumo/Transcrição). | Kafka/RabbitMQ, Integração com APIs Externas, Microsserviços Reativos. |
| **4: Front-End e Premium**    | Interface Angular/React e Recursos Premium (Colaboração, Billing).                     | Angular/React, Consumo de APIs, WebSockets.                            |

---

## 🛠️ **Tecnologias Core**

| **Categoria** | **Stack**                                             | **Detalhes**                              |
| ------------- | ----------------------------------------------------- | ----------------------------------------- |
| **Backend**   | Java 17+, Spring Boot 3+, Spring Data JPA, Lombok     | Core do sistema e lógica de negócio.      |
| **Infra**     | PostgreSQL (Prod), H2 Database (Dev), Docker (Futuro) | Gerenciamento de dados e conteinerização. |
| **APIs**      | RESTful Services, OpenFeign                           | Comunicação entre microsserviços.         |
| **Frontend**  | Angular (ou React)                                    | Interface do usuário (Fase 4).            |

---

## 💻 **Configuração e Execução Local (MVP)**

### **Pré-requisitos**

* ☕ Java Development Kit (**JDK 17+**)
* 🧩 **Maven** (Gerenciador de dependências)
* 💻 IDE de sua preferência (**IntelliJ IDEA**, **VS Code**, etc.)
* 🧪 **Postman** ou **Thunder Client** para testar APIs

---

### **1️⃣ Clonar e Compilar**

```bash
# Clone o repositório principal
git clone <URL_DO_REPOSITORIO>
cd mindtrack

# Navegue para o primeiro microsserviço
cd user-service

# Compile o projeto (se necessário)
mvn clean install
```

---

### **2️⃣ Executar o user-service**

Execute a aplicação pelo método `main` da classe `UserServiceApplication.java`
ou via terminal:

```bash
cd user-service
java -jar target/<nome-do-arquivo>.jar
```

A API estará disponível em:

```
http://localhost:8080
```

*(Configuração padrão do Spring Boot)*

---

### **3️⃣ Teste o CRUD**

Use o **Postman** para testar o fluxo completo do CRUD no `user-service`:

| **Ação**              | **Método HTTP** | **Endpoint**         | **Retorno Esperado** |
| --------------------- | --------------- | -------------------- | -------------------- |
| **Criar Usuário**     | `POST`          | `/api/v1/users`      | `201 Created`        |
| **Buscar Usuário**    | `GET`           | `/api/v1/users/{id}` | `200 OK`             |
| **Atualizar Usuário** | `PUT`           | `/api/v1/users/{id}` | `204 No Content`     |
| **Deletar Usuário**   | `DELETE`        | `/api/v1/users/{id}` | `204 No Content`     |

---

### 🧩 **Resultado**

Após o sucesso do `user-service`, os próximos módulos (como `study-service` e `pomodoro-service`) expandirão o ecossistema MINDTRACK, criando uma **plataforma integrada de estudo e foco**, evoluindo de **MVP Backend** para **sistema completo full-stack**.

