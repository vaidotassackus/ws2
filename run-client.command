#!/bin/zsh
cd "${0:A:h}" || exit 1
./mvnw -q compile exec:java -Dexec.args=client
