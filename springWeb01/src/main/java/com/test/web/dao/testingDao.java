package com.test.web.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import com.test.web.vo.MemberVO;

@Repository
public class testingDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<String> testing(HashMap hm) {
		testingMapper mapper = sqlSession.getMapper(testingMapper.class);
		System.out.println(hm);
		return mapper.testing(hm);
	}
}
