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

### set application in docker

you have to go through the following steps:

* compile and build the whole project with

```console
./gradlew build
````
* build jar file
```console
./gradlew bootJar
````

* build docker image 
```console
./gradlew bootbuildimage
````

* run docker image
```console
docker run docker.io/library/shop:0.0.1-SNAPSHOT
````