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

	public void writeArticle(String title, String body) {
		this.articleDao.writeArticle(title, body);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

	public List<Article> getArticles() {
		return this.articleDao.getArticles();
	}

	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	public void modifyAricle(int id, String title, String body) {
		this.articleDao.modifyAricle(id, title, body);
	}

	public void deleteArticle(Article foundArticle) {
		this.articleDao.deleteAricle(foundArticle);
	}

}
