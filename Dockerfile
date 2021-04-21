FROM gradle:6.7.0-jdk11 AS builder
COPY . /app
WORKDIR /app
USER root
RUN gradle clean assemble -Dorg.gradle.daemon=false --stacktrace --info

FROM openjdk:12-alpine
COPY --from=builder ./app/build/libs/*.jar app.jar
EXPOSE 8080
CMD java -jar app.jar