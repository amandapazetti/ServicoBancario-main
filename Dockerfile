
FROM maven:3.6.3-jdk-8 AS build


WORKDIR /app


COPY pom.xml .


COPY src ./src


RUN mvn clean install

FROM openjdk:8-jre-alpine

WORKDIR /app


COPY --from=build /app/target/ServicoBancario-main-0.0.1-SNAPSHOT.jar ./ServicoBancario-main.jar


EXPOSE 9007


CMD ["java", "-jar", "ServicoBancario-main.jar"]


