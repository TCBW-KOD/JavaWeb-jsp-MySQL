package com.get.jdbc.dao;

import com.get.jdbc.entity.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewsDao extends BaseDao {
	/**
	 * 根据新闻的主键id，查询新闻的详情内容
	 * @param nid
	 * @return
	 */
	public NewsDetail findNewsById(int nid) {
		String sql = "SELECT n.*,t.tname FROM news n,topic t WHERE n.ntid=t.tid AND nid=?";
		Connection con = this.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NewsDetail news = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,nid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				news = new NewsDetail();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setTname(rs.getString("tname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDB(con, pstmt, rs);
		}
		return news;
	}
	
	public List<News> findAllNews() {
		String sql = "SELECT * FROM news";
		Connection con = this.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNpicpath(rs.getString("npicpath"));
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDB(con, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 查询所有新闻列表
	 * @return
	 */
	public List<News> findNewsByPage(int pageNum, int pageSize) {
		String sql = "SELECT * FROM news LIMIT ?,?";
		Connection con = this.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (pageNum-1)*pageSize);
			pstmt.setInt(2,pageSize);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNpicpath(rs.getString("npicpath"));
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDB(con, pstmt, rs);
		}
		return list;
	}
	/**
	 * 统计所有新闻记录数
	 * @return
	 */
	public int getAllCounter() {
		String sql = "SELECT COUNT(*) FROM news";
		Connection con = this.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDB(con, pstmt, rs);
		}
		return count;
	}
}
