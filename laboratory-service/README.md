
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

### Run

To launch the server, simply run with java -jar the generated jar file.

```bash
cd target
java -jar multitenant-1.0.jar
```

### Certificate

A self signed certificate is provided in order to show use how to setup the application.

The certificate was generated with the following commands:

```bash
keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 365 -keysize 2048
keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.p12 -deststoretype pkcs12
```

> Do not use the certificate provided in production and never put any secret in your application.yml file.


## API

You will find the swagger UI at https://localhost:8443/swagger-ui.html and http://localhost:8080/swagger-ui.html 

First generate a token by login as user Systelab and password Systelab. After that authorize Swagger by copying the bearer.

## Docker

### Running Postgres as a Docker image

docker run --name db -p 5432:5432 -e POSTGRES_DB=multitenant -e POSTGRES_USER=multitenant -e POSTGRES_PASSWORD=multitenant -d postgres

### Build docker image

There is an Automated Build Task in Docker Cloud in order to build the Docker Image. 
This task, triggers a new build with every git push to your source code repository to create a 'latest' image.
There is another build rule to trigger a new tag and create a 'version-x.y.z' image

You can always manually create the image with the following command:

```bash
docker build -t systelab/multitenant . 
```

### Run the container

```bash
docker run -p 8443:8443 -p 8080:8080 systelab/multitenant
```

The app will be available at https://localhost:8443/swagger-ui.html


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


