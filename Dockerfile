# inspiration from : https://wkrzywiec.medium.com/how-to-run-database-backend-and-frontend-in-a-single-click-with-docker-compose-4bcda66f6de

# BUILD
FROM gradle:7.6.0-jdk17-jammy # when using wrapper I should not need gradle docker image, but whatever
RUN mkdir -p /workspace
WORKDIR /workspace
RUN echo $PWD
# fix from here https://stackoverflow.com/questions/46792438/build-gradle-project-inside-a-docker
COPY --chown=gradle:gradle . . 
RUN ./gradlew build --stacktrace

# RUN
FROM amazoncorretto:17.0.5
COPY --from=0 /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ap0.jar"]
