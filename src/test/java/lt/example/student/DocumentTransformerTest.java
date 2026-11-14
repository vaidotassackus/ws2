package lt.example.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentTransformerTest {
    @TempDir
    Path directory;

    @Test
    void transformsXmlToHtmlAndPdf() throws Exception {
        Path xml = directory.resolve("student.xml");
        Path html = directory.resolve("student.html");
        Path pdf = directory.resolve("student.pdf");
        Files.writeString(xml, """
                <student><id>1</id><name>Jonas</name><age>21</age>
                <averageGrade>8.5</averageGrade><active>true</active><initial>J</initial>
                <subjects><subject><name>Math</name><credits>6</credits></subject></subjects>
                </student>
                """);

        DocumentTransformer transformer = new DocumentTransformer();
        transformer.toHtml(xml, html);
        transformer.toPdf(xml, pdf);

        assertTrue(Files.readString(html).contains("Math"));
        assertTrue(Files.readString(html).contains("Jonas"));
        assertEquals("%PDF", new String(Files.readAllBytes(pdf), 0, 4,
                StandardCharsets.US_ASCII));
    }
}
