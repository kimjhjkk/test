package com.test.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.web.vo.TestVO;

@Repository
public class TestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int testInsert(TestVO vo) {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.testInsert(vo);
	}
}
