package com.example.demo.vo;

import java.io.IOException;

import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {

	@Getter
	private int loginedMemberId;
	private HttpServletResponse resp;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {

		this.resp = resp;

		this.session = req.getSession();

		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		this.loginedMemberId = loginedMemberId;
	}

	public void jsPrintReplace(String msg, String url) {
		resp.setContentType("text/html; charset=UTF-8");
		try {
			resp.getWriter().append(Util.jsReplace(msg, url));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

}
