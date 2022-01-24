FROM openjdk:11

EXPOSE 8080

ADD build/libs/*.jar gateway-service.jar

ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
