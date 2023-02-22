FROM amazoncorretto:17-al2-full
WORKDIR /app
COPY ./target/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar" ,"backend.jar"]