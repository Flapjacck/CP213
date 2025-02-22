package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Spencer Kelly
 * @version 2025-02-22
 */
public class IA extends Student {

    // your code here
    
    private String course;
    
    public IA(String last, String first, String department, String course) {
	super(last, first, department);
	this.course = course;
    }
    
    public String getCourse() {
	return course;
    }
    
    @Override
    public String toString() {
	return (super.toString() + '\n' + "Course: " + this.course);
    }

}
