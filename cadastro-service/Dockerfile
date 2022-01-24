FROM openjdk:11

EXPOSE 8090

ADD build/libs/*.jar cadastro-service.jar

ENTRYPOINT ["java", "-jar", "cadastro-service.jar"]
