package models;

public class User {
	private int id;
    private String name;
    private String pwd;
    private String email;
    private String mobile;

    

    // Constructeurs
    public User(String name, String pwd,String email,String mobile) {
    	super();
        this.name= name;
        this.pwd = pwd;
        this.email = email;
        this.mobile = mobile;
    }
    public User(int id, String name, String pwd,String email,String mobile) {
		super();
		this.id = id;
	    this.name= name;
        this.pwd = pwd;
        this.email = email;
        this.mobile = mobile;
	}
    
    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +", mobile='" + mobile + '\'' +
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

    public String getPwd() {
        return pwd;
    }
    public String getMobile() {
        return mobile;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
