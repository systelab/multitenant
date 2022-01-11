
# Multitenant application sample

This project is an application skeleton for a typical [Spring Boot][sboot] backend application. You can use it
to quickly bootstrap your projects and dev environment.


## Getting Started

To get you started you can simply clone the `multitenant` repository and install the dependencies:

### Prerequisites

You need [git][git] to clone the `multitenant` repository.

You will need [OpenJDK 11][jdk-download] and [Maven][maven].

### Clone `multitenant`

Clone the `multitenant` repository using git:

```bash
git clone https://github.com/systelab/multitenant.git
cd multitenant
```

### Install Dependencies

In order to install the dependencies and generate the Uber jar you must run:

```bash
mvn clean install
```

for each service.

## API

You will find the swagger UI at http://localhost:9090/swagger

First generate a token by connection to AWS Cognito. After that authorize Swagger by copying the bearer.

## Docker

### Running Postgres and Redis as a Docker image

docker run --name db -p 5432:5432 -e POSTGRES_DB=multitenant -e POSTGRES_USER=multitenant -e POSTGRES_PASSWORD=multitenant -d postgres

docker run --name db -p 5432:5432 -e POSTGRES_DB=inventory -e POSTGRES_USER=inventory -e POSTGRES_PASSWORD=inventory -d postgres

docker run --name inventory -p 8060:8060 -e spring.datasource.url=jdbc:postgresql://docker.for.mac.localhost:5432/inventory inventory:0.0.1-SNAPSHOT


docker run --name redis -p 6379:6379 -d redis

### Build docker image

Build the docker images with the following command:

```bash
mvn spring-boot:build-image
```

### Run the container

Use the provided docker compose configuration and run:
```bash
docker compose up
```


[git]: https://git-scm.com/
[sboot]: https://projects.spring.io/spring-boot/
[maven]: https://maven.apache.org/download.cgi
[jdk-download]: https://adoptopenjdk.net/
[JEE]: http://www.oracle.com/technetwork/java/javaee/tech/index.html
[jwt]: https://jwt.io/
[cors]: https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
[swagger]: https://swagger.io/
[allure]: https://docs.qameta.io/allure/
[junit]: https://junit.org/junit5/

