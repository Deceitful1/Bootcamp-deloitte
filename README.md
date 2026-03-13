# Sistema de Gerenciamento de Clientes

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-4.0.3-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![H2 Database](https://img.shields.io/badge/H2-Database-blue)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

---

# 📌 Sobre o Projeto

O **Sistema de Gerenciamento de Clientes** é uma **API REST desenvolvida com Java e Spring Boot** responsável por realizar o gerenciamento completo de clientes através de operações **CRUD (Create, Read, Update, Delete)**.

Este projeto foi desenvolvido com o objetivo de **demonstrar minhas habilidades de desenvolvimento backend e boas práticas de arquitetura** como parte de um **bootcamp técnico da Deloitte**.

A aplicação também implementa recursos modernos de APIs REST, como:

- Navegação de recursos com **HATEOAS**
- **Validação de dados**
- **Tratamento de exceções customizadas**
- **Suporte a múltiplos formatos de resposta (JSON, XML, YAML)**

---

# 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.3**
- **Spring Web MVC**
- **Spring Data JPA**
- **Spring HATEOAS**
- **Spring DevTools**
- **Jackson Dataformat XML**
- **Jackson Dataformat YAML**
- **H2 Database**
- **JUnit**
- **Mockito**
- **Maven**

---

# ⚙️ Funcionalidades

O sistema possui as seguintes funcionalidades:

## 📋 Gerenciamento de Clientes

- Criar cliente
- Buscar todos os clientes
- Buscar cliente por ID
- Atualizar cliente
- Deletar cliente

---

## 🔎 Validação de Dados

O sistema realiza validações importantes como:

- Formato de **CPF**
- Formato de **Email**
- Campos obrigatórios

---

## ⚠️ Tratamento de Exceções

O projeto possui **tratamento centralizado de erros**, retornando mensagens claras para o consumidor da API.

Exemplo:

<img width="554" height="154" alt="image" src="https://github.com/user-attachments/assets/37ce573c-58ec-4002-b451-7a59a495732a" />



---

# 📡 Endpoints da API

**Base URL:** `http://localhost:8080/clientes`

| Ação | Método | Endpoint |
| :--- | :--- | :--- |
| Buscar todos os clientes | `GET` | `/clientes` |
| Buscar cliente por ID | `GET` | `/clientes/{id}` |
| Criar cliente | `POST` | `/clientes` |
| Atualizar cliente | `PUT` | `/clientes` |
| Deletar cliente | `DELETE` | `/clientes/{id}` |

---

## 🧪 Testes

O projeto possui dois tipos de testes:

### Testes Unitários
Utilizando:
* **JUnit**
* **Mockito**

> Esses testes garantem que os componentes funcionem corretamente de forma isolada.

### Testes de Integração
Os testes de integração utilizam o **H2 Database**, permitindo simular o comportamento real da aplicação com banco de dados.

---

## 🗂 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas, comum em aplicações Spring Boot:

`Controller` → `Service` → `Repository` → `Database`

### Camadas
* **Controller**: Responsável por expor os endpoints da API.
* **Service**: Contém as regras de negócio da aplicação.
* **Repository**: Responsável pelo acesso aos dados utilizando Spring Data JPA.
* **Entity**: Representação das entidades do banco de dados.

---

## 📁 Estrutura do Projeto

```text
gablins
 ┣ 📂 config           # Configurações do projeto (Spring)
 ┣ 📂 controllers      # Endpoints REST da API
 ┣ 📂 demo             # Classes de demonstração ou testes iniciais
 ┣ 📂 DTOs             # Data Transfer Objects e VOs
 ┣ 📂 entities         # Entidades do Banco de Dados
 ┃ ┗ 📂 validator      # Validadores específicos de entidade
 ┣ 📂 exceptions       # Tratamento de erros personalizado
 ┣ 📂 repositories     # Interfaces de acesso ao banco (JPA)
 ┣ 📂 services         # Lógica de negócio principal
 ┃ ┣ 📂 hateoas        # Implementação de links dinâmicos
 ┃ ┗ 📂 validator      # Validadores de regras de negócio
 ┗ 📜 SistemaDeCadastroApplication.java  # Classe principal (Spring Boot)

```
---
## 💾 Banco de Dados

Para facilitar o desenvolvimento e testes, o projeto utiliza **H2 Database** em memória.

---

## ▶️ Como Executar o Projeto

### Pré-requisitos
* Java 21
* Maven
* IDE (IntelliJ, Eclipse ou VSCode)

### 1. Clone o repositório
```bash
git clone [https://github.com/Deceitful1/Bootcamp-deloitte](https://github.com/Deceitful1/Bootcamp-deloitte)

```
## 2. Entre na pasta do projeto

```bash
cd Bootcamp-deloitte

### 3. Execute o projeto

```bash
./mvnw spring-boot:run
```
Ou simplesmente Execute o código pela sua IDE.
---


## 👨‍💻 Autor

**Gabriel Lins** 
* [GitHub](https://github.com/Deceitful1)  
* [LinkedIn](https://www.linkedin.com/in/gabriel-lins-03a043288/)

---

## 🎯 Objetivo do Projeto

O projeto foi desenvolvido como parte de um bootcamp técnico da **Deloitte**, com o objetivo de demonstrar:

* **Boas práticas** de desenvolvimento
* **Organização** de código
* **Testes** automatizados
* **Construção** de APIs REST robustas



