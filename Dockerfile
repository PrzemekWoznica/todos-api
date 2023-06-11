FROM maven:3.9.1-eclipse-temurin-17-alpine as build
WORKDIR /api
COPY pom.xml ./
COPY src/ src/
RUN mvn dependency:resolve
RUN mvn install

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /api/target/api-0.0.1-SNAPSHOT.jar api.jar
CMD java -jar api.jar