package com.get.jdbc.entity;

/**
 * 拓展的新闻详情类
 * @author 14541
 *
 */
public class NewsDetail extends News{

	private String tname; //新闻类别名称
	
	public void setTname(String tname) {
		this.tname = tname;
	}	
	public String getTname() {
		return tname;
	}
}
