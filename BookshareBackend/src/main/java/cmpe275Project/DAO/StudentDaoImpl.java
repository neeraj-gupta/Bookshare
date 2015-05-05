package cmpe275Project.DAO;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import cmpe275Project.Model.Book;
import cmpe275Project.Model.Student;
import cmpe275Project.MyExceptions.Exceptions;
import cmpe275Project.config.SpringMongoConfig;

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
		if(isExistingStudent(student.getStudentId())){
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
		Query query = new Query(Criteria.where("_id").is(s_id));
		Update update = new Update();
		
		if(student.getFirstName() != null)
    		    update.set("firstname", student.getFirstName());
		if(student.getLastName() != null)
    		    update.set("lastname", student.getLastName());
    		if(student.getEmail() != null)
    		    update.set("email", student.getEmail());
    		if(student.getPhone() != null)
    		    update.set("phone", student.getPhone());
    		if(student.getUniversity() != null)
    		    update.set("university", student.getUniversity());
    	
		Student res = mongoOps.findAndModify(query, update, Student.class);
		return res;
	}
	
	// Helper Validation Functions
	private boolean isExistingStudent(int studentId) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("id").is(studentId));
		Student existingStudent = mongoOps.findOne(query, Student.class, Student_COLLECTION);

		if(existingStudent != null){
			return true;
		}
		
		return false;
	}

	private boolean isDuplicateEmail(String email) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("email").is(email));
		Student student = mongoOps.findOne(query, Student.class, Student_COLLECTION);
		
		if(student != null){
			return true;
		}
		
		return false;
	}
}
