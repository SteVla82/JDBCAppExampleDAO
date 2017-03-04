package gr.haec.model;

//Term DTO
public class Term {
	// Constants
	public static final int INVALID_ID = -1;

	// Attributes
	private int term_id;
	private String termName;
	private String termSlug;
	private int isDeleted;

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

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}
	
	public String getTermSlug() {
		return termSlug;
	}

	public void setTermSlug(String termSlug) {
		this.termSlug = termSlug;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Term [ id =  " + term_id + ", Term Name = " + termName + ", Term Slug = " + termSlug + " , is deleted =  " + isDeleted + " ]";
	}
}