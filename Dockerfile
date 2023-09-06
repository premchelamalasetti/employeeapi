FROM openjdk:17
COPY target/apidemo.jar /usr/app
WORKDIR /usr/app
ENTRYPOINT [ "java", "-jar","apidemo.jar" ]