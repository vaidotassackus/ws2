# WS2 – JAX-WS ir XSL transformacijos

Reikia Java 21. Gradle atskirai diegti nereikia: `gradlew` jį parsiunčia pirmo paleidimo metu.

```bash
./gradlew clean check
./gradlew run --args=transform
./gradlew run --args=server
```

`check` paleidžia testus ir sukuria JavaDoc `docs/javadoc/`. `transform` sukuria XML, HTML ir PDF `output/` aplanke. Serverio WSDL: <http://localhost:8080/students?wsdl>. Kol serveris veikia, antrame Terminal lange paleisk klientą:

```bash
./gradlew run --args=client
```

SOAP užklausos pavyzdys yra [soap-request.xml](soap-request.xml). `build.gradle` užduotis `generateSoapClient` kiekvieną kartą sugeneruoja klientą su Metro `wsimport` iš vietinio WSDL.
