FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /api
COPY src /api/src
COPY pom.xml /api
RUN mvn -f /api/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build api/target/api-0.0.1-SNAPSHOT.jar /api/api-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api/api-0.0.1.jar"]