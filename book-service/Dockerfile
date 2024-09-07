FROM openjdk:17

VOLUME /tmp

COPY target/*.jar resource.jar

ENTRYPOINT ["java", "-jar", "/resource.jar"]