<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="index-elements/index_top.jsp"></jsp:include>
<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp" />
	<div class="main">
		<div class="class_type">
			<img src="Images/class_type.gif" alt="新闻中心" />
		</div>
		<div class="content">
			<ul class="class_date">
				<!--遍历 显示topic list -->
				<li id='class_month'><a href="index.jsp?tid=1"><b></b> </a> <!--遍历 显示topic list -->
			</ul>

			<ul class="classlist">
				<!-- 遍历显示 news list  -->
				<c:forEach var="news" items="${newsList}">
					<li><a href="${pageContext.request.contextPath}/NewsServlet?action=detail&nid=${news.nid}">${news.ntitle}</a> <span>${news.ncreatedate}</span></li>
				</c:forEach>
				<!-- 遍历显示 news list  -->

				<!-- 分页显示 -->
				<p align="right">
					当前页数:[${pageNum}/${allPage}]&nbsp;&nbsp;总记录数${allCount} 
					<a href="${pageContext.request.contextPath}/NewsServlet?action=listNews&pageNum=1">首页</a>
					<a href="${pageContext.request.contextPath}/NewsServlet?action=listNews&pageNum=${prev}">上页</a>
					<a href="${pageContext.request.contextPath}/NewsServlet?action=listNews&pageNum=${next}">下页</a>
					<a href="${pageContext.request.contextPath}/NewsServlet?action=listNews&pageNum=${allPage}">尾页</a>
				</p>

			</ul>
		</div>
		<jsp:include page="index-elements/index_rightbar.html" />
	</div>
</div>

<jsp:include page="index-elements/index_bottom.html" />
