# Dockerfile

FROM maven:3.8.1-jdk-8@sha256:03257e9c22fa347a9ff589a3381018751e8bb36f0c505187d61731bff3a63c49
RUN rm -rf /app
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN rm -rf ./target/*.jar
RUN mvn clean package
COPY target/pfc-iban-1.0-SNAPSHOT.jar /app/pfc-iban-1.0-SNAPSHOT.jar
EXPOSE 8080 8081
ENTRYPOINT ["java", "-jar", "pfc-iban-1.0-SNAPSHOT.jar", "server", "config.yaml"]