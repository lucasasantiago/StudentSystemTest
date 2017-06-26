package com.ses.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ses.dao.CareerDAO;
import com.ses.domain.Career;
import com.ses.service.CareerService;

@Service
public class CareerServiceImpl implements CareerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CareerServiceImpl.class);
	
	@Autowired
	private CareerDAO careerDAO;
	
	@Override
	public void saveCareer(Career career) {
		LOGGER.debug("CareerServiceImpl: saveCareer is called");
		careerDAO.save(career);		
	}

	@Override
	public void deleteCareer(Career career) {
		LOGGER.debug("CareerServiceImpl: deleteCareer is called");
		careerDAO.delete(career);
	}
	
	@Override
	public List<Career> listAllCareers() {
		return (List<Career>) careerDAO.findAll();
	}

	@Override
	public Career findCareerById(Long id_career) {
		LOGGER.debug("CareerServiceImpl: findCareerById is called");
		return careerDAO.findOne(id_career);
	}

}
