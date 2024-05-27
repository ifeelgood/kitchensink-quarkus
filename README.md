# Modernization Factory: kitchensink-quarkus

This project showcases migration of legacy JBoss java application to modern quarkus platform.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## System requirements

All you need to build these projects is Java 21.0 (Java SDK 21) or later and [Docker](https://www.docker.com/products/docker-desktop/). If you do not have Docker installed you can alternatively provide URL for your MongoDB instance, it will eliminate the requirement for Docker.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
In dev mode this will also spin up MongoDB instance and therefore you need docker demon running as a pre-requisite.
If you do not want to install Docker or you wish to use your mongoDB instance you should pass it using the alternative command:
```shell script
./mvnw compile quarkus:dev "-Dquarkus.mongodb.connection-string=mongodb://localhost:27017"
```

Once build is completed and application is started you can access it via browser at http://localhost:8080

> **_NOTE:_**  Quarkus ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.

The application is now runnable using
```shell script
java -jar target/quarkus-app/quarkus-run.jar "-Dquarkus.mongodb.connection-string=mongodb://localhost:27017"
```
Replace mongoDB connection string with the connection string to your running mongoDB instance.

Once started you can access it via browser at http://localhost:8080

## Related Guides

- MyFaces ([guide](https://myfaces.apache.org/#/coregettingstarted?id=quarkus)): MyFaces is a JSF / Faces implementation which also provides a Quarkus integration
- MongoDB with Panache ([guide](https://quarkus.io/guides/mongodb-panache)): Simplify your persistence code for MongoDB via the active record or the repository pattern
- Quarkus REST ([guide]([https://quarkus.io/guides/resteasy](https://quarkus.io/guides/rest))): REST endpoint framework implementing Jakarta REST and more
- Testing Quarkus application ([guide](https://quarkus.io/guides/getting-started-testing)): Testing framework based on JUnit 5 and Mockito
- Measuring test coverage ([guide](https://quarkus.io/guides/tests-with-coverage)): JaCoCo is a free code coverage library for Java
