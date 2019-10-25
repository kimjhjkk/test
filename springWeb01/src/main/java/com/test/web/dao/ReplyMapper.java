package com.test.web.dao;

import java.util.ArrayList;

import com.test.web.vo.ReplyVO;

public interface ReplyMapper {
	public void replyWrite(ReplyVO vo);
	public ArrayList<ReplyVO> replyList(int num);
	public void replyUpdate(ReplyVO vo);
	public int replyDelete(ReplyVO vo);
}
