package lt.example.student;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

/** Public SOAP contract for reading student data. */
@WebService(name = "StudentService", targetNamespace = "https://example.lt/student")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface StudentService {
    /**
     * Finds a student by identifier.
     *
     * @param id student identifier
     * @return student, or {@code null} when the identifier is unknown
     */
    @WebMethod
    Student getStudent(@WebParam(name = "id") int id);
}
