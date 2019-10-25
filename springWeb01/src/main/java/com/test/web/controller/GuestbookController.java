package com.test.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.web.service.GuestbookService;
import com.test.web.vo.GuestbookVO;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	private static final Logger logger = LoggerFactory.getLogger(GuestbookController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	private GuestbookService service;
	
	@RequestMapping(value = "guestbookList", method = {RequestMethod.GET, RequestMethod.POST})
	public String guestbookList(Model model,
	@RequestParam(value="searchItem", defaultValue="name") String searchItem,
	@RequestParam(value="searchKeyword", defaultValue="") String searchKeyword ) {
		System.out.println("컨트롤러");
		ArrayList<GuestbookVO> list = service.guestbookList(searchItem, searchKeyword);
		model.addAttribute("list", list);
		return "/member/guestbook";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(GuestbookVO vo, Model model, MultipartFile uploadFile) {
		boolean result = service.write(vo, uploadFile);
		model.addAttribute("writeResult", result);
		return "forward:/guestbook/guestbookList";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(GuestbookVO vo, Model model) {
		boolean result = service.delete(vo);
		model.addAttribute("deleteResult", result);
		return "forward:/guestbook/guestbookList";
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public void download(int seq, HttpServletResponse response) {
		GuestbookVO vo = service.read(seq);
		service.download(vo, response);
	}
}
