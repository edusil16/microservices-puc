FROM openjdk:11

EXPOSE 8761

ADD build/libs/*.jar registry-service.jar

ENTRYPOINT ["java", "-jar", "registry-service.jar"]
