# Dockerfile

FROM maven:3.8.1-jdk-8@sha256:03257e9c22fa347a9ff589a3381018751e8bb36f0c505187d61731bff3a63c49 AS build
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM openjdk:8@sha256:1b68e7d8373940d8ece63eb3528aae952d544afb377887b132ebebe050276216
COPY --from=build /app/target/pfc-iban-1.0-SNAPSHOT.jar ./pfc-iban-1.0-SNAPSHOT.jar
COPY --from=build /app/config.yaml ./config.yaml
EXPOSE 8080 8081
ENTRYPOINT ["java", "-jar", "pfc-iban-1.0-SNAPSHOT.jar", "server", "config.yaml"]