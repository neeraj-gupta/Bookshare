package cmpe275Project.Model;

import myvote.controllers.DateParser;

public class Login {
	
	private String studentId;
	private String email;
	private String password;
	private String lastLogin;
	
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin() {
		DateParser dparser = new DateParser();
		this.lastLogin = dparser.getDate();
	}
}