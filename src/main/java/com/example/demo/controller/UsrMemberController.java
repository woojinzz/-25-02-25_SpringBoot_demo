package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String loginPwChk, String name, String nickname, String cellphoneNum, String email) {

		if (Util.isEmpty(loginId))
			return "아이디는 필수 입력 정보입니다.";

		if (Util.isEmpty(loginPw))
			return "비밀번호는 필수 입력 정보입니다.";

		if (!loginPw.equals(loginPwChk))
			return "비밀번호가 다릅니다.";

		if (Util.isEmpty(name))
			return "이름은 필수 입력 정보입니다.";

		if (Util.isEmpty(nickname))
			return "닉네임은 필수 입력 정보입니다.";

		if (Util.isEmpty(cellphoneNum))
			return "번호는 필수 입력 정보입니다.";

		if (Util.isEmpty(email))
			return "이메일은 필수 입력 정보입니다.";
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return loginId + "는 이미 가입된 아이디입니다.";
		}
		
		memberService.joinMember(loginId, loginPw, loginPwChk, name, nickname, cellphoneNum, email);

		int id = memberService.getLastInsertId();

		return memberService.getMemberById(id);

	}
}
