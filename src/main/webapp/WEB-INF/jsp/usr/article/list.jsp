<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE LIST" />

<%@ include file="../../common/head.jsp" %>
	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
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
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>