package cmpe275Project.DAO;

import cmpe275Project.Model.Student;

public interface StudentDao {
	public void createStudent(Student student);
	public Student updateStudent(Student student, Integer s_id);	
}
