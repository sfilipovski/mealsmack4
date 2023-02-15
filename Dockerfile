FROM openjdk:19-djk-alpine
ARG JAR_File=target/*.jar
COPY ./target/Mealsmack-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]