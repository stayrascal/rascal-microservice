FROM docker.elastic.co/elasticsearch/elasticsearch:5.5.2
MAINTAINER stayrascal

ADD config/elasticsearch.yml /usr/share/elasticsearch/config/elasticsearch.yml

USER root
RUN chown elasticsearch:elasticsearch config/elasticsearch.yml

USER elasticsearch

