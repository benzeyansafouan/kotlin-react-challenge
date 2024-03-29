# iMedia24 Coding challenge

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Official Kotlin documentation](https://kotlinlang.org/docs/home.html)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/#build-image)
* [Flyway database migration tool](https://flywaydb.org/documentation/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

# Set application in docker

Instructions for building and running the application using Docker containers.

## Docker Containerization

### Build Docker Image

To build a Docker image for this application, use the following command:

```bash
docker build -t imedia24-backend-challenge .
````

### Run Docker Container

To run the application as a Docker container, use the following command:

```bash
docker run -p 8080:8080 imedia24-backend-challenge
````
