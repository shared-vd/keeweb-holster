#!/bin/bash

DIR="$(pwd)"
echo "Current directory: $DIR"

export MODE=service
export APP_NAME=keewebholster
export PID_FOLDER=${DIR}
export LOG_FOLDER=${DIR}/logs
export LOG_FILENAME=application.log
rm -f $LOG_FOLDER/*

cp target/keewebholster.war .
./keewebholster.war stop
