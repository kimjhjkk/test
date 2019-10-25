package com.test.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.web.vo.MemberVO;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int memberInsert(MemberVO vo) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.MemberInsert(vo);
	}
	
	public String memberSelect(String id) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.MemberSelect(id);
	}
}
