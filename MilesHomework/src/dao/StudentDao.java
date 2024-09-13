package dao;

import java.util.List;

import model.Student;

public interface StudentDao {
	//create
	void add(Student s);
	
	//read
	List<Student> selectAll();
	List<Student> selectId(int id);
	
	//upadte
	void update(Student s);
	
	//delete
	void delete(int id);

}
