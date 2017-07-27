FROM stayrascal/infra-image
MAINTAINER stayrascal

ARG jar

WORKDIR /apps/boot


COPY ./build/libs/$jar $jar
COPY ./docker/run.sh run.sh
RUN chmod u+x run.sh

COPY ./config config
VOLUME /apps/boot/config

ENV JAR_FILE=$jar

EXPOSE 8080
