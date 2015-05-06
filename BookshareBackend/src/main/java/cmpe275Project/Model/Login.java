package cmpe275Project.Model;

import cmpe275Project.Controller.DateParser;

public class Login {
	
	private String studentId;
	private String email;
	private String password;
	private String lastLogin;
	
	//dummy constructor, dnt remove it!
	public Login()
	{
		
	}
	
	public Login(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
	
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
