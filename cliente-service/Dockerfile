FROM openjdk:11

EXPOSE 8091

ADD build/libs/*.jar cliente-service.jar

ENTRYPOINT ["java", "-jar", "cliente-service.jar"]
