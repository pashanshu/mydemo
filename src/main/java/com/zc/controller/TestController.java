package com.zc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.dao.MyTestMapper;
import com.zc.model.MyTest;
import com.zc.model.User;
import com.zc.service.TestService;

/**
 * test controller
 * @author zhanchang
 * 
 **/

@Controller
@RequestMapping(value="/test")
public class TestController {
    
   @Resource
   private TestService testService;
   @Resource
	private MyTestMapper myTestMapper;
	
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
	
	
	@ResponseBody
	@RequestMapping(value="/getuserinfo", method = RequestMethod.GET)
	public User getUserInfo(){
		logger.info("we begin here");
		return testService.queryByName("abcd");
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getmytestinfo", method = RequestMethod.GET)
	public MyTest getMytestInfo(){
		logger.info("mytest is begin!");
		MyTest m = myTestMapper.selectByName("zc").get(0);
		return m;
	}
	
}
