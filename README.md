# API de E-commerce
Uma API RESTful para uma plataforma de comércio eletrônico, desenvolvida em Java com Spring Boot para gerenciar produtos, com autenticação de usuários e processamento de pedidos.

# Índice

- [Visão geral](#visao-geral)
- [Funcionalidades](#funcionalidades)
- [Tecnologias utilizadas](#tecnologias)
- [Modelo de Dados](#modelo)
- [Instalação](#instalacao)
- [Configuração](#config)
- [Endpoints da API](#endpoints)

## Visão geral <a id="visao-geral"></a>

Este projeto é uma API backend para uma aplicação de e-commerce. Ele fornece endpoints para gerenciar produtos, para fazer a autenticação de usuários, para gerenciar os carrinhos de compras e para controlar os pedidos e seus pagamentos. 
A API é projetada para ser escalável e segura, utilizando Spring Boot para facilitar o desenvolvimento e a manutenção.

## Funcionalidades <a id="funcionalidades"></a>

<ul>
  <li>Autenticação e autorização de usuários, utilizando o JWT com Spring Security</li>
  <li>Gerenciamento do catálogo de produtos (operações CRUD)</li>
  <li>Funcionalidades de carrinho de compras</li>
  <li>Criação e rastreamento de pedidos</li>
  <li>Design de API RESTful com respostas em JSON</li>
</ul>

## Tecnologias usadas <a id="tecnologias"></a>

<ul>
  <li>Java 21</li>
  <li>Spring Boot (com Spring Web, Spring Data JPA, Spring Security)</li>
  <li>PostgreSQL</li>
  <li>Flyway 9.8.1 (para migrações de banco de dados)</li>
  <li>Lombok (para redução de código boilerplate)</li>
  <li>Java-JWT 4.5.0 (para autenticação baseada em JWT)</li>
  <li>Maven (gerenciamento de dependências)</li>
</ul>

## Modelo de Dados <a id="modelo"></a>
Modelo de dados usando o diagrama entidade-relacionamento (ERD) com as principais entidades e seus relacionamentos:

<img width="525" height="561" alt="ecommerce-diagram drawio" src="https://github.com/user-attachments/assets/2396dea6-ac1f-474b-ae16-627bed2f05f3" />

## Instalação <a id="instalacao"></a>


<ol>
  <li>Clone o repositório:
    
  ```bash
      git clone https://github.com/joseajr17/ecommerce-api.git
      cd ecommerce-api
  ```
  </li>
  <li>Instale as dependências:
    
  ```bash
      mvn install
  ```
  </li>
  <li>Configure as variáveis de ambiente (veja Configuração).</li>
  <li>Inicie a aplicação:
    
  ```bash
      mvn spring-boot:run
  ```
  </li>
</ol>

## Configuração <a id="config"></a>
Crie um arquivo application.properties ou application.yml no diretório src/main/resources e adicione as seguintes configurações. 
```
# Configuração para PostgreSQL (produção)
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommercedb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configuração do JWT
jwt.secret=sua_chave_secreta_jwt
```
**Nota**: Certifique-se de que o PostgreSQL esteja em execução e que o banco de dados ecommercedb tenha sido criado antes de iniciar a aplicação.

## Endpoints da API <a id="endpoints"></a>

### Autenticação

| Método | Endpoint           | Descrição                               |
|--------|--------------------|-----------------------------------------|
| POST   | `/api/auth/register` | Registrar um novo usuário               |
| POST   | `/api/auth/login`    | Autenticar um usuário e obter token JWT |

### Categorias (Admin)

| Método | Endpoint           | Descrição                               |
|--------|--------------------|-----------------------------------------|
| POST e GET  | `/api/admin/category` | Gerenciar categorias              |

### Produtos

| Método | Endpoint           | Descrição                               |
|--------|--------------------|-----------------------------------------|
| GET  | `/api/products` | Listar todos os produtos                      |
| GET  | `/api/products/{productId}` | Obter detalhes de um produto específico |
| GET  | `/api/products/filter` | Filtrar produtos (ex.: por categoria, preço, etc.) |
| POST  | `/api/admin/products` | Criar um novo produto (admin)              |
| PUT  | `/api/admin/category` | Atualizar um produto (admin)             |
| DELETE  | `/api/admin/products/{productId}` | Excluir um produto (admin)             |

### Carrinho

| Método  | Endpoint                         | Descrição                        |
|---------|----------------------------------|----------------------------------|
| POST    | `/api/cart/add`                 | Adicionar item ao carrinho       |
| GET     | `/api/cart/get`                 | Obter itens do carrinho          |
| PUT     | `/api/cart/items/{cartItemId}`  | Atualizar item do carrinho       |
| DELETE  | `/api/cart/items/{cartItemId}`  | Remover item do carrinho         |
| DELETE  | `/api/cart`                     | Limpar o carrinho                |


### Pedidos

| Método  | Endpoint                                | Descrição                                      |
|---------|------------------------------------------|------------------------------------------------|
| POST    | `/api/orders/checkout`                  | Finalizar compra e criar pedido                |
| GET     | `/api/orders`                           | Listar pedidos do usuário                      |
| GET     | `/api/orders/{orderId}`                 | Obter detalhes de um pedido específico         |
| GET     | `/api/admin/orders`                     | Listar todos os pedidos (admin)                |
| PUT     | `/api/admin/orders/{orderId}`           | Atualizar status de um pedido (admin)                    |
| GET     | `/api/payment/{orderId}`                | Obter informações de pagamento de um pedido    |
