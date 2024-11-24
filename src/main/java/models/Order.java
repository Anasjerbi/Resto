package models;

public class Order {
	private int id;
    private String name;
    private String email;
    private String description;

    // Constructeurs
    public Order(String name, String email, String description) {
    	super();
        this.name= name;
        this.email = email;
        this.description = description;
    }
    public Order(int id, String name, String email, String description) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.description = description;
	}
    
    @Override
    public String toString() {
        return "Order{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // Getters et Setters
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
