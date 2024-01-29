package com.thekiranacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiranacademy.dao.RegistrationDAO;
import com.thekiranacademy.entity.User;

@Service
public class RegistrationService {

	@Autowired
	RegistrationDAO dao;
	
	public void saveToDB(User user)
	{
		dao.saveToDB(user);
	}
	


}
