package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	private ArticleService articleService;
	private Rq rq;

	public UsrArticleController(ArticleService articleService, Rq rq) {
		this.articleService = articleService;
	}

	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, int boardId) {

		articleService.writeArticle(rq.getLoginedMemberId(), title, body, boardId);

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
	public String list(Model model, int boardId, @RequestParam(defaultValue = "1") int cPage,
			@RequestParam(defaultValue = "title") String searchKeywordType,
			@RequestParam(defaultValue = "") String searchKeyword) {

		String boardName = articleService.getBoardNameById(boardId);
		int articlesCnt = articleService.getArticlesCnt(boardId, searchKeywordType, searchKeyword);
		
		

		int itemsInAPage = 10; /* 한 페이지 데이터 수 */
		int limitFrom = (cPage - 1) * itemsInAPage; /* db 데이터 시작 위치 */

		List<Article> articles = articleService.getArticles(boardId, limitFrom, itemsInAPage, searchKeywordType, searchKeyword);

		int from = ((cPage - 1) / 10) * 10 + 1; // 1~10 이면 1, 11~20 = 2
		int end = (((cPage - 1) / 10) + 1) * 10;// * 10 으로 맨 마지막 페이지 구함

		int totalPageCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage);

		if (end >= totalPageCnt) {
			end = totalPageCnt;
		}

		model.addAttribute("cPage", cPage);
		model.addAttribute("from", from);
		model.addAttribute("end", end);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("articles", articles);
		model.addAttribute("boardName", boardName);
		model.addAttribute("articlesCnt", articlesCnt);

		return "usr/article/list";
	}

	@GetMapping("/usr/article/detail")
	public String detail(Model model, int id) {

		Article article = articleService.forPrintArticle(id);

		if (article == null)
			return "게시물이 없습니다";

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
		return Util.jsReplace(String.format("%d 번 게시물을 수정했습니다.", id), String.format("detail?id=%d", id));
	}

	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpSession session, int id) {

		articleService.deleteArticle(id);

		return Util.jsReplace(String.format("%d 번 게시물을 삭제 했습니다.", id), "list");
	}

}
