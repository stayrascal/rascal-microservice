FROM rabbitmq:3.5.3-management
MAINTAINER stayrascal

COPY rabbitmq-cluster.sh /usr/local/bin/
COPY docker-entrypoint.sh /

RUN chmod +x /usr/local/bin/rabbitmq-cluster.sh
RUN chmod +x /docker-entrypoint.sh

EXPOSE 5672 15672 25672 4369 9100 9101 9102 9103 9104 9105
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["rabbitmq-cluster.sh"]