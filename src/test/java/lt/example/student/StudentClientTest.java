package lt.example.student;

import jakarta.xml.ws.Endpoint;
import org.junit.jupiter.api.Test;

import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentClientTest {
    @Test
    void generatedClientCallsPublishedService() throws Exception {
        int port;
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
        }
        String url = "http://localhost:" + port + "/students";
        Endpoint endpoint = Endpoint.publish(url, new StudentServiceImpl());
        try {
            var student = new StudentClient().fetchStudent(1, url);
            assertEquals("Jonas Jonaitis", student.getName());
            assertEquals(2, student.getSubjects().getSubject().size());
        } finally {
            endpoint.stop();
        }
    }
}
