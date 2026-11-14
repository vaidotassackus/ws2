# Web services – Assessment Task 2: paaiškinimas lietuviškai

## Nuo ko pradėti

Šis projektas padarytas taip, kad nereikėtų mokėti Java ar įdiegti Maven atskirai. Šiame kompiuteryje jau yra Java 21. Visas toliau pateiktas komandas reikia vykdyti **Terminal** programoje, projekto aplanke. Pirmą kartą reikės interneto: `mvnw` parsisiųs Maven ir projekto bibliotekas.

1. Atidaryk **Terminal** (`Command + Space`, įrašyk `Terminal`, spausk `Enter`).
2. Įvesk:

   ```bash
   cd /Users/vaidotas/Projects/ws2
   java -version
   ./mvnw clean verify
   ```

   Jei paskutinė komanda baigiasi `BUILD SUCCESS`, projektas sukompiliuotas, testai praėjo, JavaDoc sukurtas. Jei `java -version` neveikia, reikia įdiegti **JDK 21**, pavyzdžiui, iš [Eclipse Temurin](https://adoptium.net/temurin/releases/?version=21).

3. Sugeneruok XML, HTML ir PDF:

   ```bash
   ./mvnw -q exec:java -Dexec.args=transform
   open output/student.html
   open output/student.pdf
   ```

   Sukurti failai: [student.xml](output/student.xml), [student.html](output/student.html), [student.pdf](output/student.pdf).

4. Paleisk SOAP serverį. Šis Terminal langas turi likti atidarytas:

   ```bash
   ./mvnw -q exec:java -Dexec.args=server
   ```

   Ekrane turi pasirodyti `WSDL: http://localhost:8080/students?wsdl`. Šį adresą galima atidaryti naršyklėje. **Norint sustabdyti serverį, šiame lange paspausk `Enter`.**

5. Atidaryk **antrą** Terminal langą ir paleisk sugeneruotą SOAP klientą:

   ```bash
   cd /Users/vaidotas/Projects/ws2
   ./mvnw -q exec:java -Dexec.args=client
   ```

   Turi būti parašyta `Student: Jonas Jonaitis` ir `Subjects: 2`.

macOS komandas taip pat galima paleisti dukart paspaudus [create-documents.command](create-documents.command), [start-server.command](start-server.command) arba [run-client.command](run-client.command). Terminal būdas aukščiau aiškiau parodo klaidas, jei kas nors nepavyksta.

## Kaip patikrinti su SoapUI arba Postman

### SoapUI

1. Kol serveris veikia, SoapUI pasirink **File → New SOAP Project**.
2. Laukelyje **Initial WSDL** įrašyk `http://localhost:8080/students?wsdl`.
3. Atidaryk sugeneruotą `getStudent` užklausą. Į `id` įrašyk `1` ir spausk žalią siuntimo rodyklę.
4. Atsakyme ieškok `Jonas Jonaitis`, `Mathematics` ir `Physics`. Pakeitęs `id` į `999`, gausi tuščią rezultatą, nes pavyzdyje yra tik vienas studentas.

### Postman

1. Sukurk **POST** užklausą adresu `http://localhost:8080/students`.
2. Į **Headers** įrašyk `Content-Type` = `text/xml; charset=utf-8`.
3. **Body → raw → XML** laukelyje įklijuok visą [soap-request.xml](soap-request.xml) turinį.
4. Spausk **Send**. Atsakymo `Body` bus SOAP XML su studentu ir jo dviem dalykais.

Ši užklausa patikrinta ir Postman programoje: gautas `200 OK` SOAP XML atsakymas su `Jonas Jonaitis` ir dalykų sąrašu. Tą patį galima pakartoti Terminal komanda:

```bash
curl -H 'Content-Type: text/xml; charset=utf-8' \
  --data-binary @soap-request.xml http://localhost:8080/students
```

## Kas ir kodėl sukurta

| Užduoties dalis | Failai | Paaiškinimas |
| --- | --- | --- |
| 1.1 XML → HTML | `src/main/resources/xsl/student-html.xsl` | XSLT šablonas nurodo, kaip studento XML laukus ir dalykų sąrašą parodyti HTML puslapyje. |
| 1.1 XML → PDF | `src/main/resources/xsl/student-pdf.xsl` | Šis XSLT iš XML sukuria XSL-FO puslapio aprašą. Apache FOP iš XSL-FO pagamina tikrą PDF. |
| 1.1 Java transformacija | `DocumentTransformer.java`, `Application.java` | Java paleidžia abu XSLT ir išsaugo failus `output` aplanke. JAXB iš studento objekto pirmiausia sukuria XML. |
| 1.2 duomenų klasės | `Student.java`, `Subject.java` | `Student` turi `String`, `int`, `float`, `boolean`, `char` ir `List<Subject>` laukus. JAXB anotacijos nusako XML atvaizdavimą. |
| 1.2 SOAP paslauga | `StudentService.java`, `StudentServiceImpl.java` | Interfeisas yra paslaugos sutartis, implementacija grąžina pavyzdinį studentą pagal ID. JAX-WS anotacijos automatiškai sukuria WSDL. |
| 1.2 adresas ir paleidimas | `Application.java` | `Endpoint.publish` paskelbia paslaugą `http://localhost:8080/students`. |
| 1.2 `wsimport` klientas | `pom.xml`, `src/main/resources/wsdl/`, `StudentClient.java` | Maven automatiškai vykdo `wsimport` iš išsaugoto WSDL ir XSD. Sugeneruotos klasės atsiranda `target/generated-sources/wsimport`, o `StudentClient` jas naudoja tikrai SOAP užklausai. |

WSDL ir XSD failai buvo gauti iš veikiančios paslaugos. WSDL XSD nuoroda pakeista į vietinę, kad `wsimport` veiktų ir tada, kai serveris išjungtas. Patys sugeneruoti Java failai neįrašyti į projektą: jie kiekvieną kartą atkuriami per Maven.

`char` tipo `initial` JAXB į XML įrašo kaip vieno simbolio tekstą per `getInitialText()` ir `setInitialText()`. Kiti laukai į XML įrašomi tiesiogiai. `id=1` grąžina vieną pavyzdinį studentą; kitam ID paslauga grąžina `null`. Duomenų bazės užduotis nereikalauja, todėl jos nėra.

## Ką reiškia pagrindiniai žodžiai, jei moki PHP

- `pom.xml` panašus į PHP `composer.json`: jame nurodytos bibliotekos ir projekto kūrimo veiksmai.
- `Student` ir `Subject` yra paprastos duomenų klasės, panašios į PHP objektus.
- `@WebService`, `@WebMethod`, `@XmlElement` yra anotacijos, panašios į PHP atributus. Jas skaito JAX-WS ir JAXB bibliotekos.
- SOAP yra HTTP užklausa ir atsakymas XML formatu. WSDL aprašo, kokias operacijas ir laukus paslauga priima.
- `target/` yra automatiškai sugeneruoti Java, testų ir dokumentacijos failai. Jų rankomis redaguoti nereikia.
- `src/main/` yra programos kodas ir XSL, `src/test/` yra testai.

## Testai, JavaDoc ir tvarka

Komanda `./mvnw clean verify` vykdo 7 testus iš 6 testų klasių. Jie tikrina visų ranka parašytų vykdomųjų klasių darbą: duomenų laukus ir XML atkūrimą, paslaugą, HTML/PDF transformacijas, programos failų generavimą ir tikrą sugeneruoto kliento SOAP iškvietimą. Testų ataskaitos yra `target/surefire-reports/`. JavaDoc atidaromas per `open target/reports/apidocs/index.html`.

Klasės ir metodai parašyti pagal Java vardų taisykles. Atsakomybės atskirtos: duomenų klasės saugo duomenis, `StudentService` aprašo mažą sąsają, implementacija teikia duomenis, transformavimo klasė kuria dokumentus, klientas kviečia paslaugą, o `Application` viską paleidžia. Tai yra paprastas SOLID principų pritaikymas šios apimties projektui.

## Naudotos oficialios nuorodos

- [Eclipse Metro JAX-WS Maven įskiepis ir `wsimport`](https://eclipse-ee4j.github.io/metro-jax-ws/jaxws-maven-plugin/)
- [Apache FOP 2.11 ir XSL-FO](https://xmlgraphics.apache.org/fop/2.11/)
- [SoapUI SOAP projekto kūrimas](https://www.soapui.org/docs/getting-started/projects/working-with-soapui-projects/)
