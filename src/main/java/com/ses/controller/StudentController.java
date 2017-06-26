package com.ses.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ses.domain.Career;
import com.ses.domain.Student;
import com.ses.service.CareerService;
import com.ses.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CareerService careerService;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseTestController.class);
	
	/* =========================================================== */
	/*			       SELECTING ALL STUDENT   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/allStudents", method = RequestMethod.GET)
	public String selectStudent(Model model){
		
		String goodMessage = "";
		String badMessage = "";
		List<Student> students = null;

		try {
			students = studentService.listAllStudents();
			if (students.size() == 0) {
				goodMessage = "There is no Students!";
			}
			
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		model.addAttribute("goodMessage", goodMessage);
		model.addAttribute("badMessage", badMessage);
		model.addAttribute("students", students);
		
		return "all-students";
	}
	
	/* =========================================================== */
	/*			            NEW STUDENT FORM   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/newStudentForm", method = RequestMethod.GET)
	public String saveStudentForm(Model model){
		
		String goodMessage = "";
		String badMessage = "";
		String flagNeedCareer = "";
		List<Career> careers = null;
		
		try {
			careers = careerService.listAllCareers();
			if (careers.size() == 0) {
				flagNeedCareer = "Add a Career First!";
			}
			
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		model.addAttribute("flagNeedCareer", flagNeedCareer);
		model.addAttribute("goodMessage", goodMessage);
		model.addAttribute("badMessage", badMessage);
		model.addAttribute("careers", careers);
		
		return "new-student";
	}
	
	
	/* =========================================================== */
	/*			        SAVING A NEW STUDENT    					   */
	/* =========================================================== */
	
	@RequestMapping(value="/newStudent", method = RequestMethod.POST)
	public String saveStudent(
				@RequestParam(value="name") String name,
				@RequestParam(value="username") String username,
				@RequestParam(value="password") String password,
				@RequestParam(value="idCareer") Long idCareer,
				Model model){
		
		String goodMessage = "";
		String badMessage = "";
		Student student = new Student();
		Career career = null;
		List<Career> careers = null;
		
		career = careerService.findCareerById(idCareer);
		
		student.setName(name);
		student.setUsername(username);
		student.setPassword(password);
		student.setCareer(career);
		
		try {
			careers = careerService.listAllCareers();
			studentService.saveStudent(student);
			goodMessage = "Student is saved";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		model.addAttribute("goodMessage", goodMessage);
		model.addAttribute("badMessage", badMessage);
		model.addAttribute("careers", careers);
		
		return "new-student";
		
	}
	
	/* =========================================================== */
	/*			         UPDATE STUDENT FORM   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/updateStudentForm", method = RequestMethod.POST)
	public String updateStudentForm(
			@RequestParam(value="id") Long idStudent,
			Model model){
		
		String goodMessage = "";
		String badMessage = "";
		Student student = null;
		List<Career> careers = null;
		
		try {
			careers = careerService.listAllCareers();
			student = studentService.findStudentById(idStudent);
			goodMessage = "Student Found!";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}		
		
		LOGGER.debug("deleteStudent - goodMessage: " + goodMessage);
		LOGGER.debug("deleteStudent - badMessage: " + badMessage);
		
		model.addAttribute("student", student);
		model.addAttribute("careers", careers);
		
		return "update-student";
	}
	
	/* =========================================================== */
	/*			           UPDATING A STUDENT   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/updateStudent", method = RequestMethod.POST)
	public String updateStudent(
				@RequestParam(value="id") Long idStudent,
				@RequestParam(value="name") String name,
				@RequestParam(value="username") String username,
				@RequestParam(value="password") String password,
				@RequestParam(value="idCareer") Long idCareer,
				Model model){
		
		String goodMessage = "";
		String badMessage = "";
		Career career = null;
		
		Student student = studentService.findStudentById(idStudent);
		career = careerService.findCareerById(idCareer);
		
		student.setName(name);
		student.setUsername(username);
		student.setPassword(password);
		student.setCareer(career);
		
		try {
			studentService.saveStudent(student);
			goodMessage = "Student Updated";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		LOGGER.debug("deleteStudent - goodMessage: " + goodMessage);
		LOGGER.debug("deleteStudent - badMessage: " + badMessage);
		
		return "redirect:/allStudents";
	}
	
	/* =========================================================== */
	/*			         DELETE STUDENT FORM   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/deleteStudentForm", method = RequestMethod.POST)
	public String deleteStudentForm(
			@RequestParam(value="id") Long idStudent,
			Model model){
		
		String goodMessage = "";
		String badMessage = "";
		Student student = null;
		
		try {
			student = studentService.findStudentById(idStudent);
			goodMessage = "Student Found!";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}		
		
		LOGGER.debug("deleteStudent - goodMessage: " + goodMessage);
		LOGGER.debug("deleteStudent - badMessage: " + badMessage);
		
		model.addAttribute("student", student);
		
		return "delete-student";
	}
	
	/* =========================================================== */
	/*			           DELETING A STUDENT   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/deleteStudent", method = RequestMethod.POST)
	public String deleteStudent(
				@RequestParam(value="id") Long idStudent,
				Model model){
		
		String goodMessage = "";
		String badMessage = "";
		
		Student student = studentService.findStudentById(idStudent);
		
		try {
			studentService.deleteStudent(student);
			goodMessage = "Student Deleted";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		LOGGER.debug("deleteStudent - goodMessage: " + goodMessage);
		LOGGER.debug("deleteStudent - badMessage: " + badMessage);
		
		return "redirect:/allStudents";
	}	
	
}
