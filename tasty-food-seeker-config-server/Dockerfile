FROM        adoptopenjdk:11-jre-hotspot
STOPSIGNAL  SIGTERM
LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
ARG         JAR_FILE=build/libs/*.jar
COPY        ${JAR_FILE} app.jar
ENV         TZ Asia/Seoul
ENTRYPOINT  ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar"]
