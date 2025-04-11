
# Desafio - Sistema de Streaming de Filmes

Este projeto foi desenvolvido como parte do curso da [DevSuperior](https://devsuperior.com/), com o objetivo de implementar um sistema backend para um serviço de streaming de filmes usando Java e Spring Boot, seguindo práticas de TDD.

## Modelo Conceitual

![Image](https://github.com/user-attachments/assets/39c3f30e-3684-4366-ba16-54d3461610e5)

## Tecnologias utilizadas
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- OAuth2
- Banco de dados H2 (em memória)
- Maven
- Postman (para testes das requisições)

## Funcionalidades implementadas
O sistema possui autenticação e autorização baseada em roles:

- ROLE_VISITOR: pode listar filmes, buscar detalhes dos filmes e visualizar reviews.
- ROLE_MEMBER: possui todas as permissões do VISITOR, além de poder criar reviews dos filmes.

### Casos de uso

#### 1. Listar filmes
- [OUT] O sistema apresenta uma listagem dos nomes de todos gêneros, bem como uma listagem paginada com título, subtítulo, ano e imagem dos filmes, ordenada alfabeticamente por título.
- [IN] O usuário visitante ou membro seleciona, opcionalmente, um gênero.
- [OUT] O sistema apresenta a listagem atualizada, restringindo somente ao gênero selecionado.

Exemplo de requisição:
```
GET /movies?genreId=2&page=0&size=6&sort=title,ASC
```

#### 2. Visualizar detalhes do filme
- [IN] O usuário visitante ou membro seleciona um filme
- [OUT] O sistema informa título, subtítulo, ano, imagem e sinopse do filme, e também uma listagem dos textos das avaliações daquele filme juntamente com nome do usuário que fez cada avaliação.

#### 3. Inserir avaliação (Somente MEMBER)
- [IN] O usuário membro informa, opcionalmente, um texto para avaliação do filme.
- [OUT] O sistema apresenta os dados atualizados, já aparecendo também a avaliação feita pelo usuário.
- [Exceção] O sistema apresenta uma mensagem de que não é permitido texto vazio na avaliação 

---

## Critérios de Validação
| Endpoint | Restrições | Retorno esperado |
|----------|------------|-----------------|
|GET /genres|Token inválido|401 Unauthorized|
|GET /genres|VISITOR ou MEMBER|200 OK + Lista de Gêneros|
|GET /movies/{id}|Token inválido|401 Unauthorized|
|GET /movies/{id}|VISITOR ou MEMBER|200 OK + Filme Detalhado|
|GET /movies/{id}|Filme inexistente|404 Not Found|
|GET /movies|Token inválido|401 Unauthorized|
|GET /movies|VISITOR ou MEMBER|200 OK + Página de Filmes|
|GET /movies?genreId={id}|VISITOR ou MEMBER|200 OK + Filmes filtrados|
|POST /reviews|Token inválido|401 Unauthorized|
|POST /reviews|VISITOR|403 Forbidden|
|POST /reviews|MEMBER com dados válidos|201 Created|
|POST /reviews|MEMBER com dados inválidos|422 Unprocessable Entity|

---

## Considerações Técnicas

### Problema N+1
Durante o desenvolvimento, um dos desafios foi resolver o problema das N+1 consultas no JPA, muito comum em cenários com múltiplos relacionamentos. Para resolver esse problema, foi utilizado:

- JPQL com JOIN FETCH
- Consultas SQL nativas
- Ajuste correto das relações nas entidades

### Relações entre entidades no Spring JPA
- @ManyToOne: Define um relacionamento Muitos-para-Um (muitos objetos apontando para um mesmo objeto pai).
- @ManyToMany: Define um relacionamento Muitos-para-Muitos (vários objetos se relacionando com vários).
- JOIN FETCH: Utilizado em consultas JPQL para buscar uma entidade e suas associações em uma única query, evitando o problema N+1.
