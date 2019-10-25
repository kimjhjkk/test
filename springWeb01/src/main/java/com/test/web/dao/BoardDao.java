package com.test.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.web.vo.BoardVO;
import com.test.web.vo.ReplyVO;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<BoardVO> boardList(HashMap<String, String> map, int startRecord, int countPerPage) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		return mapper.boardList(map, rb);
	}
	
	public BoardVO boardRead(int num) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.hitUp(num);
		return mapper.boardRead(num);
	}
	
	public BoardVO read(int num) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.boardRead(num);
	}
	
	public int delete(BoardVO vo) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.delete(vo);
	}
	
	public int write(BoardVO vo) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.write(vo);
	}
	
	public int update(BoardVO vo) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.update(vo);
	}
	
	public void replyWrite(ReplyVO vo) {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		mapper.replyWrite(vo);
	}
	
	public ArrayList<ReplyVO> replyList(int num) {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.replyList(num);
	}
	
	public void replyUpdate(ReplyVO vo) {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		mapper.replyUpdate(vo);
	}
	
	public int replyDelete(ReplyVO vo) {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.replyDelete(vo);
	}

	public int getTotal(HashMap<String, String> map) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		return mapper.getTotal(map);
	}
	
}