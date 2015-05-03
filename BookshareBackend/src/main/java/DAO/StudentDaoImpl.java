package DAO;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import config.SpringMongoConfig;
import Model.Student;
import MyExceptions.Exceptions;

public class StudentDaoImpl implements StudentDao{
	private static MongoOperations mongoOps;
	private static final String Student_COLLECTION = "students";  
	
	public StudentDaoImpl(){
		if(mongoOps == null){
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	    	mongoOps = (MongoOperations)context.getBean("mongoTemplate");
		}
	}

	@Override
	public void createStudent(Student student) {
		// TODO Auto-generated method stub
		while(isExistingStudent(student.getStudentId())){
			student.setStudentId(student.getStudentId() + 1);
		}
		
		if(isDuplicateEmail(student.getEmail())){
			throw new Exceptions.DuplicateStudentEmailException();
		}
		
		System.out.println("mongoOps " + mongoOps);
		mongoOps.insert(student, Student_COLLECTION);
	}

	@Override
	public Student updateStudent(Student student, Integer s_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Helper Validation Functions
	private boolean isExistingStudent(int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isDuplicateEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}
}
