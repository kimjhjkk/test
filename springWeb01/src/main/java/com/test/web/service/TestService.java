package com.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.dao.TestDao;
import com.test.web.vo.TestVO;

@Service
public class TestService {
	
	@Autowired
	private TestDao dao;
	
	public void testInsert(TestVO vo) {
		int result = dao.testInsert(vo);
		System.out.println(result);
	}
}
