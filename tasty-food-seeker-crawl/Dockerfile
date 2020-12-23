# multi stage dockerfile
#FROM        adoptopenjdk:11-jre-hotspot as builder
#LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
#WORKDIR     /workspace/app
#ARG         JAR_FILE=build/libs/*.jar
#COPY        ${JAR_FILE} /workspace/app
#RUN         mkdir -p build/dependency &&\
#            cd build/dependency; jar -xf ../libs/*.jar

FROM        adoptopenjdk:11-jre-hotspot
LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
ARG         JAR_FILE=build/libs/*.jar
COPY        ${JAR_FILE} app.jar
#ARG         DEPENDENCY=build/dependency
#COPY        ${DEPENDENCY}/BOOT-INF/lib      /application/lib
#COPY        ${DEPENDENCY}/META-INF          /application/META-INF
#COPY        ${DEPENDENCY}/BOOT-INF/classes  /application
ENV         TZ Asia/Seoul
ENTRYPOINT  ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar"]
