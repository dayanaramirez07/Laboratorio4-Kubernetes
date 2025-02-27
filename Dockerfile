FROM openjdk:17-slim
WORKDIR /app
COPY target/flight-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar"]