package gr.haec.model;

public class Tag extends Term {

	private String taxonomy;
	private String description;

	public Tag() {

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
		return "Tag [ term_id = " + term_id + " , Term Name = " + termName + " , taxonomy = " + taxonomy
				+ " , description = " + description + " ]";
	}
}
