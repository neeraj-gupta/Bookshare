package cmpe275Project.Model;

public class Student {
	private static Integer count = 1;
	private Integer studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
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
}