FROM openjdk:17-jdk-slim
copy ./target/resources-service-0.0.1-SNAPSHOT.jar resources-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java","-jar","resources-service-0.0.1-SNAPSHOT.jar"]