package com.web.news.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.get.jdbc.dao.*;
import com.get.jdbc.entity.Users;
import com.get.jdbc.entity.*;

/**
 * Servlet implementation class NewsServlet
 */
public class NewsServlet extends HttpServlet {

	public static final int PAGE_NUM = 5;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先设置响应编码
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action"); //请求标识
		if("listNews".equals(action)) { //新闻列表分页
			doListNews(request,response);
		}else if ("detail".equals(action)) {
			doDetail(request,response);
		}else if ("newsComment".equals(action)) {
			System.out.println("2222");
			doNewsComment(request,response);		
		}

	}

	private void doNewsComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			System.out.println("1111");
			CommentDao commentDao = new CommentDao();
			Comment comment = new Comment();
			int nid = Integer.parseInt(request.getParameter("nid"));
			comment.setCnid(Integer.parseInt(request.getParameter("nid")));
			comment.setCip(request.getParameter("cip"));
			comment.setCauthor(request.getParameter("cauthor"));
			comment.setCcontent(request.getParameter("ccontent"));
			commentDao.addComment(comment);
			request.getRequestDispatcher("/NewsServlet?action=detail&nid="+nid).forward(request,response);	
		}

	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsDao newsDao = new NewsDao();
		CommentDao commentDao = new CommentDao();
		// 1.查询新闻详情数据
		int nid = Integer.parseInt(request.getParameter("nid"));
		NewsDetail news = newsDao.findNewsById(nid);
		// 2.查询评论列表
		List<Comment> commentList = commentDao.findCommentByNews(nid);
		
		//获取IP
		String cip = request.getRemoteAddr();
		request.setAttribute("news", news);
		request.setAttribute("commentList", commentList);
		request.setAttribute("cip", cip);
		
		request.getRequestDispatcher("/news_read.jsp").forward(request,response);
		
	}

	private void doListNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		NewsDao newsDao = new NewsDao();
		int pageNum = 1; // 默认显示第一页
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		List<News> newsList = newsDao.findNewsByPage(pageNum, PAGE_NUM);

		// 计算总记录数
		int allCount = newsDao.getAllCounter();

		// 计算总页数
		int allPage = 0;
		if (allCount % PAGE_NUM != 0) {// 不能整除
			allPage = allCount / PAGE_NUM + 1;
		} else {
			allPage = allCount / PAGE_NUM;
		}
		
		//计算下一页，上一页的参数
		int next = pageNum; //下一页
		int prev = pageNum; //上一页
		//判断页码边界
		if(pageNum<=1) { //首页边界
			next++;
			prev=1;
		}else if(pageNum>=allPage) { //尾页边界
			next = allPage;
			prev--;
		} else {
			next++;
			prev--;
		}
		//把所有的分页参数保存到作用域中
		request.setAttribute("newsList", newsList);
		request.setAttribute("allCount", allCount);
		request.setAttribute("allPage", allPage);
		request.setAttribute("next", next);
		request.setAttribute("prev", prev);
		request.setAttribute("pageNum", pageNum);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
