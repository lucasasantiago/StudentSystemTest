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
import com.ses.service.CareerService;

@Controller
public class CareerController {

	@Autowired
	private CareerService careerService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseTestController.class);
	
	/* =========================================================== */
	/*			       SELECTING ALL CAREER    					   */
	/* =========================================================== */
	
	@RequestMapping(value="/allCareers", method = RequestMethod.GET)
	public String selectCareer(Model model){
		
		String goodMessage = "";
		String badMessage = "";
		List<Career> careers = null;

		try {
			careers = careerService.listAllCareers();
			if (careers.size() == 0) {
				goodMessage = "There is no Careers!";
			}
			
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		model.addAttribute("goodMessage", goodMessage);
		model.addAttribute("badMessage", badMessage);
		model.addAttribute("careers", careers);
		
		return "all-careers";
	}
	
	/* =========================================================== */
	/*			            NEW CAREER FORM   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/newCareerForm", method = RequestMethod.GET)
	public String saveCareerForm(){
		return "new-career";
	}
	
	
	/* =========================================================== */
	/*			        SAVING A NEW CAREER    					   */
	/* =========================================================== */
	
	@RequestMapping(value="/newCareer", method = RequestMethod.POST)
	public String saveCareer(
				@RequestParam(value="name") String name,
				Model model){
		
		String goodMessage = "";
		String badMessage = "";
		
		Career career = new Career();
		career.setName(name);
		
		try {
			careerService.saveCareer(career);
			goodMessage = "Career is saved";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		model.addAttribute("goodMessage", goodMessage);
		model.addAttribute("badMessage", badMessage);
		
		return "new-career";
	}
	
	/* =========================================================== */
	/*			         UPDATE CAREER FORM   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/updateCareerForm", method = RequestMethod.POST)
	public String updateCareerForm(
			@RequestParam(value="id") Long idCareer,
			Model model){
		
		String goodMessage = "";
		String badMessage = "";
		Career career = null;
		
		try {
			career = careerService.findCareerById(idCareer);
			goodMessage = "Career Found!";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}		
		
		LOGGER.debug("deleteCareer - goodMessage: " + goodMessage);
		LOGGER.debug("deleteCareer - badMessage: " + badMessage);
		
		model.addAttribute("career", career);
		
		return "update-career";
	}
	
	/* =========================================================== */
	/*			           UPDATING A CAREER   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/updateCareer", method = RequestMethod.POST)
	public String updateCareer(
				@RequestParam(value="id") Long idCareer,
				@RequestParam(value="name") String name,
				Model model){
		
		String goodMessage = "";
		String badMessage = "";
		
		Career career = careerService.findCareerById(idCareer);
		
		career.setName(name);
		
		try {
			careerService.saveCareer(career);
			goodMessage = "Career Saved";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		LOGGER.debug("deleteCareer - goodMessage: " + goodMessage);
		LOGGER.debug("deleteCareer - badMessage: " + badMessage);
		
		return "redirect:/allCareers";
	}
	
	/* =========================================================== */
	/*			         DELETE CAREER FORM   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/deleteCareerForm", method = RequestMethod.POST)
	public String deleteCareerForm(
			@RequestParam(value="id") Long idCareer,
			Model model){
		
		String goodMessage = "";
		String badMessage = "";
		Career career = null;
		
		try {
			career = careerService.findCareerById(idCareer);
			goodMessage = "Career Found!";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}		
		
		LOGGER.debug("deleteCareer - goodMessage: " + goodMessage);
		LOGGER.debug("deleteCareer - badMessage: " + badMessage);
		
		model.addAttribute("career", career);
		
		return "delete-career";
	}
	
	/* =========================================================== */
	/*			           DELETING A CAREER   					   */
	/* =========================================================== */
	
	@RequestMapping(value="/deleteCareer", method = RequestMethod.POST)
	public String deleteCareer(
				@RequestParam(value="id") Long idCareer,
				Model model){
		
		String goodMessage = "";
		String badMessage = "";
		
		Career career = careerService.findCareerById(idCareer);
		
		try {
			careerService.deleteCareer(career);
			goodMessage = "Career Deleted";
		} catch (Exception e) {
			badMessage = "A problem has ocurred!";
		}
		
		LOGGER.debug("deleteCareer - goodMessage: " + goodMessage);
		LOGGER.debug("deleteCareer - badMessage: " + badMessage);
		
		return "redirect:/allCareers";
	}	
	
}
