package gr.haec.model;

public class Category extends Term{

	private String taxonomy;
	private String description;
    
	public Category() {
		this.taxonomy = "";
		this.description = "";
	}

	public String getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [ term_id = " + term_id + " , Term Name = " + termName + " , taxonomy = " + taxonomy + " , description = " + description + " ]";
	}
}