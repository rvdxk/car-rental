FROM eclipse-temurin:17

LABEL mentainer="rvdxk1@gmail.com"

WORKDIR /app

COPY target/car-rental-spring-project-0.0.1-SNAPSHOT.jar /app/car-rental.jar

ENTRYPOINT ["java", "-jar", "car-rental.jar"]