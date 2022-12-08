FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kafkaProject-1.0.jar
ENTRYPOINT ["java","-jar","/kafkaProject-1.0.jar"]
EXPOSE  8080