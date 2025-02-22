package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Spencer Kelly
 * @version 2025-02-22
 */
public class CAS extends Professor {

    // your code here
    
    private String term;
    
    public CAS(String last, String first, String department, String term) {
	super(last, first, department);
	this.term = term;
    }
    
    public String getTerm() {
	return term;
    }
    
    @Override
    public String toString() {
	return (super.toString() + '\n' + "Term: " + this.term);
    }

}
