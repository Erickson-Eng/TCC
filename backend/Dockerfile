FROM maven:3.8.7-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-al2-full
WORKDIR /app
COPY --from=build ./app/target/*.jar backend.jar
ARG KEYCLOAK_URL
ARG REALM
ARG RESOURCE_SERVICE
ARG RESOURCE_SERVICE_CLIENT
ENTRYPOINT ["java", "-jar" ,"backend.jar"]