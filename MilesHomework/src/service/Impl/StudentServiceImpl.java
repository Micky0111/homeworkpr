package service.impl;

import java.lang.reflect.Member;
import java.util.List;

import dao.impl.StudentDaoImpl;
import model.Student;
import service.StudentService;

public class StudentServiceImpl implements StudentService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static StudentDaoImpl sdi=new StudentDaoImpl();
	
	
	@Override
	public void addStudent(Student s) {
		
		sdi.add(s);
		
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return sdi.selectAll();
	}

	@Override
	public void updateStudent(int id, String name, int chi, int eng) {
		List<Student> l=sdi.selectId(id);
		Student s=l.get(0);
		s.setName(name);
		s.setChi(chi);
		s.setEng(eng);
		
		sdi.update(s);
		
		
		
	}

	@Override
	public void deleteStudent(int id) {
		sdi.delete(id);
		
	}

}
