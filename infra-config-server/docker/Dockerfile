FROM stayrascal/infra-image
MAINTAINER stayrascal

ARG jar

WORKDIR /apps/boot

COPY ./build/libs/$jar $jar
COPY ./docker/run.sh run.sh
COPY ./docker/files/infra-config-server.jks /apps/java8/infra-config-server.jks
COPY ./docker/files/US_export_policy.jar /apps/java8/jre/lib/security/US_export_policy.jar
COPY ./docker/files/local_policy.jar /apps/java8/jre/lib/security/local_policy.jar

RUN chmod u+x run.sh

ENV ENCRYPT_KEY_STORE_PASSWORD my-s70r3-s3cr3t
ENV ENCRYPT_KEY_STORE_ALIAS infra-config-server-key
ENV ENCRYPT_KEY_STORE_SECRET my-k34-s3cr3t
ENV JAR_FILE=$jar

EXPOSE 8888

CMD ["./run.sh"]