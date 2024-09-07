FROM openjdk:17

VOLUME /tmp

COPY target/*.jar author.jar

ENTRYPOINT ["java", "-jar", "/author.jar"]