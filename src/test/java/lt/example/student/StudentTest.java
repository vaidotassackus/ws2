package lt.example.student;

import jakarta.xml.bind.JAXBContext;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentTest {
    @Test
    void preservesAllFieldTypesAndSubjectsInXml() throws Exception {
        Student original = new Student(1, "Jonas", 21, 8.5f, true, 'J',
                List.of(new Subject("Math", 6)));
        JAXBContext context = JAXBContext.newInstance(Student.class);
        StringWriter xml = new StringWriter();
        context.createMarshaller().marshal(original, xml);
        Student restored = (Student) context.createUnmarshaller()
                .unmarshal(new StringReader(xml.toString()));

        assertEquals(1, restored.getId());
        assertEquals("Jonas", restored.getName());
        assertEquals(21, restored.getAge());
        assertEquals(8.5f, restored.getAverageGrade());
        assertTrue(restored.isActive());
        assertEquals('J', restored.getInitial());
        assertEquals("Math", restored.getSubjects().getFirst().getName());
        assertEquals(6, restored.getSubjects().getFirst().getCredits());
    }
}
