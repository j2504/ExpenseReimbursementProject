package com.revature.expense.model;

public class Users {
	private int user_id;
	private String user_role;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String username;
	
	public Users() {
		super();
	}
	
	
	public Users(String email, String firstName, String lastName, String password, String username) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.username = username;
	}


	public Users(int user_id, String user_role, String email, String firstName, String lastName, String password,
			String username) {
		super();
		this.user_id = user_id;
		this.user_role = user_role;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.username = username;
	}


	public Users(String user_role, String email, String firstName, String lastName, String password,
			String username) {
		super();
		this.user_role = user_role;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.username = username;
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		
		return "Employee_Id=" + user_id +"\r\n Role=" + user_role + "\n Email=" + email + "\n First Name="
				+ firstName + "\n Last Name=" + lastName + "\n Password=" + password + "\n Username=" + username;
		
	}
	
	
	
}
