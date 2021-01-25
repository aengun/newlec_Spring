<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<aside class="aside">
				<h1>ADMIN PAGE</h1>
				<div>
					<span>${email}</span>
				</div>
				<nav class="menu text-menu first margin-top">
					<h1>마이페이지</h1>
					<ul>
						<li><a href="/admin/index">관리자홈</a></li>
						<li><a href="/teacher/index">선생님페이지</a></li>
						<li><a href="/student/index">수강생페이지</a></li>
					</ul>
				</nav>

				<nav class="menu text-menu">
					<h1>알림관리</h1>
					<ul>
						<li><a href="/admin/board/notice/list">공지사항</a></li>
					</ul>
				</nav>
				
				<nav class="menu text-menu">
					<h1>Top 5 공지사항</h1>
					<ul>
						<c:forEach var="n" items="${asideList}">
							<li><a href="/admin/board/notice/${n.id}">${n.title}</a></li>
						</c:forEach>
					</ul>
				</nav>

			</aside>