package lt.example.student;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/** Converts one student XML file to HTML and PDF using XSL stylesheets. */
public class DocumentTransformer {
    /**
     * Converts XML to HTML with the bundled XSLT stylesheet.
     *
     * @param xml input XML file
     * @param html output HTML file
     * @throws IOException if a file cannot be read or written
     * @throws TransformerException if XSLT fails
     */
    public void toHtml(Path xml, Path html) throws IOException, TransformerException {
        try (InputStream stylesheet = stylesheet("student-html.xsl");
             OutputStream output = Files.newOutputStream(html)) {
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer(new StreamSource(stylesheet));
            transformer.transform(new StreamSource(xml.toFile()), new StreamResult(output));
        }
    }

    /**
     * Converts XML to XSL-FO with the bundled stylesheet, then renders it as PDF.
     *
     * @param xml input XML file
     * @param pdf output PDF file
     * @throws IOException if a file cannot be read or written
     * @throws TransformerException if XSLT fails
     * @throws FOPException if PDF rendering fails
     */
    public void toPdf(Path xml, Path pdf)
            throws IOException, TransformerException, FOPException {
        FopFactory factory = FopFactory.newInstance(pdf.toAbsolutePath().getParent().toUri());
        try (InputStream stylesheet = stylesheet("student-pdf.xsl");
             OutputStream output = Files.newOutputStream(pdf)) {
            Fop fop = factory.newFop(MimeConstants.MIME_PDF, output);
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer(new StreamSource(stylesheet));
            transformer.transform(new StreamSource(xml.toFile()),
                    new SAXResult(fop.getDefaultHandler()));
        }
    }

    private InputStream stylesheet(String name) throws IOException {
        InputStream stream = getClass().getResourceAsStream("/xsl/" + name);
        if (stream == null) {
            throw new IOException("Missing stylesheet: " + name);
        }
        return stream;
    }
}
