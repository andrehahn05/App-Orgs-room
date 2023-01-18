

# App-Orgs-Room-Migrations

App persistencia-de-dados-com-room-migrations
Formação Android com Kotlin Alura-Cursos(https://cursos.alura.com.br/formacao-android-kotlin)

## 🔨 Funcionalidades do projeto

O App lista produtos com imagem, título, descrição e valor. Também, é possível cadastrar, editar, deletar, produtos e filtrar a busca.



![](img/amostra.gif)

## ✔️ Técnicas e tecnologias utilizadas

As técnicas e tecnologias utilizadas pra isso são:

- `Coil`: carregar imagens via requisição HTTP
- `View Binding`: busca de views do layout de forma segura
- `AlertDialog`: Exibição de formulário para carregar novas imagens do produto
- `Fontes personalizadas`: configuração para adicionar novas fontes
- `Extension functions`: adicionar comportamentos em outras classes para reutilizá-los
- `Fluxo de autenticação com DataStore`: armazenar tipos primitivos via preferences, como por exemplo, o id do usuário autenticado
- `Migration`: permitir que o App evolua cada vez que as entidades do Room são modificadas, pois modificam também o schema do banco de dados
- `Coroutines e Flow`: utilizados para fazer a comunicação com o Room e o DataStore
- `StateFlow`: permitir a alteração do valor do Flow fora do builder, como por exemplo, atualizar o valor ao coletar novos valores de um outro Flow.
- `Activity base`: compartilhar código comum entre as Activities, como por exemplo, código de autenticação que permite acessar o usuário logado, deslogar do App e verificar se o usuário está ou não logado
- `Relacionamento no Room`: configurar entidade para identificar a qual registro ela pertence, como por exemplo, um produto que pertence a um usuário
