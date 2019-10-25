package com.test.web.dao;

import com.test.web.vo.MemberVO;

public interface MemberMapper {
	public int MemberInsert(MemberVO vo);
	public String MemberSelect(String id);
}
