FROM eclipse-temurin:17 as build

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src

RUN apt-get update && apt-get install -y maven

RUN mvn clean package -DskipTests


FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/target/car-rental-spring-project-0.0.1-SNAPSHOT.jar /app/car-rental.jar

COPY .env /app/.env

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "car-rental.jar"]