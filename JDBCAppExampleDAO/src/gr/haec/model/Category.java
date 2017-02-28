package gr.haec.model;

public class Category {

	public static final int DEFAULT_ID = -1;

	private int id;
	private String name;
	private String taxonomy;
	private String description;
    
	public Category() {
		this.id = DEFAULT_ID;
		this.name = "";
		this.taxonomy = "";
		this.description = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	    this.name = name;	
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
		return "Category [ term_id = " + id + " , Term Name = " + name + " , taxonomy = " + taxonomy + " , description = " + description + " ]";
	}
}