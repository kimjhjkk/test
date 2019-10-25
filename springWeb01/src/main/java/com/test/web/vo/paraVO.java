package com.test.web.vo;

public class paraVO {
	private String str;
	private int num;
	
	public paraVO() {}
	public paraVO(String str, int num) {
		super();
		this.str = str;
		this.num = num;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "paraVO [str=" + str + ", num=" + num + "]";
	}
}