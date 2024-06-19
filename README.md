# OrdersArticlesBack

## Build Backend
The application it is accessible under `localhost:8080`.

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

The MSSQL Server with func with Docker composer, **recomendable start docker desktop**

The database is created by itself in docker you only have to run the spring boot project

## Documentation apis

In the following link you can see the documentation of the apis with Swagger
- [Documentacion](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/)


## Build Frontend

The application it is accessible under `localhost:5173`.

dependencies must be installed

```
npm install 
```
## Orther instrucctions 

By keeping the back service started as a front service, it could already perform the operations consuming the apis.
