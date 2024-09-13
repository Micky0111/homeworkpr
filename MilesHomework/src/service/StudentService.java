package service;

import java.util.List;

import model.Student;

public interface StudentService {
	
	void addStudent(Student s);//新增
	
	List<Student> findAll();//查詢
	
	void updateStudent(int id,String name,int chi,int eng);//修改
	
	void deleteStudent(int id);//刪除

}
