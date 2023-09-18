FROM gradle:7.6 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build

FROM eclipse-temurin:17-jdk-focal
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/shop-0.0.1-SNAPSHOT.jar /app/
RUN bash -c 'touch /app/shop-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/shop-0.0.1-SNAPSHOT.jar"]