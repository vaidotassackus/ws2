# WS2 – JAX-WS ir XSL transformacijos

Projektui reikia Java 21. Projektą paleisi **IntelliJ IDEA**, o SOAP užklausą patikrinsi **SoapUI**. Atskirai diegti Gradle nereikia.

1. IntelliJ IDEA pasirink **File → Open** ir atidaryk visą `/Users/vaidotas/Projects/ws2` aplanką. Pasirink **Gradle** importavimą ir palauk, kol baigsis sinchronizavimas.
2. Dešinėje atidaryk **Gradle** langą (arba **View → Tool Windows → Gradle**). Išskleisk `student-soap-service → Tasks`.
3. Dukart spausk **verification → check**. Tai paleidžia 7 testus ir sukuria JavaDoc `docs/javadoc/index.html`.
4. Dukart spausk **application → generateDocuments**. XML, HTML ir PDF atsiras `output/` aplanke.
5. Dukart spausk **application → startServer**. Palik šią užduotį veikti. Jos WSDL pasiekiamas adresu <http://localhost:8080/students?wsdl>.
6. Atidaryk **SoapUI** (šiame kompiuteryje jau įdiegta). Kairėje turi būti projektas **WS2 Student SOAP Service**. Jei jo nėra, rinkis **File → Import Project** ir nurodyk [ws2-soapui-project.xml](ws2-soapui-project.xml). Išskleisk **StudentServicePortBinding → getStudent**, dukart spausk **Request 1** ir jo lange paspausk žalią **Submit** rodyklę. Dešinėje turi būti `HTTP/1.1 200 OK` bei `Jonas Jonaitis`, `Mathematics`, `Physics`.
7. Grįžęs į IntelliJ, kol serveris dar veikia, dukart spausk **application → callClient**. Atsakyme turi būti `Student: Jonas Jonaitis` ir `Subjects: 2`. Tada sustabdyk serverį raudonu **Stop** mygtuku.

Jei Gradle užduočių nesimato, Gradle lange spausk **Re-import All Gradle Projects**. Jei IDE prašo JDK, **File → Project Structure → Project** pasirink Java 21 ir Gradle nustatymuose **Gradle JVM** nustatyk Java 21.
