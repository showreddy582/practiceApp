FROM java:8
EXPOSE 8000
ENV MYSQL_HOST localhost
ENV MYSQL_USER root
ENV MYSQL_PASSWORD root
ADD courseapp.jar courseapp.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","courseapp.jar"]