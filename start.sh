#!/bin/bash

DIR="$(pwd)"
echo "Current directory: $DIR"

PORT=7070
KDBX_DIR=$DIR
KDBX_NAME=Secrets

export MODE=service
export APP_NAME=keewebholster
export PID_FOLDER=${DIR}
export LOG_FOLDER=${DIR}/logs
mkdir -p $LOG_FOLDER
export LOG_FILENAME=application.log
rm -f $LOG_FOLDER/*

export JAVA_OPTS="-Dserver.port=$PORT -Dkdbx.dir=$KDBX_DIR -Dkdbx.name=$KDBX_NAME"
export DEBUG=true

echo "Listening on port $PORT"
cp target/keewebholster.war .
./keewebholster.war start
