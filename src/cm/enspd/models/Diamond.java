package cm.enspd.models;

public class Diamond {
	//property
	protected int id;
	protected String designation;
	protected int weight;
	protected int price;
	
	//default constructor
	public Diamond() {
		super();
	}

	//constructor with parameter
	public Diamond(int id, String designation, int weight, int price) {
		super();
		this.id = id;
		this.designation = designation;
		this.weight = weight;
		this.price = price;
	}

	//constructor without id parameter
	public Diamond(String designation, int weight, int price) {
		super();
		this.designation = designation;
		this.weight = weight;
		this.price = price;
	}

	//getters and setters of all the diamond attribut
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
	
	
	
}
