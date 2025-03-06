<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL" />

<%@ include file="../../common/head.jsp" %>

${loginedMemberId}
	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table>
					<tr>
						<th>번호</th>
						<td>${foundArticle.id }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${foundArticle.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${foundArticle.body }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${foundArticle.writerName }</td>
					</tr>
			
					<tr>
						<th>작성일</th>
						<td>${foundArticle.updateDate }</td>
					</tr>
				</table>
			</div>
			
			<div>
				<button>뒤로가기</button>
				<button>수정</button>
				<button>삭제</button>
			</div>
			
			
		</div>
		
	</section>
<%@ include file="../../common/foot.jsp" %>