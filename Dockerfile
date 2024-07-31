
FROM openjdk:17-jdk

WORKDIR /app
# Copy the application JAR file to the container
COPY target/*.jar /app/pokesoap.jar
EXPOSE 8088
# Command to run the application and access to H2 console
ENTRYPOINT ["java", "-jar", "pokesoap.jar",  "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]