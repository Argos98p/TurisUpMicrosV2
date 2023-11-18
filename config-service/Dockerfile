FROM openjdk:17-jdk-slim
copy ./target/config-service-0.0.1-SNAPSHOT.jar config-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java","-jar","config-service-0.0.1-SNAPSHOT.jar"]