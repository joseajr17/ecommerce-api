# E-commerce com Spring Boot

API REST para um sistema de e-commerce desenvolvida com Spring Boot. A aplicação permite o gerenciamento de usuários, autenticação via JWT, carrinhos de compras, produtos e pedidos.

## 🚀 Funcionalidades 

- ✅ **Autenticação e autorização**: Registro e login de usuários com tokens JWT usando Spring Security.
- ✅ **Painel administrativo**: Endpoints para gerenciar produtos, categorias e pedidos
- ✅ **Carrinho de compras**: Endpoints para adicionar, atualizar, remover itens e limpar o carrinho. Além do endpoint para fazer checkout do carrinho

## 🛠️ Tecnologias usadas 

- **Java 21** - Linguagem principal
- **Spring Boot** - Framework base
- **Spring Data JPA** - Persistência
- **Spring Security** - Segurança
- **Lombok** - Redução de boilerplate
- **PostgreSQL 16** - Banco de dados
- **Flyway** - Migrações de banco de dados
- **Maven**: Gerenciamento de dependências
- **Docker e Docker Compose** - Containerização da aplicação 

## Modelo de Dados - Diagrama Entidade-Relacionamento (ERD):

<img width="725" height="761" alt="ecommerce-diagram drawio" src="https://github.com/user-attachments/assets/2396dea6-ac1f-474b-ae16-627bed2f05f3" />

## Pré-requisitos

Para executar a aplicação, você precisa de:
- Java 21 (JDK instalado e configurado).
- Maven (para build local).
- PostgreSQL 16 (instalado localmente ou via Docker).
- Docker e Docker Compose (opcional, para execução em contêineres).
- Ferramenta para testar APIs (ex.: Postman ou Insomnia).

## Como executar 

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


## 🚦 Endpoints

### APIs públicas 
```http
# Registrar um novo usuário
POST /auth/register

# Autenticar um usuário e obter token JWT 
POST /auth/login

# Listar todos os produtos 
GET /public/products

# Obter detalhes de um produto específico
GET /public/products/{productId}

# Filtrar produtos (ex.: por categoria, preço, etc.)
GET /public/products/filter

# Adicionar item ao carrinho 
POST /public/cart/add

# Obter itens do carrinho
GET /public/cart/get

# Atualizar item do carrinho
PUT /public/cart/items/{cartItemId}

# Remover item do carrinho
DELETE /public/cart/items/{cartItemId}

# Limpar o carrinho  
DELETE /public/cart

# Finalizar compra e criar pedido 
POST /orders/checkout

# Listar pedidos do usuário
GET /orders

# Obter detalhes de um pedido específico
GET /orders/{orderId}

# Obter informações de pagamento de um pedido
GET /payment/{orderId}
```

### APIs Administrativas (Autenticação Requerida)
```http
# Adicionar categoria
GET /admin/category

# Listar categorias
POST /admin/category

#  Adicionar novo produto
POST /admin/products

# Atualizar um produto
PUT /admin/products

# Excluir um produto
DELETE /admin/products/{productId}

# Listar todos os pedidos
GET /admin/orders

# Atualizar status de um pedido
PUT /admin/orders/{orderId}
```
