package com.test.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.web.vo.GuestbookVO;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<GuestbookVO> guestbookList(HashMap<String, String> map) {
		System.out.println("다오");
		GuestbookMapper mapper = sqlSession.getMapper(GuestbookMapper.class);
		return mapper.guestbookList(map);
	}
	
	public int write(GuestbookVO vo) {
		GuestbookMapper mapper = sqlSession.getMapper(GuestbookMapper.class);
		return mapper.write(vo);
	}
	
	public int delete(GuestbookVO vo) {
		GuestbookMapper mapper = sqlSession.getMapper(GuestbookMapper.class);
		return mapper.delete(vo);
	}
	
	public GuestbookVO read(int seq) {
		GuestbookMapper mapper = sqlSession.getMapper(GuestbookMapper.class);
		return mapper.read(seq);
	}
}
