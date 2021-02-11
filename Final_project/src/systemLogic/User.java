package systemLogic;

import java.io.Serializable;

public abstract class User implements Comparable<User>, Serializable {

	private static final long serialVersionUID = 14L;
	
	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private boolean loginned = false;
	
	{
		Database.counterUserId++;
		id = Database.counterUserId;
	}
	
	public User() {}
	public User(String name, String surname) {
		this.name = name.strip();
		this.surname = surname.strip();
		setUsername(this.name, this.surname);
		this.password = username;
	}
	
	
	// Checking password and changing loginned status
	public boolean login(String password) {
		if (this.password.equals(password)) {
			loginned = true;
			Database.logFiles.add(new LogFile(LogType.USER_LOGINNED));
			return true;
		}
		return false;
	}
	
	// Log out of account
	public boolean logout() {
		loginned = false;
		Database.logFiles.add(new LogFile(LogType.USER_LOGOUT));
		return false;
	}
	
	// Checking loginned variable
	public boolean isLoginned() {
		return loginned;
	}

	// Getting user id
	public int getId() {
		return id;
	}
	
	// Setting new user id
	public void setId(int id) {
		this.id = id;
	}
	
	// Getting full name
	public String getFullName() {
		return name + " " + surname;
	}
	
	// Setting new full name
	public void setFullName(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	// Getting username
	public String getUsername() {
		return username;
	}
	
	// Transforming full name to username
	public void setUsername(String name, String surname) {
		this.username = name.toLowerCase() + "_" + surname.toLowerCase();
	}
	
	// Getting user password
	public String getPassword() {
		return password;
	}
	
	// Setting new password
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Comparing user to current by full name
	public int compareTo(User user) {
		return username.compareTo(user.username);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (loginned ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (loginned != other.loginned)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + ", loginned=" + loginned + "]";
	}
	
}
