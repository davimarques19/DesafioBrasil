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
<p align="justify"> O banco de dados mysql será exposto na porta 3309 no docker, localmente permanece o padrão 3306</p>
<p align="justify"> A API será esposta no localhost, porta 8080</p>

