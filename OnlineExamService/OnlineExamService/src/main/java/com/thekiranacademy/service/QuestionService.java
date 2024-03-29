package com.thekiranacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thekiranacademy.dao.QuestionDAO;
import com.thekiranacademy.entity.Question;

@Service
public class QuestionService 
{
	@Autowired
	QuestionDAO dao;
	
	public List<Question> getAllQuestions(String subject)
	{
			return dao.getAllQuestions(subject);
	}
	
}
