# E-commerce com Spring Boot

API REST para um sistema de e-commerce desenvolvida com Spring Boot. A aplicação permite o gerenciamento de usuários, autenticação via JWT, carrinhos de compras, produtos e pedidos.

---

# Índice

- [Funcionalidades](#funcionalidades)
- [Tecnologias utilizadas](#tecnologias)
- [Modelo de Dados](#modelo)
- [Pré-requisitos](#pre-requisitos)
- [Como executar](#como-executar)
- [Endpoints da API](#endpoints)

## Funcionalidades <a id="funcionalidades"></a>

- **Autenticação e autorização**: Registro e login de usuários com tokens JWT usando Spring Security.
- **Gerenciamento de produtos e categorias**: Operações CRUD (criar, ler, atualizar, excluir) para produtos e categorias no catálogo.
- **Carrinho de compras**: Adicionar, atualizar, remover itens e limpar o carrinho.
- **Gestão de pedidos**: Criar, listar, rastrear e atualizar o status de pedidos.

## Tecnologias usadas <a id="tecnologias"></a>

- Java 21
- Spring Boot
- Spring Security com JWT
- PostgreSQL 16
- Flyway para migrações de banco de dados
- Docker e Docker Compose

## Modelo de Dados <a id="modelo"></a>

O sistema utiliza as seguintes entidades principais:
- **User**: Representa os usuários da plataforma (clientes e administradores).
- **Product**: Itens disponíveis no catálogo, associados a categorias.
- **Category**: Categorias para organizar produtos.
- **Cart**: Carrinho de compras de um usuário, contendo itens.
- **CartItem**: Itens específicos no carrinho, com quantidade e produto.
- **Order**: Pedidos realizados, vinculados a um usuário e itens.
- **OrderItem**: Itens específicos de um pedido.

Diagrama Entidade-Relacionamento (ERD):

<img width="725" height="761" alt="ecommerce-diagram drawio" src="https://github.com/user-attachments/assets/2396dea6-ac1f-474b-ae16-627bed2f05f3" />

## Pré-requisitos<a id="pre-requisitos"></a>

Para executar a aplicação, você precisa de:
- Java 21 (JDK instalado e configurado).
- Maven (para build local).
- PostgreSQL 16 (instalado localmente ou via Docker).
- Docker e Docker Compose (opcional, para execução em contêineres).
- Ferramenta para testar APIs (ex.: Postman ou Insomnia).

## Como executar <a id="como-executar"></a>

### Opção 1: Usando Docker Compose
1. Clone o repositório
```bash
git clone https://github.com/joseajr17/ecommerce-api.git
cd ecommerce-api
```

2. Crie o arquivo .env baseado no .env.example

3. Suba a aplicação com Docker Compose
```bash
docker compose up --build
```
A API estará disponível em: http://localhost:8080

### Opção 2: Executando Localmente (sem Docker)
1. Certifique-se de que o PostgreSQL está rodando localmente e crie o banco de dados
2. Configure as credenciais no arquivo src/main/resources/application.properties:
3. Execute a aplicação no IntelliJ:
  - Abra o projeto no IntelliJ IDEA.
  - Configure o JDK 21 em File > Project Structure > SDKs.
  - Execute a classe principal (ex.: ApiApplication).


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
| PUT  | `/api/admin/products` | Atualizar um produto (admin)             |
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
