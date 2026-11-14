package lt.example.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentServiceImplTest {
    private final StudentService service = new StudentServiceImpl();

    @Test
    void returnsStudentWithDependentSubjects() {
        Student student = service.getStudent(1);

        assertEquals("Jonas Jonaitis", student.getName());
        assertEquals(2, student.getSubjects().size());
    }

    @Test
    void returnsNullForUnknownId() {
        assertNull(service.getStudent(999));
    }
}
