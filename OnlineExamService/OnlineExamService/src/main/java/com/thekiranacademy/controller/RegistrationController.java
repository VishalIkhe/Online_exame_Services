package com.thekiranacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekiranacademy.entity.User;
import com.thekiranacademy.service.RegistrationService;

@RestController
@CrossOrigin("http://localhost:4200")
public class RegistrationController 
{
	@Autowired
	RegistrationService service;
	
	@RequestMapping("saveToDB")
	public ResponseEntity<Boolean> saveToDB(@RequestBody User user)
	{
		
		System.out.println(user);
		
		service.saveToDB(user);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
	}
}
