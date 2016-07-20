package com.zc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.model.User;

/**
 * test controller
 * @author zhanchang
 * 
 **/

@Controller
@RequestMapping(value="/test")
public class TestController {
    
	private static Logger logger = Logger.getLogger(TestController.class);
	
	@RequestMapping(value="/querymemberinfo")
	public String queryMemberInfo(){
		logger.info("we begin here");
		return "success";
	}
	
	@RequestMapping(value="/getname/{name}/{age}")
	public String getName(@PathVariable("name") String name,@PathVariable("age") String age){
		logger.info("name is " +name );
		logger.info("age is " +age );
		return "success";
	}	
	
	@ResponseBody
	@RequestMapping(value="/getobject")
	public User getObject(){
		logger.info("we begin here");
		User user = new User();
		user.setUserName("zhanchang");
		user.setUserAge("100");
		return user;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/retobject", method = RequestMethod.POST)
	public User retObject(@Valid @RequestBody User user,BindingResult result){
		
		if (result.hasErrors()) {
            return user;
        }
		logger.info("we begin here");
		user.setUserName(user.getUserName()+"001");
		user.setUserAge("200");
		return user;
	}
}
