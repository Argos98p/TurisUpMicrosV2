FROM openjdk:17-jdk-slim
copy ./target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service-0.0.1-SNAPSHOT.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java","-jar","gateway-service-0.0.1-SNAPSHOT.jar"]