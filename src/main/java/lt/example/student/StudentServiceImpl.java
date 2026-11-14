package lt.example.student;

import jakarta.jws.WebService;

import java.util.List;

/** Small in-memory implementation of the SOAP contract. */
@WebService(endpointInterface = "lt.example.student.StudentService",
        serviceName = "StudentService", portName = "StudentServicePort",
        targetNamespace = "https://example.lt/student")
public class StudentServiceImpl implements StudentService {
    /** {@inheritDoc} */
    @Override
    public Student getStudent(int id) {
        if (id != 1) {
            return null;
        }
        return new Student(1, "Jonas Jonaitis", 21, 8.5f, true, 'J',
                List.of(new Subject("Mathematics", 6), new Subject("Physics", 5)));
    }
}
