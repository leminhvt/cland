package spring.model.bean;

import javax.validation.constraints.NotBlank;

public class User {
	private int id;
	@NotBlank
	private String username;
	private String fullname;
	private String password;
	private Role role;

	public User(int id, String username, String fullname, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		super();
	}

	public User(int id) {
		this.id = id;
	}
	
	

}
