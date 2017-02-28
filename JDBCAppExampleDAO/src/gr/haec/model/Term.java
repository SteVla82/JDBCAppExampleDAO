package gr.haec.model;

//Term DTO
public class Term {
	// Constants
	public static final int INVALID_ID = -1;

	// Attributes
	private int term_id;
	private String termName;

	// Constructor
	public Term() {
		this.term_id = INVALID_ID;
		this.termName = "";
	}

	// Properties
	public int getTermId() {
		return term_id;
	}

	public void setTermId(int id) {
		this.term_id = id;
	}

	public String gettermName() {
		return termName;
	}

	public void settermName(String termName) {
		this.termName = termName;
	}

	@Override
	public String toString() {
		return "Term [ id =  " + term_id + ", Term Name = " + termName + " ]";
	}
}
