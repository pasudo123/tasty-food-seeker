FROM        adoptopenjdk:11-jre-hotspot
LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
ARG         LATEST_JAR
ARG         JAR_FILE=build/libs/${LATEST_JAR}
RUN         echo "$LATEST_JAR"
RUN         echo "$JAR_FILE"
COPY        ${JAR_FILE} app.jar
ENV         TZ Asia/Seoul
ENTRYPOINT  ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar", "--crawl.year=${YEAR}", "--crawl.year=${MONTH}"]
