FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} team-player.jar
ENTRYPOINT ["java", "-Dspring.config.use-legacy-processing=true", "-jar", "/team-player.jar"]
EXPOSE 8090