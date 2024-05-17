# Li4Movie

Li4Movie é um esboço inicial de um projeto que visa criar uma aplicação para recomendação de filmes. Atualmente, o projeto inclui a implementação básica da lógica de segurança, importando-a de um arquivo externo para lidar com dados de login. Além disso, são configuradas chaves individuais que podem ser utilizadas em um cofre de nuvem para garantir a segurança dos dados sensíveis.

## Descrição

Li4Movie é uma aplicação em fase inicial de desenvolvimento, projetada para recomendar filmes aos usuários com base em seus interesses e preferências. A lógica de segurança foi implementada para garantir a proteção dos dados de login dos usuários. Além disso, o projeto está configurado para utilizar chaves individuais para acesso seguro aos dados sensíveis armazenados em um cofre de nuvem.

## Tecnologias Utilizadas

- Java 22
- Spring Batch
- Banco de dados MySQL

## Funcionalidades

- Implementação da lógica de segurança
- Configuração de chaves individuais para acesso seguro aos dados
- Integração com inteligência artificial para personalização da experiência do usuário

## Pré-requisitos

Antes de executar este projeto, certifique-se de ter instalado:

- Java JDK 22 ou superior
- MySQL Server
- Maven

## Configuração

1. Clone este repositório:

git clone https://github.com/seu-usuario/Li4Movie.git


2. Configure o banco de dados MySQL:

- Por segurança nenhuma propriedade que envolve dados sensíveis foi posta nesse repositório.
- Utilize o mesmo banco de dados criando seu próprio banco de dados.
- As credenciais do banco de dados podem ser configuradas no arquivo `application.properties`.

3. Execute o projeto:

mvn spring-boot:run




