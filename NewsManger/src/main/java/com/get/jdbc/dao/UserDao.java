package com.get.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.get.jdbc.entity.Users;

/**
 * Users实体类的操作类
 * @author Administrator
 *
 */
// 返回用户对象，判断是否为空
public class UserDao extends BaseDao {
    public Users login(String username, String password){
        String sql = "select * from news_users where uname=? and upwd=?";
        Connection con = this.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Users loginUser = null;
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                loginUser = new Users();
                loginUser.setId(rs.getInt("usid"));
                loginUser.setUsername(rs.getString("upwd"));
                loginUser.setPassword(rs.getString("uname"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            this.closeDB(con, pstmt, rs);
        }
        return loginUser;

    }

    public static void main(String[] args) {
        String username  = "admin";
        String userpwd = "admin";
        UserDao userDao = new UserDao();
        Users loginUser = userDao.login(username,userpwd);
        System.out.println(loginUser.getUsername());
    }
}
