# WS2 – JAX-WS ir XSL transformacijos

Projektui reikia Java 21. Visi žemiau nurodyti veiksmai atliekami **IntelliJ IDEA**; atskirai diegti Gradle nereikia.

1. IntelliJ IDEA pasirink **File → Open** ir atidaryk visą `/Users/vaidotas/Projects/ws2` aplanką. Pasirink **Gradle** importavimą ir palauk, kol baigsis sinchronizavimas.
2. Dešinėje atidaryk **Gradle** langą (arba **View → Tool Windows → Gradle**). Išskleisk `student-soap-service → Tasks`.
3. Dukart spausk **verification → check**. Tai paleidžia 7 testus ir sukuria JavaDoc `docs/javadoc/index.html`.
4. Dukart spausk **application → generateDocuments**. XML, HTML ir PDF atsiras `output/` aplanke.
5. Dukart spausk **application → startServer**. Kol serveris veikia, jo WSDL pasiekiamas adresu <http://localhost:8080/students?wsdl>.
6. Atidaryk [soap-requests.http](soap-requests.http) ir paspausk žalią paleidimo trikampį prie `GET` bei `POST` užklausų. Atsakymus matysi IntelliJ lange. Taip pat gali paleisti **application → callClient** ir pamatyti Java kliento atsakymą. Serverį sustabdyk raudonu **Stop** mygtuku.

Jei Gradle užduočių nesimato, Gradle lange spausk **Re-import All Gradle Projects**. Jei IDE prašo JDK, **File → Project Structure → Project** pasirink Java 21 ir Gradle nustatymuose **Gradle JVM** nustatyk Java 21.
