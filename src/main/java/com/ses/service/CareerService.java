package com.ses.service;

import java.util.List;

import com.ses.domain.Career;

public interface CareerService {
	
	public void saveCareer(Career career);
	public void deleteCareer(Career career);
	public List<Career> listAllCareers();
	public Career findCareerById(Long id_career);
}
