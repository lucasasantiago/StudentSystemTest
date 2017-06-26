package com.ses.service;

import java.util.List;

import com.ses.domain.Student;

public interface StudentService {
	
	public void saveStudent(Student student);
	public void deleteStudent(Student student);
	public List<Student> listAllStudents();
	public Student findStudentById(Long id_student);
	
}
