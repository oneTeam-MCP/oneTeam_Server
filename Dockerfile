FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/server.jar server.jar
CMD ["java", "-jar", "server.jar"]
EXPOSE 8080
