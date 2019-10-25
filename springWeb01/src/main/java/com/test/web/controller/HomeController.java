package com.test.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.web.dao.testingDao;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	*/
	
	@Autowired
	testingDao dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpSession session) {
		session.removeAttribute("id");
		return "home";
	}
	
	@RequestMapping(value = "/nullpoint", method = RequestMethod.GET)
	public String nullponit() {
		throw new NullPointerException();
	}
	
	@RequestMapping(value = "/listTest", method = RequestMethod.POST)
	public String listTest(HttpServletRequest request, Model model) {
		String [] sss = request.getParameterValues("aa");
		for(int i=0; i<sss.length; i++)
			System.out.println(sss[i]);
		HashMap hm = new HashMap();
		List slist = new ArrayList();
		for(int i=0; i<sss.length; i++)
			slist.add(sss[i]);
		hm.put("key", slist);
		System.out.println(hm);
		ArrayList<String> list = dao.testing(hm);
		System.out.println();
		System.out.println(list);
		return "home";
	}
	
	/*
	@RequestMapping(value = "send1", method = RequestMethod.GET)
	public String send1(String a, int b) {
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		return "index";
	}
	
	@RequestMapping(value = "send2", method = { RequestMethod.GET, RequestMethod.POST })
	public String send2(String a, String b, int c) {
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		System.out.println("c : " + c);
		return "index";
	}
	
	@RequestMapping(value = "send4", method = RequestMethod.GET)
	public String send4(TestVO vo) {
		System.out.println("a : " + vo.getA());
		System.out.println("b : " + vo.getB());
		System.out.println(vo);
		return "index";
	}
	
	@RequestMapping(value = "act", method = RequestMethod.POST)
	public String act(paraVO vo) {
		System.out.println("str : " + vo.getStr());
		System.out.println("num : " + vo.getNum());
		System.out.println(vo);
		return "testing";
	}
	
	@RequestMapping(value = "send5", method = RequestMethod.GET)
	public String send5(Model model) {
			model.addAttribute("data", "�׿�����");
			model.addAttribute("vo", new TestVO("aaBBc", 123987));
		return "index";
	}*/
	
	/*
	@RequestMapping(value = "send2", method = RequestMethod.GET)
	public String send2(String a, String b, int c) {
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		System.out.println("c : " + c);
		return "index";
	}
	
	@RequestMapping(value = "send3", method = RequestMethod.POST)
	public String send3(String a, String b, int c) {
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		System.out.println("c : " + c);
		return "index";
	}
	*/
}
