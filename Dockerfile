FROM maven:3.9.3-eclipse-temurin-17 AS build

COPY /src /app/src

COPY /pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skin

FROM eclipse-temurin:21-jre

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "/app.jar"]
