# DesafioBrasil

## Descrição do Projeto
<p align="justify"> Desafio proposta afim de desenvolver uma api rest de usuários </p>

## Rodar o Projeto localmente
<p align="justify"> No terminal rode: </p>

```
git clone git@github.com:davimarques19/DesafioBrasil.git
```
<p align="justify"> Acesse o diretório do sistema: </p>

```
cd DesafioBrasil
```

## Subir container docker
```
docker-compose up -d --build
```
<p align="justify"> Validar se subiu normalmente </p>

```
docker ps
```

<p align="justify"> Derrubar o container </p>

```
docker-compose down
```

<p align="justify"> O banco de dados mysql será exposto na porta 3309 no docker, localmente permanece o padrão 3306</p>
<p align="justify"> A API será esposta no localhost, porta 8080</p>

## Segurança API
<p align="justify"> Está sendo utilizado o método basicAutentication do spring.
Existem dois usuários que podem efetuar essa autenticação, que são: <strong>admin</strong> e <strong>user</strong>
  Com isso ao tentar efetuar um post ou put será necessário colocar login de admin: </p>

```
User:admin 
Password: Brasil1000@

User: user
Password: brasil1020
```


## Testando API
<p align="justify"> Acesse a documentação no path: </p>

```
localhost:8080/swagger-ui.html
```


