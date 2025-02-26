package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		return articleService.writeArticle(title, body);
	}
	
	@GetMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}

	@GetMapping("/usr/article/showDetail")
	@ResponseBody
	public Object showDetail(int id) {

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}

		return foundArticle;
	}

	@GetMapping("/usr/article/doMoidfy")
	@ResponseBody
	public String doMoidfy(int id, String title, String body) {

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}

		articleService.moidfyArticle(foundArticle, title, body);

		return id + "번 게시물을 삭제 했습니다.";
	}

	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}

		articleService.deleteArticle(foundArticle);

		return id + "번 게시물을 삭제 했습니다.";
	}
	


}
