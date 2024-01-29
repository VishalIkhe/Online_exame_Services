package com.thekiranacademy.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekiranacademy.entity.Answer;
import com.thekiranacademy.entity.Question;
import com.thekiranacademy.service.QuestionService;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuestionController 
{

	@Autowired
	QuestionService questionService;
	
	@RequestMapping("getFirstQuestion/{subject}")
	public ResponseEntity<Question> getFirstQuestion(@PathVariable String subject)
	{

		HttpSession httpsession=LoginController.httpsession;
		
		System.out.println(httpsession.getId());

		System.out.println("Subject from Angular is " + subject);

		List<Question> list =questionService.getAllQuestions(subject);
				
		Question question=list.get(0);
		
		ResponseEntity<Question> responseEntity=new ResponseEntity(question,HttpStatus.OK);
		
		httpsession.setAttribute("list", list);
		
		return responseEntity;
		
	}
	

	@PostMapping("saveAnswer")
	public void saveAnswer(@RequestBody Answer answer)
	{
		System.out.println(answer);
		
		HttpSession httpsession=LoginController.httpsession;
		
		// add/update user response in HashMap
		
		if(answer.getSubmittedAnswer()!=null)
		{
			HashMap<Integer,Answer> hashMap=(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
			hashMap.put(answer.getQno(),answer);
			System.out.println(hashMap);
		}
	
	}
	
	@RequestMapping("next")
	public ResponseEntity<Question> next(HttpServletRequest request)
	{
				
		HttpSession httpsession=LoginController.httpsession;
	
		
		System.out.println(httpsession.getId());
		
		// Object getAttribute(String name);
		
	List<Question> list= (List<Question>) httpsession.getAttribute("list");

		  // 0 1 2  index
		//   1 2 3
		// 3-2

		Question question=null;
		
		System.out.println(httpsession.getAttribute("qno"));
		
		if((int)httpsession.getAttribute("qno")<=list.size()-2)
		{
			httpsession.setAttribute("qno", (int)httpsession.getAttribute("qno") + 1);//2
		
			int index=(int)httpsession.getAttribute("qno");//2
			
			question=list.get(index);
		}
		else
		{
			question=list.get(list.size()-1);
			
		}
		

		ResponseEntity<Question> responseEntity=new ResponseEntity<Question>(question,HttpStatus.OK);
		
		return responseEntity;
	
		
		// 0 1 2 3 4 5
		// 1 2 3 4 5 6
		// 6-2=4
	}

	

	@RequestMapping("previous")
	public ResponseEntity<Question> previous()
	{
				
		HttpSession httpsession=LoginController.httpsession;
		System.out.println(httpsession.getId());
		
		// Object getAttribute(String name);
		
	List<Question> list= (List<Question>) httpsession.getAttribute("list");

		  // 0 1 2  index
		//   1 2 3
		// 3-2

		Question question=null;
		
		System.out.println(httpsession.getAttribute("qno"));
		
		if((int)httpsession.getAttribute("qno")>0)
		{
			httpsession.setAttribute("qno", (int)httpsession.getAttribute("qno") - 1);
		
			int index=(int)httpsession.getAttribute("qno");
			
			question=list.get(index);
		}
		else
		{
			question=list.get(0);
			
		}
				
		ResponseEntity<Question> responseEntity=new ResponseEntity<Question>(question,HttpStatus.OK);
		
		return responseEntity;
	
		
	}


	
	
	
	@RequestMapping("endexam")
	public ResponseEntity<Integer> endexam()
	{	

		HttpSession httpsession=LoginController.httpsession;
	
		HashMap<Integer,Answer> hashMap=(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
		
		Collection<Answer> collection=hashMap.values();
		
		System.out.println(" values() gives object of class whose name is " + collection.getClass().getName());
		
		
		// Collection collection=new ArrayList();
		
		// reference of interface can refer to object of implementation class
		
		
		for (Answer ans : collection) 
		{
			if(ans.getSubmittedAnswer().equals(ans.getOriginalAnswer()))
			{
				httpsession.setAttribute("score",(int)httpsession.getAttribute("score")+1);//2
				
				// httpsession.setAttribute("score",10);
				
			}
		}
		

		Integer score=(Integer)httpsession.getAttribute("score");
		
		System.out.println("Total Score at Server " + score);

		ResponseEntity<Integer> responseEntity=new ResponseEntity<Integer>(score,HttpStatus.OK);

		return responseEntity;
		
	}
		
	@RequestMapping("allAnswers")
	public ResponseEntity<Collection<Answer>> getAllAnswers()
	{

		HttpSession httpsession=LoginController.httpsession;

		HashMap<Integer,Answer> hashMap=(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
		
		Collection<Answer> collection=hashMap.values();
		
		ResponseEntity<Collection<Answer>> responseEntity=new ResponseEntity<>(collection,HttpStatus.OK);

		return responseEntity;
			
		
	}
	
	
	
	
	

}
