FROM openjdk:11

EXPOSE 8092

ADD build/libs/*.jar login-service.jar

ENTRYPOINT ["java", "-jar", "login-service.jar"]
