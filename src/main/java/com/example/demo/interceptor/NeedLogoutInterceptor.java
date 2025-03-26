package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NeedLogoutInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		Rq rq = (Rq) request.getAttribute("rq");
		
		if (rq.getLoginedMemberId() != 0) {
			rq.jsPrintReplace("로그아웃 후 이용해 주세요.!", "/");
		
			return false; 
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
