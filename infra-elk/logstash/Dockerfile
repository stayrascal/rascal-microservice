FROM docker.elastic.co/logstash/logstash:5.5.2
MAINTAINER stayrascal

RUN rm -f /usr/share/logstash/pipeline/logstash.conf

ADD pipeline/ /usr/share/logstash/pipeline/
ADD config/ /usr/share/logstash/config/