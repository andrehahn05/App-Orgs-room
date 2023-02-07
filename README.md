

# App-Orgs-Room-Migrations

App persistencia-de-dados-com-room-migrations
[Formação Android com Kotlin Alura-Cursos ](https://cursos.alura.com.br/formacao-android-kotlin)

## 🔨 Funcionalidades do projeto

O App lista produtos com imagem, título, descrição e valor. Também, é possível cadastrar, editar, deletar, produtos e filtrar a busca.



## Cadastro e Login

![cadastroOrgsRoom](https://user-images.githubusercontent.com/46737586/213084148-7dd65dd2-618c-49b3-8eeb-1e3833cfa18e.gif)

## Ordenação de produtos
Menus de opções para ordenar produtos por nome " A-Z, Z-A " ou  por valor.

![menu_filtro](https://user-images.githubusercontent.com/46737586/213306302-0e0c29e9-d8f0-47e3-9cac-79f1f7b1806d.gif)

## Menu de popup

Menu de popup ( Editar e Remover )  ao clicar e pressionar um item da lista de produtos.

![menu_popup](https://user-images.githubusercontent.com/46737586/213311233-10c06ec5-ea1d-45f1-890d-d1a019347543.gif)

<br>

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
<br>

## 📁 Acesso ao projeto
Você pode clonar projeto
```
git clone git@github.com:andrehahn05/app-orgs-com-Kotlin-room-migrations.git
```
se preferir

Você pode pode [baixar-lo](https://github.com/andrehahn05/app-orgs-com-Kotlin-room-migrations/archive/refs/heads/main.zip) zip

O Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. 
Ao finalizar as tasks, você pode executar o App 🏆
