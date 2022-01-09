package com.get.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.get.jdbc.entity.Comment;


public class CommentDao extends BaseDao {
	/**
	 * 查询新闻之下的评论列表
	 * @param nid
	 * @return
	 */
	public List<Comment> findCommentByNews(int nid) {
		String sql = "SELECT * FROM comments WHERE cnid=?";
		Connection con = this.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCid(rs.getInt("cid"));
				comment.setCnid(rs.getInt("cnid"));
				comment.setCcontent(rs.getString("ccontent"));
				comment.setCdate(rs.getString("cdate"));
				comment.setCip(rs.getString("cip"));
				comment.setCauthor(rs.getString("cauthor"));

				list.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDB(con, pstmt, rs);
		}
		return list;
	}

	public int addComment(Comment comment) {
		String sql = "INSERT INTO COMMENTS(cnid,ccontent,cdate,cip,cauthor) VALUES(?,?,NOW(),?,?)";
		Connection con = this.getConnection();
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,comment.getCnid());
			pstmt.setString(2,comment.getCcontent());
			pstmt.setString(3,comment.getCip());
			pstmt.setString(4,comment.getCauthor());
			
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.closeDB(con, pstmt,null);
		}
		return row; 
		}
}
