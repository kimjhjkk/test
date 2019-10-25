package com.test.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.web.service.TestService;
import com.test.web.vo.TestVO;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
	*/
	
	@Autowired
	TestService service;
	
	@RequestMapping(value = "testInsert", method = RequestMethod.GET)
	public String testInsert(TestVO vo) {
		service.testInsert(vo);
		return "main";
	}
	
	@RequestMapping(value = "testSession1", method = RequestMethod.GET)
	public String testSession1(HttpSession session) {
		session.setAttribute("test", "세션저장");
		return "main";
	}
	
	@RequestMapping(value = "testSession2", method = RequestMethod.GET)
	public String testSession2(HttpSession session) {
		session.removeAttribute("test");
		return "main";
	}
}
