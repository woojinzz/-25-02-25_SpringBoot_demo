<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="${boardName} 게시판"  />

<%@ include file="../../common/head.jsp" %>
	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<div>게시글 수 : ${articlesCnt}</div>
			<div class="table-box-type">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr>
								<td>${article.id }</td>
								<td class="hover:underline"><a href="detail?id=${article.id }">${article.title }</a></td>
								<td>${article.writerName }</td>
								<td>${article.updateDate.substring(2, 16) }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${rq.getLoginedMemberId() != 0}">
				<div class="flex justify-end mt-2 mr-3">
					<a class="btn btn-active btn-sm" href="write">글쓰기</a>
				</div>
			</c:if>
			
			<div>
				<div>
					<c:forEach begin="${from }" end="${end } var="i">
						<a href="?boardId=">${i }</a>
					</c:forEach>
				</div>
			</div>
			
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>