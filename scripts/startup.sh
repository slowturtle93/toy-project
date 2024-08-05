#!/bin/sh

DEPLOY_DIR=/home/toy/deploy
timestamp=`date +%Y%m%d%H%M%S`

## jvm heap memory min 1024 max 1536
## -XX:+UseParallelGC >> 병렬 GC를 사용하도록 설정
## -Djava.net.preferIPv4Stack=true >> JVM이 IPv4를 우선적으로 사용하도록
java -jar -Xms1024m -Xmx1536m -XX:+UseParallelGC -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -Djava.net.preferIPv4Stack=true $DEPLOY_DIR/$1
