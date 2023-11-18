FROM openjdk:17-jdk-slim
copy ./target/eureka-service-0.0.1-SNAPSHOT.jar eureka-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java","-jar","eureka-service-0.0.1-SNAPSHOT.jar"]