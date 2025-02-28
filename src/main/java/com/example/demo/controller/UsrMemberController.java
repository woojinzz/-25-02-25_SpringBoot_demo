package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

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
}
