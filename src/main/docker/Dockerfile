FROM java:8
EXPOSE 8080
ADD courseapp.jar courseapp.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","courseapp.jar"]