FROM openjdk:18.0.2-jdk

COPY build/libs/toy-project-0.0.1-SNAPSHOT.jar /home/toy/deploy/toy-project.jar
COPY scripts /home/toy/scripts

EXPOSE 9090
ENTRYPOINT ["/home/toy/scripts/startup.sh", "toy-project.jar"]