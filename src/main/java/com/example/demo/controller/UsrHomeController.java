package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UsrHomeController {
	
	
	UsrHomeController() {
	}
	
	@GetMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "메인페이지";
	}
	



}
