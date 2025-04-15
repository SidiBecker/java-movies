# java-movies

A project that list the Golden Raspberry Awards movies, using a .csv file.
Developed with `Spring Boot` and `Java 21`. It persist and get all data in a `H2` database.

## CSV Location

`src/main/resources/movies.csv`

## How to start a development server

- `mvn clean install spring-boot:run`

## Endpoints

- `GET http://localhost:8080/movies`
- `GET http://localhost:8080/producers/min-max-interval`

## Swagger

`http://localhost:8080/swagger-ui/index.html`

## How to run unit tests

`mvn test`

## How to build a .jar

`mvn clean install package`

To run .jar after above command:

`java -jar target/movies-0.0.1-SNAPSHOT.jar`
