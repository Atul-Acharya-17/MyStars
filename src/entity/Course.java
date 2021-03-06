package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Entity Class Course that stores course details
 *
 * @author Anon
 */
public class Course implements Serializable {
    /**
     * Course code of the course which uniquely identifies the course
     * Name of the course
     * Academic Units of the course
     * Name of the school offering the course
     * List of indexes belonging to the course
     */
    private String courseCode;
    private String courseName;
    private int academicUnits;
    private String schoolName;
    private ArrayList<Object> indexNumberList;

    /**
     * Constructor of Course class
     *
     * @param courseCode    course code of the course
     * @param courseName    name of the course
     * @param academicUnits academic units of the course
     * @param schoolName    school offering the course
     */
    public Course(String courseCode, String courseName, int academicUnits, String schoolName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.academicUnits = academicUnits;
        this.schoolName = schoolName;
        this.indexNumberList = new ArrayList<>();
    }

    /**
     * Dummy constructor of Course class. Initialises the unique attribute to identify the course
     *
     * @param courseCode course code of the course
     */

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Accessor of courseCode
     *
     * @return returns the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Mutator of courseCode
     *
     * @param courseCode course code to be set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
        for (Object index :
                indexNumberList) {
            ((Index) index).setCourseCode(courseCode);
        }
    }

    /**
     * Accessor of courseName
     *
     * @return returns the name of the course
     */

    public String getCourseName() {
        return courseName;
    }

    /**
     * Mutator of courseName
     *
     * @param courseName course name to be set
     */

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Accessor of academicUnits
     *
     * @return returns academic units of the course
     */

    public int getAcademicUnits() {
        return academicUnits;
    }

    /**
     * Mutator of academicUnits
     *
     * @param academicUnits academic units to be set
     */
    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
        for (Object index :
                indexNumberList) {
            ((Index) index).setAcademicUnits(academicUnits);
        }
    }

    /**
     * Accessor of schoolName
     *
     * @return returns name of the school offering the course
     */

    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Mutator of schoolName
     *
     * @param schoolName school name to be set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * Creates a new Index and adds it to indexNumberList
     *
     * @param indexNumber index number of the Index
     * @param vacancy     vacancy of the index
     */

    public void addIndexNumber(int indexNumber, int vacancy) {
        Index index = new Index(indexNumber, vacancy, this.academicUnits, this.courseCode);
        this.indexNumberList.add(index);
    }

    /**
     * Removes an index from indexNumberList
     *
     * @param index Index to remove
     */

    public void removeIndexNumber(Object index) {
        for (Student student : ((Index) index).getStudentsRegistered()
        ) {
            student.removeIndex((Index) index);

        }
        for (Student student : ((Index) index).getWaitingList()
        ) {
            student.removeIndexFromWaitList((Index) index);

        }
        this.indexNumberList.remove(index);
    }

    /**
     * Accessor of indexNumberList
     *
     * @return returns the list of Indexes
     */

    public ArrayList<Object> getIndexNumberList() {
        return indexNumberList;
    }

    /**
     * Mutator of indexNumberList
     *
     * @param indexNumberList index list to be set
     */

    public void setIndexNumberList(ArrayList<Object> indexNumberList) {
        this.indexNumberList = indexNumberList;
    }

    /**
     * Overridden method
     * Checks if two courses have the same course code
     *
     * @param object Object to compare with
     * @return returns a boolean value that determines if the two courses have the same course code
     */

    public boolean equals(Object object) {
        if (object instanceof Course)
            return (courseCode.equals(((Course) object).getCourseCode()));
        return false;
    }

    /**
     * Prints details of a course object
     */
    public void print() {

        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("| Course Code   |                  Course Name               |     AU    |    School    |");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.printf("| %5s %8c %28s %15c %5s %5c %8s %5c\n", courseCode, '|', courseName, '|', academicUnits, '|', schoolName, '|');
        System.out.println("+---------------------------------------------------------------------------------------+");


    }

}
