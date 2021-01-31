FROM        adoptopenjdk:11-jre-hotspot
STOPSIGNAL  SIGTERM
LABEL       maintainer="pasudo123 <pasudo123@naver.com>"
ARG         YEAR
ARG         MONTH
ARG         JAR_FILE=build/libs/*.jar
RUN         echo "${YEAR}, ${MONTH}"
COPY        ${JAR_FILE} app.jar
ENV         TZ Asia/Seoul
ENV         PARAM_YEAR=$YEAR
ENV         PARAM_MONTH=$MONTH
# https://stackoverflow.com/questions/37904682/how-do-i-use-docker-environment-variable-in-entrypoint-array
# https://docs.docker.com/engine/reference/builder/#exec-form-entrypoint-example
# 차이점을 이해해야 한다.
ENTRYPOINT  java -jar -Dspring.profiles.active=prod ./app.jar --crawl.year=$PARAM_YEAR --crawl.month=$PARAM_MONTH
#ENTRYPOINT  ["java", "-jar", "-Dspring.profiles.active=prod", "./app.jar", "--crawl.year=echo $PARAM_YEAR", "--crawl.month=echo $PARAM_MONTH"]
