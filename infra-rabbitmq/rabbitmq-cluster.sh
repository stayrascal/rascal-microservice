#!/bin/bash

change_default_user() {
    if [ -z $RABBITMQ_DEFAULT_USER ] && [ -z $RABBITMQ_DEFAULT_PASSWORD ]; then
        echo "Maintaining default 'guest' user"
    else
        echo "Removing 'guest' user and adding ${RABBITMQ_DEFAULT_USER}"
        rabbitmqctl delete_user guest
        rabbitmqctl add_user $RABBITMQ_DEFAULT_USER $RABBITMQ_DEFAULT_PASSWORD
        rabbitmqctl set_user_tags $RABBITMQ_DEFAULT_USER administrator
        rabbitmqctl set_permissions -p / $RABBITMQ_DEFAULT_USER ".*" ".*" ".*"
    fi
}

hostname=`hostname`
RABBITMQ_NODENAME=${RABBITMQ_NODENAME:-rabbit}

if [ -z "$CLUSTERED" ]; then
    # if not clustered then start it normally as if it a single server
    /usr/sbin/rabbitmq-server &
    rabbitmqctl wait /var/lib/rabbitmq/mnesia/rabbit\@$hostname.pid
    change_default_user
    tail -f /var/log/rabbitmq/*
else
    if [ -z "$RABBITMQ_CLUSTER_WITH" -o "$RABBITMQ_CLUSTER_WITH" = "$hostname" ]; then
        # If clustered, but cluster with is not specified then again start normally, could be the first server tin the cluster
        echo "Running as single server"
        /usr/sbin/rabbitmq-server&
        rabbitmqctl wait /var/lib/rabbitmq/mnesia/rabbit\@$hostname.pid
        tail -f /var/log/rabbitmq/*
    else
        echo "Running as clustered server"
        /usr/sbin/rabbitmq-server&
        rabbitmqctl wait /var/lib/rabbitmq/mnesia/rabbit\@$hostname.pid
        rabbitmqctl stop_app

        echo "Joining cluster $RABBITMQ_CLUSTER_WITH"
        rabbitmqctl join_cluster ${ENABLE_RAM:+--ram} $RABBITMQ_NODENAME@$RABBITMQ_CLUSTER_WITH

        rabbitmqctl start_app

        # Tail to keep the a foreground process active..
        tail -f /var/log/rabbitmq/*
    fi
fi


