# Backend Developer Challenge
Spring Boot project to manage places (CRUD). This project is part of Click Bus candidate evaluation.

# Application Setup (Local)

## Prerequisites
Before running the application you must ensure that the following dependencies are correctly installed:

```
Java 8
Maven 3.2.2
```

You also need to provide the following environment properties:
 - spring.datasource.platform
 - spring.datasource.url
 - spring.datasource.username
 - spring.datasource.password
 - spring.jpa.properties.hibernate.dialect
 - spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults

## Installation
Access the project:
```
cd [projectPath]
```
It's necessary to compile the code and download the project dependencies:
```
mvn clean package
```
After this step, let's run the application:
```
mvn spring-boot:run
```
# API endpoints
These endpoints allow you to manage places. 
- If you are running the app locally, you can consider `HOST` as *http://localhost:8080*
- If you want the Cloud app, you can consider `HOST` as *https://developerchallenge.herokuapp.com/*

## GET
`HOST`[/findAll](#get-findAll) <br/>
`HOST`[/findAllByName](#get-findAllByName) <br/>
`HOST`[/findByName](#get-findByName) <br/>

## POST
`HOST`[/create](#post-create) <br/>

## PUT
`HOST`[/update](#put-update) <br/>
___
To check each endpoint parameters and responses, go to `HOST`/swagger-ui.html