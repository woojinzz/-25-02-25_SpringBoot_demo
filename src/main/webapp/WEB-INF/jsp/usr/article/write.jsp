<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="WRITE DETAIL" />

<%@ include file="../../common/head.jsp" %>

<script type="text/javascript">
	const modifyForm_onSubmit = founction(form) {
		form.title.value = form.title.value.trim();
		form.body.value = form.body.value.trim();
		
		if(form.title.value.length == 0) {
			alert('제목을 입력해 주세요');
			form.title.focus();
			return;
		}
		
		if(form.body.value.length == 0) {
			alert('내용을 입력해 주세요');
			form.body.focus();
			return;
		}
		
		form.submit();
	}
</script>

	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<form action="doWrite" method="post" onsubmit="modifyForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<table>
						<tr>
							<th>제목</th>
							<td><input class="input" type="text" name="title"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="textarea textarea-xs" name="body"></textarea></td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn-neutral">작성</button></td>
						</tr>
					</table>
				</div>
			</form>
			<div class="btns mt-3 text-sm">
			<button class="btn btn-neutral" onclick="history.back();">뒤로가기</button>
			</div>
		</div>			

	</section>
<%@ include file="../../common/foot.jsp" %>