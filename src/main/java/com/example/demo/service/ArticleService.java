package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

@Service
public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void writeArticle(int memberId, String title, String body, int boardId) {
		this.articleDao.writeArticle(memberId, title, body, boardId);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}
	
	public Article forPrintArticle(int id) {
		return articleDao.forPrintArticle(id);
	}

	public List<Article> getArticles(int boardId, int limitFrom, int itemsInAPage, String searchKeywordType, String searchKeyword) {
		return this.articleDao.getArticles(boardId, limitFrom, itemsInAPage, searchKeywordType, searchKeyword);
	}

	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	public void modifyAricle(int id, String title, String body) {
		this.articleDao.modifyAricle(id, title, body);
	}

	public void deleteArticle(int id) {
		this.articleDao.deleteAricle(id);
	}

	public String getBoardNameById(int boardId) {
		return this.articleDao.getBoardNameById(boardId);
	}

	public int getArticlesCnt(int boardId, String searchKeywordType, String searchKeyword) {
		return this.articleDao.getArticlesCnt(boardId, searchKeywordType, searchKeyword);
	}

	public void increaseView(int id) {
		articleDao.increaseView(id);
		
	}





}
