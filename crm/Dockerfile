FROM gradle:7.2.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-ea-oracle
EXPOSE 8080/tcp/app
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/crm-service.jar

ENTRYPOINT ["java", "-jar", "/app/crm-service.jar"]