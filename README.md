# java-movies

A project that list the Golden Raspberry Awards movies, using a .csv file. It persist and get all data in a H2 database.

## Requirements

- `Java JDK 21`
- `Maven 3.9.2`

## CSV Location

`src/main/resources/movies.csv`

## How to start a development server

- `mvn install`
- `mvn spring-boot:run`

## Endpoints

- `GET /movies`
- `GET /producers/min-max-interval`

## Swagger

`http://localhost:8080/swagger-ui/index.html`

## How to run unit tests

`mvn test`

## How to build a .jar

`mvn clean install package`

To run .jar after above command:

`java -jar target/movies-0.0.1-SNAPSHOT.jar`
