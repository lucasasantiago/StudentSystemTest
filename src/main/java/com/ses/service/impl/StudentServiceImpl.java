package com.ses.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ses.dao.StudentDAO;
import com.ses.domain.Student;
import com.ses.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public void saveStudent(Student student) {
		LOGGER.debug("StudentServiceImpl: saveStudent");
		studentDAO.save(student);
	}

	@Override
	public void deleteStudent(Student student) {
		LOGGER.debug("StudentServiceImpl: deleteStudent");
		studentDAO.delete(student);
	}

	@Override
	public List<Student> listAllStudents() {
		LOGGER.debug("StudentServiceImpl: listAllStudents");
		return (List<Student>) studentDAO.findAll();
	}

	@Override
	public Student findStudentById(Long id_student) {
		return studentDAO.findOne(id_student);
	}

	
}
