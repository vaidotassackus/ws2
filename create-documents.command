#!/bin/zsh
cd "${0:A:h}" || exit 1
./mvnw -q compile exec:java -Dexec.args=transform || exit 1
open output/student.html
open output/student.pdf
