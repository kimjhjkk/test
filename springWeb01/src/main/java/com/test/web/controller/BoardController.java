package com.test.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.web.service.BoardService;
import com.test.web.util.PageNavigator;
import com.test.web.vo.BoardVO;
import com.test.web.vo.ReplyVO;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "boardList", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model,
			@RequestParam(value="searchItem", defaultValue="title") String searchItem,
			@RequestParam(value="searchKeyword", defaultValue="") String searchKeyword,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage
			) {
		PageNavigator navi = service.getNavi(currentPage, searchItem, searchKeyword);
		ArrayList<BoardVO> list = service.boardList(searchItem, searchKeyword, navi);
		model.addAttribute("list", list);
		model.addAttribute("navi", navi);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchKeyword", searchKeyword);
		return "/board/boardList";
	}
	
	@RequestMapping(value = "boardRead", method = RequestMethod.GET)
	public String boardRead(Model model, int num) {
		BoardVO board = service.boardRead(num);
		ArrayList<ReplyVO> replyList = service.replyList(num);
		model.addAttribute("board", board);
		model.addAttribute("replyList", replyList);
		return "/board/boardRead";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(int num, HttpSession session, RedirectAttributes rttr) {
		BoardVO vo = new BoardVO();
		vo.setBoardNum(num);
		boolean result = service.delete(vo, session);
		rttr.addFlashAttribute("deleteResult", result);
		return "redirect:/board/boardList";
	}
	
	@RequestMapping(value = "boardWrite", method = RequestMethod.GET )
	public String boardWrite() {
		return "/board/boardWriteForm";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST )
	public String write(BoardVO vo, HttpSession session, RedirectAttributes rttr, MultipartFile uploadFile) {
		vo.setUserId((String)session.getAttribute("id"));
		boolean result = service.write(vo, uploadFile);
		rttr.addFlashAttribute("writeResult", result);
		return "redirect:/board/boardList";
	}
	
	@RequestMapping(value = "boardUpdate", method = RequestMethod.GET )
	public String boardUpdate(int num, Model model) {
		BoardVO vo = service.boardRead(num);
		model.addAttribute("vo", vo);
		return "/board/boardUpdateForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST )
	public String update(BoardVO vo, RedirectAttributes rttr, MultipartFile uploadFile) {
		boolean result = service.update(vo, uploadFile);
		rttr.addFlashAttribute("updateResult", result);
		return "redirect:/board/boardRead?num="+vo.getBoardNum();
	}
	
	@RequestMapping(value = "replyWrite", method = RequestMethod.POST )
	public String replyWrite(ReplyVO vo, HttpSession session) {
		service.replyWrite(vo, session);
		return "redirect:/board/boardRead?num="+vo.getBoardNum();
	}
	
	@RequestMapping(value = "replyUpdate", method = RequestMethod.GET )
	public String replyUpdate(ReplyVO vo, HttpSession session) {
		service.replyUpdate(vo, session);
		return "redirect:/board/boardRead?num="+vo.getBoardNum();
	}
	
	@RequestMapping(value = "replyDelete", method = RequestMethod.GET )
	public String replyDelete(ReplyVO vo, HttpSession session, RedirectAttributes rttr) {
		System.out.println(vo);
		boolean result = service.replyDelete(vo, session);
		rttr.addFlashAttribute("deleteReplyResult", result);
		return "redirect:/board/boardRead?num="+vo.getBoardNum();
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET )
	public void download(int num, HttpServletResponse response) {
		BoardVO vo = service.read(num);
		service.download(vo, response);
	}
}
