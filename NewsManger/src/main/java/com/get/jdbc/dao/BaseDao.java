package com.get.jdbc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 通用DAO
 * @author 14541
 *
 */
public class BaseDao {
	/**
	 * 获取数据库的连接对象
	 * @return
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_conect?useUnicode=true&characterEncoding=UTF-8","root","");
			System.out.println("连接成功");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 释放数据库资源
	 * @param con
	 * @param stamt
	 * @param rs
	 */
	public void closeDB(Connection con, Statement stamt, ResultSet rs) {
		try {
            if(rs != null)
            {
                rs.close();
            }
            if(stamt != null)
            {
                stamt.close();
            }
            if(con != null)
            {
                con.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

}
