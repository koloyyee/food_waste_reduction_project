package cst8288.project.fwrp.model;

/**
 * User, the base class for the application. <br>
 * 3 types of Users: 1. Retailer, 2. Charitable Organization, 3. Consumer
 * 
 * 
 * the Subscriber in Observer pattern
 */
public class User {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private UserType type;
	private CommMethodType commMethod;
	private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public CommMethodType getCommMethod() {
		return commMethod;
	}

	public void setCommMethod(CommMethodType commMethod) {
		this.commMethod = commMethod;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ ", type=" + type + ", commMethod=" + commMethod + ", location=" + location + "]";
	}

}
