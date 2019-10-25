package com.test.web.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import com.test.web.vo.BoardVO;

public interface BoardMapper {
	public ArrayList<BoardVO> boardList(HashMap<String, String> map, RowBounds rb);
	public BoardVO boardRead(int num);
	public int delete(BoardVO vo);
	public void hitUp(int num);
	public int write(BoardVO vo);
	public int update(BoardVO vo);
	public int getTotal(HashMap<String, String> map);
}
