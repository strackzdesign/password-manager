### STAGE 1: Build ###
FROM openjdk:21-slim AS build
WORKDIR /app
COPY . /app
RUN ./mvnw clean package


### STAGE 2: Run ###
FROM openjdk:21-slim
EXPOSE 8080
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8080"]
