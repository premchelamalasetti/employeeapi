FROM openjdk:17
COPY build/libs/api-demo-2-0.0.1-SNAPSHOT.jar /api-demo/src/api-demo-2-0.0.1-SNAPSHOT.jar
CMD java -jar /api-demo/src/api-demo-2-0.0.1-SNAPSHOT.jar
