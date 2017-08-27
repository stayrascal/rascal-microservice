FROM docker.elastic.co/kibana/kibana:5.5.2
MAINTAINER stayrascal

ADD config/kibana.yml /usr/share/kibana/config/kibana.yml

USER root
RUN chown kibana:kibana config/kibana.yml

USER kibana

