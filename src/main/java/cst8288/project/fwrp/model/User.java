package cst8288.project.fwrp.model;

/**
 * User, the base class for the application. <br>
 * 3 types of Users: 1. Retailer, 2. Charitable Organization, 3. Consumer
 */
public class User {

	private final Long id;
	private final String name;
	private final String email;
	private final String phone;
	private final String password;
	private final UserType type;


	private User(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.email = builder.email;
		this.phone = builder.phone;
		this.password = builder.password;
		this.type = builder.type;
	}

	public static class Builder {
		private Long id;
		private String name;
		private String email;
		private String phone;
		private String password;
		private UserType type;

		public Builder(String email, String password) {
			this.email = email;
			this.password = password;
		}

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setPhone(String phone) {
			// TODO: phone validation needed
			this.phone = phone;
			return this;
		}

		public Builder setUserType(UserType type) {
			this.type = type;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
	
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ ", type=" + type + "]";
	}
	
	
}
