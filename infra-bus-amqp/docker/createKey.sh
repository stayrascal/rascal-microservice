#!/bin/bash

cd $JAVA_HOME

$JAVA_HOME/bin/keytool -genkeypair -alias infra-config-server-key \
       -keyalg RSA -keysize 512 -sigalg SHA256withRSA \
       -dname 'CN=Config Server,OU=Spring Cloud,O=Baeldung' \
       -keypass my-k34-s3cr3t -keystore infra-config-server.jks \
       -storepass my-s70r3-s3cr3t \
       -validity 365