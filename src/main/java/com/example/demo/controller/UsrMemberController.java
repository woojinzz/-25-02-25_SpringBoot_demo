package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String loginPwChk, String name, String nickname, String cellphoneNum, String email) {

		if (Util.isEmpty(loginId))
			return ResultData.from("F-1", "아이디는 필수 입력 정보입니다.");

		if (Util.isEmpty(loginPw))
			return ResultData.from("F-2", "비밀번호는 필수 입력 정보입니다.");

		if (!loginPw.equals(loginPwChk))
			return ResultData.from("F-3", "비밀번호가 다릅니다.");

		if (Util.isEmpty(name))
			return ResultData.from("F-4", "이름은 필수 입력 정보입니다.");
			
		if (Util.isEmpty(nickname))
			return ResultData.from("F-5", "닉네임은 필수 입력 정보입니다.");

		if (Util.isEmpty(cellphoneNum))
			return ResultData.from("F-6", "번호는 필수 입력 정보입니다.");

		if (Util.isEmpty(email))
			return ResultData.from("F-7", "이메일은 필수 입력 정보입니다.");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return ResultData.from("F-8", String.format("%s 는 이미 가입된 아이디입니다.", loginId));
		}
		
		memberService.joinMember(loginId, loginPw, loginPwChk, name, nickname, cellphoneNum, email);

		int id = memberService.getLastInsertId();
		
		return ResultData.from("S-1", String.format("%s 님이 가입되었습니다", loginId ), memberService.getMemberById(id));

	}
	
	@GetMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session, String loginId, String loginPw) {
		
		if (session.getAttribute("loginedMemberId") != null) {
			return ResultData.from("F-L", "로그아웃 후 이용해주세요");
		}

		if (Util.isEmpty(loginId))
			return ResultData.from("F-1", "아이디는 필수 입력 정보입니다.");

		if (Util.isEmpty(loginPw))
			return ResultData.from("F-2", "비밀번호는 필수 입력 정보입니다.");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return ResultData.from("F-3", String.format("%s 는 존재하지 않는 아이디 입니다.", loginId));
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}
		
		session.setAttribute("loginedMemberId", member.getId());
	
		return ResultData.from("S-1", String.format("%s 님이 로그인 되었습니다.", member.getNickname()));

	}
	
	@GetMapping("/usr/member/login")
	public String login(HttpSession session) {
	
		return "usr/member/login";
		
	
	}
	
	@GetMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L", "로그인 후 이용해주세요");
		}

		session.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-1", "로그아웃 되었습니다.");

	}
	
}
