[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=narsha-io_keycloak-quarkus-archetype&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=narsha-io_keycloak-quarkus-archetype)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=narsha-io_keycloak-quarkus-archetype&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=narsha-io_keycloak-quarkus-archetype)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=narsha-io_keycloak-quarkus-archetype&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=narsha-io_keycloak-quarkus-archetype)

[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=narsha-io_keycloak-quarkus-archetype&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=narsha-io_keycloak-quarkus-archetype)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=narsha-io_keycloak-quarkus-archetype&metric=bugs)](https://sonarcloud.io/summary/new_code?id=narsha-io_keycloak-quarkus-archetype)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=narsha-io_keycloak-quarkus-archetype&metric=coverage)](https://sonarcloud.io/summary/new_code?id=narsha-io_keycloak-quarkus-archetype)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=narsha-io_keycloak-quarkus-archetype&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=narsha-io_keycloak-quarkus-archetype)

# keycloak-quarkus-archetype Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/keycloak-quarkus-archetype-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
