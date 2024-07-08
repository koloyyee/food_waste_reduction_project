package model;


/**
 * Builder Pattern
 * */
public interface UserBuilder {
	UserBuilder setId(Long id );
	UserBuilder setName(String name );
	UserBuilder setEmail(String email );
	UserBuilder setPhone(String phone );
	UserBuilder setUserType(UserType type);
	User build();
}

