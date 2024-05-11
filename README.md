# Desenvolvimento de API REST para Sistema Bancário com Spring Boot

Este projeto consiste na criação de uma **API REST** para um sistema bancário utilizando **Java** e **Spring Boot**. A arquitetura adota o padrão **MVC** para uma organização clara e uma separação eficiente de responsabilidades.

## Principais Componentes

### Entidades

- **ClientesBanco:** Representação dos clientes do banco.
- **Transacoes:** Registros de transações financeiras.
- **Usuarios:** Informações sobre os usuários do sistema bancário.

### Controladores

Gerenciam operações de **CRUD** (Create, Read, Update, Delete) e buscas, utilizando **DTOs** (Data Transfer Objects) para uma transferência de dados eficiente.

### Repositórios

Estendidos de **JpaRepository** para operações de persistência, implementando critérios de paginação para melhorar a performance e lidar com grandes volumes de dados.

## Tecnologias Utilizadas

- **JPA (Java Persistence API) e Spring Data JPA:** Para interação com banco de dados de forma eficiente.
- **PostgreSQL:** Banco de dados utilizado para armazenamento dos dados.
- **Swagger:** Para documentação da API, facilitando o entendimento e uso por parte de desenvolvedores.
- **DTOs (Data Transfer Objects):** Para uma transferência eficiente de dados entre as diferentes camadas da aplicação.
- **Docker:** Utilizado para gestão de ambientes, com hospedagem de imagens no Docker Hub, e **Docker Compose** para simplificar a gestão de contêineres.
- **Maven:** Para gerenciamento de dependências e construção da aplicação.
- **AWS EC2:** Utilizado para implantação do sistema bancário, oferecendo escalabilidade e confiabilidade.

## Documentação

A documentação da API está disponível através do **Swagger**, proporcionando uma visão geral dos endpoints, parâmetros necessários e exemplos de respostas.
