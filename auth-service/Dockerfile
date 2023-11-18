FROM openjdk:17-jdk-slim
copy ./target/auth-service-0.0.1-SNAPSHOT.jar auth-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java","-jar","auth-service-0.0.1-SNAPSHOT.jar"]