# Modernization Factory: kitchensink-quarkus

This project showcases migration of legacy JBoss java application to modern quarkus platform.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
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
java -jar target/quarkus-app/quarkus-run.jar
```
Once started you can access it via browser at http://localhost:8080

## Related Guides

- MyFaces ([guide](https://myfaces.apache.org/#/coregettingstarted?id=quarkus)): MyFaces is a JSF / Faces implementation which also provides a Quarkus integration
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- Flyway ([guide](https://quarkus.io/guides/flyway)): Flyway is a popular database migration tool that is commonly used in JVM environments
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and more
- Testing Quarkus application ([guide](https://quarkus.io/guides/getting-started-testing)): Testing framework based on JUnit 5 and Mockito
- Measuring test coverage ([guide](https://quarkus.io/guides/tests-with-coverage)): JaCoCo is a free code coverage library for Java
- MongoDB with Panache ([guide](https://quarkus.io/guides/mongodb-panache)): Simplify your persistence code for MongoDB via the active record or the repository pattern
