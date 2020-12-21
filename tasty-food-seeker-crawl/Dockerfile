# multi stage images
FROM        adoptopenjdk:11-jre-hotspot as builder
LABEL       maintainer="pasudo123"
ARG         JAR_FILE=build/libs/*.jar
COPY        ${JAR_FILE} application.jar
RUN         java -Djarmode=layertools -jar application.jar extract

FROM        openjdk11:alpine-jre
EXPOSE      10428
LABEL       maintainer="pasudo123"
WORKDIR     application
ENV         TZ Asia/Seoul
ENTRYPOINT  ["java", "-jar", "./application.jar"]
