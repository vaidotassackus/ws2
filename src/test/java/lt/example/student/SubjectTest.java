package lt.example.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubjectTest {
    @Test
    void storesNameAndCredits() {
        Subject subject = new Subject();
        subject.setName("Physics");
        subject.setCredits(5);

        assertEquals("Physics", subject.getName());
        assertEquals(5, subject.getCredits());
    }
}
