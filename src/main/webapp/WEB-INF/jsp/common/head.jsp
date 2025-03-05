<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 테일윈드  -->
<script src="https://unpkg.com/@tailwindcss/browser@4"></script>
<!-- 폰드어썸 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" ></script>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" ></script>
<!-- 공통 css  -->
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
	<div class="flex justify-between">
		<div class="bg-red-300"><a href="/"><span>로고</span></a></div>
		
		<ul class="flex p-10 ">
			<li><a href="/">HOME</a></li>
			<li><a href="/usr/article/list">LIST</a></li>
		</ul>
	</div>
	
	<section>
		<div>
			<div>${pageTitle }&nbsp;PAGE</div>
		</div>
	
	</section>
