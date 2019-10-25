package com.test.web.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyNum;
	private int boardNum;
	private String userId;
	private String replyText;
	private String inputdate;
}