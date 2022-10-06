<h1 align="center">
  Controll Suggestions API 🐝
</h1>

### API para Controle de Sugestão de Produtos

### 📝 Sobre a API
 - API para Controle de Sugestões de Produtos para futuras compras dos Clientes na Loja. A API obtém os novos pedidos da loja através do Order Feed v3 da VTEX, onde registra e armazena os dados relevantes para geração de combinações, que posteriormente podem ser ativadas/desativadas como sugestões válidas para os Clientes através do Admin da Loja.

---
### 🔧 Tecnologias e Ferramentas na API

As seguintes tecnologias/ferramentas foram usadas na construção da API:

- **Java**
- **Spring Boot**
- **Jakarta Persistence API (JPA) e Hibernate**
- **Spring Data JPA**
- **Spring Security**
- **Flyway**
- **MySQL**
- **Lombok**
- **AWS EC2**
- **AWS RDS**
---
#### 📌 **Características da Aplicação**

- **Migração de Bancos de Dados - Flyway**
- **Segurança com OAuth 2 - RFC 6749**
- **2 Escopos diferentes para autorização (ADMIN e STORE)**
  - ADMIN - Realiza todas as operações na API (Consultas de Produtos, Combinações, Ativação e Inativação de Combinações)
  - STORE - Realiza consultas de Combinações Ativas (Sugestões)
- **Especificação de Erros segue a Problem Details for HTTP APIs - RFC 7807**
- **Shallow Etags para redução de tráfego**
- **Documentação Open API 3** [aqui](https://hccontroll03.app.br/swagger-ui/index.html)

---
### 🔨 Executando a API

**1. Clone a Aplicação**

```bash
https://github.com/controll-final/controll-suggestions-api.git
```

**2. Instale o MySQL ou tenha uma instância disponível**

**3. Altere as Propriedades da Aplicação**

+ Abra o arquivo `src/main/resources/application.properties`

+ Altere as Seguintes Propriedades:
```
spring.datasource.url=(String de Conexão com o Banco de Dados - ex.: jdbc:mysql://localhost/suggestionsdb?createDatabaseIfNotExist=true&serverTimezone=UTC)
spring.datasource.username=
spring.datasource.password=

security.admin-id=(ID para uso do Admin)
security.admin-secret=(Secret para uso do Admin)

security.store-id=(ID para uso da Store)
security.store-secret=(Secret para uso da Store)

security.jwt-signin-key=(Chave para assinatura dos Tokens - ex.: 89a7sd89f7as98f7dsa98fds7fd89sasd9898asdf98p)

vtex.api.vtex-account-name=(Nome da Conta VTEX)
vtex.api.vtex-environment=(Nome Environment API VTEX)
vtex.api.vtex-app-key=(App Key API VTEX)
vtex.api.vtex-app-token=(App Token API VTEX)
```

**4. Faça o build e execute a aplicação utilizando o maven**

```bash
mvn package
java -jar target/controll-suggestions-api-1.0.0.jar

```

Como alternativa, você pode executar o aplicativo sem empacotá-lo usando -

```bash
mvn spring-boot:run
```

A Aplicação iniciará em <http://localhost:8080>.

#
## 💪🏻 Desenvolvido por
- [Douglas Rodrigues](https://www.linkedin.com/in/douglas-rodrigues-pnz/)
