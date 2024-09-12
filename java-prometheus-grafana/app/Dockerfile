FROM openjdk:8-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

COPY /app/target/forum.jar forum.jar
ADD /app/target/forum.jar forum.jar

ENTRYPOINT ["java","-Xms128M","-Xmx128M","-XX:PermSize=64m","-XX:MaxPermSize=128m","-Dspring.profiles.active=prod","-jar","/app.jar"]
