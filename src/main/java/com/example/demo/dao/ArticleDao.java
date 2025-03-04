package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {

	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{memberId}
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int memberId, String title, String body);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT A.*, M.NICKNAME `writerName`
				FROM article A
				INNER JOIN `member` M
				ON A.memberId = M.id
				ORDER BY A.id DESC
			""")
	public List<Article> getArticles();
	
	@Select("""
			SELECT A.*, M.nickname `writerName`
				FROM article A
				INNER JOIN `member` M
				ON A.memberId = M.id
				WHERE A.id = #{id}
				ORDER BY A.id DESC
			""")	
	public Article forPrintArticle(int id);

	@Select("""
			SELECT *
				FROM article
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);

	@Update("""
			<script>
			UPDATE article
				SET updateDate = NOW()
					<if test= "title != null and title != ''">
						, title = #{title}
					</if>
					<if test= "body != null and body != ''">
						, `body` = #{body}
					</if>
				WHERE id = #{id}
			</script>
			""")
	public void modifyAricle(int id, String title, String body);

	@Delete("""
			DELETE FROM article
				WHERE id = #{id}
			""")
	public void deleteAricle(Article foundArticle);

	

}
