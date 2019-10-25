package com.test.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.test.web.dao.BoardDao;
import com.test.web.util.PageNavigator;
import com.test.web.vo.BoardVO;
import com.test.web.vo.ReplyVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao dao;
	
	private final int countPerPage = 10;
	private final int pagePerGroup = 5;
	
	public ArrayList<BoardVO> boardList(String searchItem, String searchKeyword, PageNavigator navi) {
		HashMap<String, String> map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchKeyword", searchKeyword);
		ArrayList<BoardVO> list = dao.boardList(map, navi.getStartRecord(), navi.getCountPerPage());
		return list;
	}
	
	public BoardVO boardRead(int num) {
		BoardVO list = dao.boardRead(num);
		return list;
	}
	
	public BoardVO read(int num) {
		BoardVO list = dao.read(num);
		return list;
	}
	
	public boolean delete(BoardVO vo, HttpSession session) {
		String userId = (String)session.getAttribute("id");
		vo.setUserId(userId);
		
		BoardVO boardVO = dao.read(vo.getBoardNum());
		String savedFilename = boardVO.getSavedFilename();
		
		if(dao.delete(vo) != 1) return false;
		File file = new File("C:/Temp/board/" + savedFilename);
		if(file.exists()) file.delete();
		return true;
	}
	
	public boolean write(BoardVO vo, MultipartFile uploadFile) {
		if(!uploadFile.isEmpty()) {
			String originalFilename = uploadFile.getOriginalFilename();
			String savedFilename = UUID.randomUUID().toString();
			vo.setOriginalFilename(originalFilename);
			vo.setSavedFilename(savedFilename);
			try {
				uploadFile.transferTo(new File("C:/Temp/board/" + savedFilename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		if(dao.write(vo) != 1) return false;
		else return true;
	}
	
	public boolean update(BoardVO vo, MultipartFile uploadFile) {
		String oldSavedFilename = vo.getSavedFilename();
		if(!uploadFile.isEmpty()) {	
			String originalFilename = uploadFile.getOriginalFilename();
			String savedFilename = UUID.randomUUID().toString();
			vo.setOriginalFilename(originalFilename);
			vo.setSavedFilename(savedFilename);
			File file = new File("C:/Temp/board/" + vo.getSavedFilename());
			try {
				uploadFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			if(dao.update(vo) != 1) {
				file.delete();
				return false;
			}
			File oldFile = new File("C:/Temp/board/" + oldSavedFilename);
			oldFile.delete();
			return true;
		}
		if(dao.update(vo) != 1) return false;
		return true;
	}
	
	public void replyWrite(ReplyVO vo, HttpSession session) {
		String userId = (String)session.getAttribute("id");
		vo.setUserId(userId);
		dao.replyWrite(vo);
	}
	
	public ArrayList<ReplyVO> replyList(int num) {
		return dao.replyList(num);
	}
	
	public void replyUpdate(ReplyVO vo, HttpSession session) {
		String userId = (String)session.getAttribute("id");
		vo.setUserId(userId);
		dao.replyUpdate(vo);
	}
	
	public boolean replyDelete(ReplyVO vo, HttpSession session) {
		String userId = (String)session.getAttribute("id");
		vo.setUserId(userId);
		if(dao.replyDelete(vo) == 1) return true;
		return false;
	}
	
	public PageNavigator getNavi(int currentPage, String searchItem, String searchKeyword) {
		HashMap<String, String> map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchKeyword", searchKeyword);
		int totalRecordsCount = dao.getTotal(map);
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, currentPage, totalRecordsCount);
		return navi;
	}
	
	public void download(BoardVO vo, HttpServletResponse response) {
		File file = new File("C:/Temp/board/" + vo.getSavedFilename());
		String originalFilename = vo.getOriginalFilename();
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(originalFilename, "UTF-8"));
			response.setContentLength((int)file.length());
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
