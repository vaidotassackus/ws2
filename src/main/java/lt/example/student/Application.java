package lt.example.student;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.ws.Endpoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;

/** Command-line entry point for the server and document generation. */
public final class Application {
    /** Address used by the SOAP service and the generated client. */
    public static final String SERVICE_URL = "http://localhost:8080/students";

    private Application() {
    }

    /**
     * Runs either the server or the XML, HTML and PDF demonstration.
     *
     * @param args one command: {@code server}, {@code transform} or {@code client}
     * @throws Exception if service startup or document generation fails
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            usage();
            return;
        }
        switch (args[0]) {
            case "server" -> runServer();
            case "transform" -> generateDocuments(Path.of("output"));
            case "client" -> runClient();
            default -> usage();
        }
    }

    /**
     * Writes one sample student's XML, HTML and PDF documents.
     *
     * @param directory output directory
     * @throws Exception if an output file cannot be generated
     */
    public static void generateDocuments(Path directory) throws Exception {
        Files.createDirectories(directory);
        Path xml = directory.resolve("student.xml");
        Path html = directory.resolve("student.html");
        Path pdf = directory.resolve("student.pdf");
        writeStudentXml(new StudentServiceImpl().getStudent(1), xml);
        DocumentTransformer transformer = new DocumentTransformer();
        transformer.toHtml(xml, html);
        transformer.toPdf(xml, pdf);
        System.out.println("Created: " + xml + ", " + html + ", " + pdf);
    }

    private static void writeStudentXml(Student student, Path xml)
            throws JAXBException, IOException {
        Marshaller marshaller = JAXBContext.newInstance(Student.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try (var output = Files.newOutputStream(xml)) {
            marshaller.marshal(student, output);
        }
    }

    private static void runServer() throws InterruptedException {
        Endpoint endpoint = Endpoint.publish(SERVICE_URL, new StudentServiceImpl());
        Runtime.getRuntime().addShutdownHook(new Thread(endpoint::stop));
        System.out.println("SOAP service: " + SERVICE_URL);
        System.out.println("WSDL: " + SERVICE_URL + "?wsdl");
        System.out.println("Stop the process to stop the server.");
        try {
            new CountDownLatch(1).await();
        } finally {
            endpoint.stop();
        }
    }

    private static void runClient() {
        var student = new StudentClient().fetchStudent(1, SERVICE_URL);
        System.out.println("Student: " + student.getName());
        System.out.println("Subjects: " + student.getSubjects().getSubject().size());
    }

    private static void usage() {
        System.out.println("Usage: server | transform | client");
    }
}
