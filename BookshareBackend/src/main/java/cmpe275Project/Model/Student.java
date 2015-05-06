package cmpe275Project.Model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonView;

public class Student {
	
	private static Integer count = 1;
	@JsonView(Integer.class)
	private Integer studentId;
	@JsonView(String.class)
	private String firstName;
	@JsonView(String.class)
	private String lastName;
	@Id
	@JsonView(String.class)
	private String email;
	@JsonView(String.class)
	@NotNull
	private String password;
	@JsonView(String.class)
	private String phone;
	@JsonView(String.class)
	private String university;
	
	
	Student(){
	}
	
	public Student(String firstName,String lastName,String email, String phone, String university){
		super();
		this.studentId = count++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.university = university;
	}
	
	public static Integer getNewId(){
		return ++count;
	}
	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	
	public String getUniversity() {
		return university;
	}
	
	public void setUniversity(String university) {
		this.university = university;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}	
}
