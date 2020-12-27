FROM        adoptopenjdk:11-jre-hotspot
LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
ARG         LATEST_JAR
ARG         YEAR
ARG         MONTH
ARG         JAR_FILE=build/libs/${LATEST_JAR}
RUN         echo "$LATEST_JAR, $YEAR, $MONTH}"
RUN         echo "$JAR_FILE"
COPY        ${JAR_FILE} app.jar
ENV         PARAM_YEAR=${YEAR}
ENV         PARAM_MONTH=${MONTH}
ENV         TZ Asia/Seoul
ENTRYPOINT  ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar", "--crawl.year=${PARAM_YEAR}", "--crawl.month=${PARAM_MONTH}"]
