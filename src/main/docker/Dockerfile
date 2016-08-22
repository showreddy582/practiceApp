FROM java:8
EXPOSE 8080
ADD target/courseApp.jar courseApp.jar
ENTRYPONT ["java","-Dspring.profiles.active=docker", "-jar","courseApp.jar"]