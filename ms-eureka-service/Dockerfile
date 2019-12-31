FROM openjdk:8
ADD target/ms-eureka-service-0.0.1-SNAPSHOT.jar ms-eureka-service.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "ms-eureka-service.jar"]