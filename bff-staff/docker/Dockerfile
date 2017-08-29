FROM stayrascal/infra-image
MAINTAINER stayrascal

ARG jar

WORKDIR /apps/boot

COPY ./config/filebeat.yml config/filebeat.yml
COPY ./build/libs/$jar $jar
COPY ./docker/run.sh run.sh
RUN chmod u+x run.sh

ENV JAR_FILE=$jar
CMD ["./run.sh"]

EXPOSE 8082