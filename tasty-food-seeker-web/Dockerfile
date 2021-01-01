FROM        adoptopenjdk:11-jre-hotspot
LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
ARG         JAR_FILE=build/libs/*.jar
RUN         echo "current jar file :: $JAR_FILE"
COPY        ${JAR_FILE} app.jar
ENV         TZ Asia/Seoul
ENTRYPOINT  ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar"]
