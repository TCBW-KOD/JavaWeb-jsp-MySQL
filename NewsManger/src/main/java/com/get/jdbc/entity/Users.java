package com.get.jdbc.entity;
/**
 * new_users映射的实体类
 * @author 14541
 *
 */
public class Users {
	private int id;
	private String username;
    private String password;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
