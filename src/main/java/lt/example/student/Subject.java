package lt.example.student;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/** A subject studied by a student. */
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    private String name;
    private int credits;

    /** Required by JAXB when converting objects to and from XML. */
    public Subject() {
    }

    /**
     * Creates a subject.
     *
     * @param name subject name
     * @param credits number of credits
     */
    public Subject(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    /**
     * Returns the subject name.
     *
     * @return subject name
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the subject name.
     *
     * @param name subject name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of credits.
     *
     * @return number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Changes the number of credits.
     *
     * @param credits number of credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
}
