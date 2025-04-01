<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

<%@ include file="../../common/head.jsp" %>


<script type="text/javascript">

	const loginForm_onSubmit = founction(form) {
		form.loginId.value = form.loginId.value.trim();
		form.loginPw.value = form.loginPw.value.trim();
		
		if(form.loginId.value.length == 0) {
			alert('아이디를 입력해 주세요');
			form.loginId.focus();
			return;
		}
		
		if(form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해 주세요');
			form.loginPw.focus();
			return;
		}
		form.submit();
	}

</script>

	<section class="mt-8 text-lg">
		<div class="container mx-auto px-3">
			<form action="doLogin" method="POST" onsubmit="loginForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<table>
						<tr>
							<th>아이디</th>
							<td><input class="w-96" type="text" name="loginId" placeholder="아이디를 입력해주세요" /></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input class="w-96" type="text" name="loginPw" placeholder="비밀번호를 입력해주세요" /></td>
						</tr>
						<tr>
							<td colspan="2"><div class="btns" ><button>로그인 버튼</button></div></td>
						</tr>
					</table>
				</div>
			</form>
			
			<div class="btns mt-3 text-sm">
			<button onclick="history.back();">뒤로가기</button>
			
			<c:if test="${article.memberId == loginedMemberId}">
				<a href="modify?id=${article.id }">수정</a>
				<a href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</c:if>
			</div>
		</div>
		
	</section>
<%@ include file="../../common/foot.jsp" %>