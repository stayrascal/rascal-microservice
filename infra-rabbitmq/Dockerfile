FROM rabbitmq:3.6.11-management
MAINTAINER stayrascal

COPY rabbitmq-cluster.sh /usr/local/bin/
COPY pre-entrypoint.sh /

ADD rabbitmq.config /etc/rabbitmq/

RUN chmod u+rw /etc/rabbitmq/rabbitmq.config
RUN chmod +x /usr/local/bin/rabbitmq-cluster.sh
RUN chmod +x /pre-entrypoint.sh

EXPOSE 5672 15672 25672 4369 9100 9101 9102 9103 9104 9105
ENTRYPOINT ["/pre-entrypoint.sh"]
CMD ["rabbitmq-cluster.sh"]