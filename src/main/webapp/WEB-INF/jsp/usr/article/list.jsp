<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

	String keyword = request.getParameter("keyword");
%>


<c:set var="pageTitle" value="${boardName} 게시판"  />

<%@ include file="../../common/head.jsp" %>
	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
				<div class="mb-2 ml-3 text-sm flex justify-between item-end">
					<div><span>게시글 수 : ${articlesCnt}</span></div>
					
					<form>
						<div>
							<input type="hidden" name="boardId" value="${param.boardId}"/>
							<select class="select select-bordered select-sm w-30" name="searchKeywordType" id="">
								<option value="title">제목</option>
								<option value="body">내용</option>
								<option value="title,body">제목 + 내용</option>
							</select>
							
							<input maxlength=20 class="input input-bordered input-sm w-60 mx-2" type="text" name="searchKeyword" value="${param.searchKeyword }" />
							<button class="btn btn-active btn-sm" type="submit">검색</button>
						</div>
					</form>
				</div>
			
			
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
			
			<div class="mt-3 mb-3 flex justify-center">
				<div class="join">
				
					<c:if test="${from != 1 }">
						<a class="join-item btn btn-sm" href="?boardId=${param.boardId }&cPage=1&${baseUri}"><i class="fa-solid fa-angles-left"></i></a>
						<a class="join-item btn btn-sm" href="?boardId=${param.boardId }&cPage=${from - 1}&${baseUri}"><i class="fa-solid fa-caret-left"></i></a>
					</c:if>
				
					<!--공통으로 들어가는 url 변수  -->
					<c:set var="baseUri" value="boardId=${param.boardId }&searchKeywordType=${param.searchKeywordType }&searchKeyword=${param.searchKeyword }"></c:set>
					<c:forEach begin="${from }" end="${end }" var="i">
						<a class="join-item btn btn-sm ${cPage == i ? 'btn-active' : '' }"href="?cPage=${i }&${baseUri}">${i }</a>
					</c:forEach>
				
					<c:if test="${end != totalPageCnt }">
						<a class="join-item btn btn-sm" href="?boardId=${param.boardId}&cPage=${end + 1 }&${baseUri}"><i class="fa-solid fa-caret-right"></i></a>
						<a class="join-item btn btn-sm" href="?boardId=${param.boardId}&cPage=${totalPageCnt }&${baseUri}"><i class="fa-solid fa-angles-right"></i></a>
					</c:if>
					
				</div>
			</div>
	</div>
	</section>
<%@ include file="../../common/foot.jsp" %>