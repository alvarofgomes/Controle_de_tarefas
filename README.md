# 📋 Projeto Controle de Tarefas - API REST

API RESTful para gerenciamento de tarefas desenvolvida com **Spring Boot**, permitindo o CRUD completo de tarefas com associação a usuários.

---

## 🚀 Funcionalidades Principais

### ✨ Gerenciamento de Tarefas
- Criação de novas tarefas com descrição, data de entrega e status
- Listagem completa de tarefas ou filtrada por:
  - Usuário específico
  - Status (concluídas/pendentes)
- Atualização de todos os campos da tarefa
- Exclusão de tarefas

### 👥 Gerenciamento de Usuários
- Cadastro de novos usuários
- Associação de tarefas a usuários
- Consulta de tarefas por usuário

---

## 📚 Documentação

- API documentada com **Swagger/OpenAPI**
- Modelos de requisição e resposta com validações

---

## 🛠️ Tecnologias Utilizadas

### 🔙 Backend
- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- Spring Validation
- Lombok

### 🗄️ Banco de Dados
- MySQL 8.0

### 📝 Documentação
- SpringDoc OpenAPI 2.7.0

### 🧰 Ferramentas
- Maven
- Git

---

## 👨‍💻 Equipe de Desenvolvimento

| Nome             | Matrícula | Função Principal     |
|------------------|-----------|----------------------|
| Alisson Santos   | 01633244  | Banco de Dados       |
| Alvaro Gomes     | 01704806  | Backend/API          |
| Eduardo Melo     | 01706118  | Documentação         |
| Kauan Alves      | 01710611  | Validações           |
| Ítalo Guilherme  | 01678558  | Coordenação          |
| Wesley José      | 01709983  | Testes               |

---

## 📌 Endpoints Principais

### 📝 Tarefas

- `POST /tarefas` – Cria nova tarefa  
- `GET /tarefas` – Lista tarefas (com filtro opcional por status)  
- `GET /tarefas/{id}` – Busca tarefa por ID  
- `PUT /tarefas/{id}` – Atualiza tarefa existente  
- `DELETE /tarefas/{id}` – Remove tarefa  

### 👤 Usuários

- `POST /usuarios` – Cria novo usuário  
- `GET /usuarios/{id}` – Busca usuário por ID  
- `GET /usuarios?email={email}` – Busca usuário por email  

---

## 🚧 Pré-requisitos

- Java 21 JDK instalado  
- MySQL 8.0+  
- Maven 3.8+  
- IDE de sua preferência (IntelliJ, Eclipse, VS Code etc.)
