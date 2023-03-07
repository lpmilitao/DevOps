
# Stack Over-Cat

Seguindo a mesma ideia do StackOverflow, o Stack Over-Cat veio para ser um local onde os donos de gatos podem publicar suas dúvidas e receberem respostas, é claro, sobre seus gatinhos.
Também podem cadastrar seus gatinhos na aplicação.


## Instalação
Após criar o banco de dados, que está no arquivo `schemas.sql`, no PostgreSQL 14, e rodar a aplicação no IntelliJ, então abra o Visual Studio Code e, pelo terminal, instale o projeto com npm:

```bash
  npm install app
```
    
## Documentação da API

#### Faz login com Basic Auth

```http
  POST /login
```

#### Faz logout

```http
  POST /logout
```

#### Cadastra um novo usuário

```http
  POST /usuarios
```

#### Edita o usuário logado

```http
  PUT /usuarios
```

#### Detalha o usuário logado

```http
  GET /usuarios/eu
```

#### Lista as solicitações de amizade recebidas pelo usuário logado

```http
  GET /usuarios/solicitacoes
```

#### Lista as solicitações de amizade feitas pelo usuário logado

```http
  GET /usuarios/solicitacoes-feitas
```

#### Usuário logado solicita amizade a outro usuário
Recebe como parâmetro o id do usuário a quem essa solicitação será enviada.

```http
  POST /usuarios/id/solicitar
```

#### Aceita um pedido de amizade
Recebe como parâmetro o id da solicitação a ser aceita.

```http
  PUT /usuarios/id/aceitar
```

#### Desfaz uma amizade
Recebe como parâmetro o id do amigo.

```http
  DELETE /usuarios/amigos/id
```

#### Lista os amigos do usuário de forma paginada
Recebe como parâmetro um filtro para buscar amigos e configurações de paginação.

```http
  GET /usuarios/amigos?filtro
```

#### Lista todos os usuários cadastrados no site de forma paginada
Recebe como parâmetro um filtro para buscar usuários e configurações de paginação.

```http
  GET /usuarios?filtro
```

#### Cadastra um novo gato para o usuário logado

```http
  POST /gatos
```

#### Edita um gato do usuário logado
Recebendo como parâmetro o id do gato a ser editado.

```http
  PUT /gatos/id
```

#### Lista os gatos do usuário logado

```http
  GET /gatos
```

#### Lista os gatos de outro usuário
Recebe como parâmetro o id do usuário de quem os gatos devem ser listados.

```http
  GET /gatos/id
```

#### Adiciona uma nova publicação

```http
  POST /publicacoes
```

#### Curte uma publicação
Recebe como parâmetro o id da publicaçã que será curtida.

```http
  POST /publicacoes/id/curtir
```

#### Descurte uma publicação
Recebe como parâmetro o id da publicaçã que será descurtida.

```http
  DELTE /publicacoes/id/curtir
```

#### Comentar em uma publicação
Recebe como parâmetro o id da publicação em que o comentário será feito.

```http
  POST /publicacoes/id/comentar
```

#### Lista publicações do usuário e de seus amigos ordenando pela mais recente e de forma paginada
Pode receber como parâmetro outras configurações de paginação.

```http
  GET /publicacoes?sort=dataPublicacao,desc
```

#### Lista publicações todas as publicações públicas do site

```http
  GET /publicacoes/publicas
```

#### Lista publicações de um determinado usuário
Recebe como parâmetro o id do usuário de quem as publicações serão listadas.

```http
  GET /publicacoes/usuario/id
```