#!/bin/zsh
cd "${0:A:h}" || exit 1
./gradlew run --args=server
