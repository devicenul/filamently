FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/filamently-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

VOLUME /app/data

ENTRYPOINT ["java", "-jar", "app.jar"]
