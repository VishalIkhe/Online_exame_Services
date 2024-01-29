package com.thekiranacademy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thekiranacademy.entity.Question;

import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

@Repository
public class QuestionDAO 
{

	@Autowired
	SessionFactory factory;
	
	public List<Question> getAllQuestions(String subject)
	{
		Session session=factory.openSession();
			
		Criteria criteria=session.createCriteria(Question.class);
		
		criteria.add(Restrictions.eq("subject", subject));
		
		return criteria.list();
	}
	
	
	

}
