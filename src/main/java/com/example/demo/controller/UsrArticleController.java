package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");

		articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		
		int id = articleService.getLastInsertId();
		
		return Util.jsReplace(String.format("%d 번 글을 작성했습니다. ", id), String.format("detail?id=%d", id));
	}
	
	@GetMapping("/usr/article/write")
	public String write() {
//		Article article = articleService.forPrintArticle(id);
//		model.addAttribute("article", article);
		return "usr/article/write";
	}

	@GetMapping("/usr/article/list")
	public String list(Model model, int boardId) {
		
		String boardName = articleService.getBoardNameById(boardId);
		int articlesCnt =  articleService.getArticlesCnt(boardId);
		List<Article> articles= articleService.getArticles(boardId);
		
		model.addAttribute("articles", articles);
		model.addAttribute("boardName", boardName);
		model.addAttribute("articlesCnt", articlesCnt);
		
		return "usr/article/list";
	}

	@GetMapping("/usr/article/detail")
	public String detail(Model model, int id) {
		

		Article article = articleService.forPrintArticle(id);

		if (article == null) return "게시물이 없습니다";

		model.addAttribute("article", article);
		return "usr/article/detail";
	}
	
	@GetMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		Article article = articleService.forPrintArticle(id);
		model.addAttribute("article", article);
		return "usr/article/modify";
	}

	@PostMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		articleService.modifyAricle(id, title, body);
		return Util.jsReplace(String.format("%d 번 게시물을 수정했습니다.", id), String.format("detail?id=%d" ,id));
	}

	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpSession session, int id) {

		articleService.deleteArticle(id);

		return Util.jsReplace(String.format("%d 번 게시물을 삭제 했습니다.", id), "list");
	}

}
