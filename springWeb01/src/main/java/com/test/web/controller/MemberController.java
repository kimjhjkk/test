package com.test.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.web.service.MemberService;
import com.test.web.vo.MemberVO;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
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
	private MemberService service;
	
	@RequestMapping(value = "signupForm", method = RequestMethod.GET)
	public String signup() {
		return "/member/signupForm";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String toMain(MemberVO mvo, Model model) {
		int r = service.memberInsert(mvo);
		model.addAttribute("r", r);
		return "main";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "/member/login";
	}
	
	@RequestMapping(value = "returnLogin", method = RequestMethod.GET)
	public String returnLogin() {
		return "main";
	}
	
	@RequestMapping(value = "loginSuccess", method = RequestMethod.POST)
	public String loginSuccess(HttpSession session, Model model, String id, String pwd) {
		if(pwd.equals(service.memberSelect(id))) {
			session.setAttribute("id", id);
			return "main";
		}
		session.setAttribute("id", id);
		return "member/login";
	}
}
