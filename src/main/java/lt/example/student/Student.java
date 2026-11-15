package lt.example.student;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.List;

/** Student data shared by the SOAP service and document transformations. */
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    private int id;
    private String name;
    private int age;
    private float averageGrade;
    private boolean active;

    @XmlTransient
    private char initial;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private List<Subject> subjects = new ArrayList<>();

    /** Required by JAXB when converting objects to and from XML. */
    public Student() {
    }

    /**
     * Creates a student.
     *
     * @param id student identifier
     * @param name full name
     * @param age age in years
     * @param averageGrade average grade
     * @param active whether the student is currently active
     * @param initial first letter of the given name
     * @param subjects subjects studied by the student
     */
    public Student(int id, String name, int age, float averageGrade,
                   boolean active, char initial, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
        this.active = active;
        this.initial = initial;
        this.subjects = new ArrayList<>(subjects);
    }

    /**
     * Returns the student identifier.
     *
     * @return student identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Changes the student identifier.
     *
     * @param id student identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the full name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the full name.
     *
     * @param name full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age in years.
     *
     * @return age in years
     */
    public int getAge() {
        return age;
    }

    /**
     * Changes the age in years.
     *
     * @param age age in years
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the average grade.
     *
     * @return average grade
     */
    public float getAverageGrade() {
        return averageGrade;
    }

    /**
     * Changes the average grade.
     *
     * @param averageGrade average grade
     */
    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    /**
     * Reports whether the student is active.
     *
     * @return whether the student is currently active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Changes the active state.
     *
     * @param active whether the student is currently active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the first letter of the given name.
     *
     * @return first letter of the given name
     */
    public char getInitial() {
        return initial;
    }

    /**
     * Changes the first letter of the given name.
     *
     * @param initial first letter of the given name
     */
    public void setInitial(char initial) {
        this.initial = initial;
    }

    /**
     * Returns the Java {@code char} as one XML text character.
     *
     * @return initial as text
     */
    @XmlElement(name = "initial")
    public String getInitialText() {
        return String.valueOf(initial);
    }

    /**
     * Changes the initial using one XML text character.
     *
     * @param initialText one-character initial from XML
     */
    public void setInitialText(String initialText) {
        this.initial = initialText == null || initialText.isEmpty() ? '\0' : initialText.charAt(0);
    }

    /**
     * Returns the subjects studied by the student.
     *
     * @return subjects studied by the student
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Changes the subjects studied by the student.
     *
     * @param subjects subjects studied by the student
     */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = new ArrayList<>(subjects);
    }
}
