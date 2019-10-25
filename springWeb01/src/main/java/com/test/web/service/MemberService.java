package com.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.dao.MemberDao;
import com.test.web.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao dao;
	
	public int memberInsert(MemberVO vo) {
		int r = dao.memberInsert(vo);
		return r;
	}
	
	public String memberSelect(String id) {
		String pwd = dao.memberSelect(id);
		return pwd;
	}
}