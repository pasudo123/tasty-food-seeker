FROM        adoptopenjdk:11-jre-hotspot
STOPSIGNAL  SIGTERM
LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
ARG         JAR_FILE=build/libs/*.jar
RUN         echo "current jar file :: $JAR_FILE" && \
            apt-get update && \
            apt-get upgrade && \
            apt-get install htop && \
            apt-get install vim -y
COPY        ${JAR_FILE} app.jar
ENV         TZ Asia/Seoul
ENTRYPOINT  ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar"]
