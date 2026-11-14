package lt.example.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {
    @TempDir
    Path directory;

    @Test
    void createsDemonstrationFiles() throws Exception {
        Application.generateDocuments(directory);

        assertTrue(Files.readString(directory.resolve("student.xml"))
                .contains("Jonas Jonaitis"));
        assertTrue(Files.readString(directory.resolve("student.html"))
                .contains("Mathematics"));
        assertTrue(Files.size(directory.resolve("student.pdf")) > 1000);
    }
}
