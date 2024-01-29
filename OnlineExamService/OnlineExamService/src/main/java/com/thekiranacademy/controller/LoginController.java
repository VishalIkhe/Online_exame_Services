package com.thekiranacademy.controller;

import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekiranacademy.entity.Answer;
import com.thekiranacademy.entity.User;
import com.thekiranacademy.service.LoginService;


@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController 
{
	@Autowired
	SessionFactory factory;
		
	@Autowired
	LoginService loginService;
	
	static HttpSession httpsession;
	
	public LoginController()
	{
		System.out.println("Constructor called");
	}
	
		@RequestMapping("validate")
		public ResponseEntity<Boolean> validate(@RequestBody User userFromAngular,HttpServletRequest request)
		{
			httpsession=request.getSession();
			Session session =factory.openSession();
			Boolean answer=loginService.validate(userFromAngular);
			httpsession=request.getSession();
			
			if(answer)
			{
				
				
				System.out.println(httpsession.getId());
				
				httpsession.setAttribute("qno", 0);
				httpsession.setAttribute("score", 0);
			
				HashMap<Integer,Answer> hashmap=new HashMap<Integer, Answer>();
				httpsession.setAttribute("submittedDetails",hashmap);
							
				System.out.println("All attributes are set");
				
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
								
			}
			
			else
			{
				return new ResponseEntity<Boolean>(false,HttpStatus.OK);
			}
				
		}
	}
