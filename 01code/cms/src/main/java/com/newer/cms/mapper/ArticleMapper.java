package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Article;

public interface ArticleMapper {

	/**
	 * . 根据id查询文章
	 * 
	 * @param aid
	 * @return
	 */
	@Select("SELECT aid,title,subhead,mainbody,accessoryname,accessory,creattime,`status`,stick,author,aboutimg,cid AS `by`FROM t_article WHERE aid=#{aid}")
	Article findArticleById(@Param("aid") Integer aid);

	/**
	 * .查询某频道下的所有文章
	 */
	@Select("SELECT a.`aid`,a.`title`,a.`subhead`,a.`accessoryname`,a.`accessory`,a.`creattime`,a.`status`,a.`stick`,a.`author`,a.`aboutimg`,b.`eid` AS 'articletype.eid',b.`ename` AS 'articletype.ename' FROM t_article a LEFT JOIN t_articletype b ON a.`eid`=b.`eid` where cid=#{cid}")
	List<Article> queryArticle(@Param("cid") Integer cid);

	/**
	 * 查询所有文章
	 * 
	 * @return
	 */
	@Select("SELECT * FROM `t_article`")
	List<Article> queryListArticle();

	/**
	 * 查询文章信息数据总记录数
	 * 
	 * @return integer 用户数据总记录数
	 */
	@Select("SELECT COUNT(*) FROM t_article")
	Integer getTotalArticle();

	/**
	 * .添加文章
	 * 
	 * @param article
	 * @return
	 */
	@Insert("INSERT INTO t_article(title,subhead,mainbody,accessoryname,accessory,creattime,author,aboutimg,cid,stick)\r\n"
			+ " VALUES(#{title},#{subhead},#{mainbody},#{accessoryname},#{accessory},NOW(),#{author},#{aboutimg},#{by},#{stick})")
	Integer inserArticle(Article article);

	/**
	 * 获取从index开始，的pageSize条记录
	 * 
	 * @param index
	 * @param pageSize
	 * @return 用户数据集合
	 */
	@Select("SELECT * FROM t_article LIMIT #{index},#{pageSize}")
	List<Article> getPageByArticle(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

	/**
	 * .添加文章
	 * 
	 * @param article
	 * @return
	 */
	@Insert("INSERT INTO t_article(title,subhead,mainbody,accessoryname,accessory,eid,stick,author,aboutimg,cid) VALUES"
			+ "(#{title},#{subhead},#{mainbody},#{accessoryname},#{accessory},#{articletype.eid},#{stick},#{author},#{aboutimg},#{channel.cid})")
	int saveArticle(Article article);

	/**
	 * .修改文章
	 * 
	 * @return
	 */
	@Update("UPDATE t_article SET title=#{title},subhead=#{subhead},mainbody=#{mainbody},accessoryname=#{accessoryname},accessory=#{accessory},stick=#{stick},author=#{author},aboutimg=#{aboutimg},cid=#{by}  WHERE aid=#{aid};")
	int updateArticle(Article article);

	/**
	 * .删除文章
	 * 
	 * @return
	 */
	@Delete("DELETE FROM t_article WHERE aid=#{aid}")
	int deleteArticle(@Param("aid") Integer aid);

	@Delete("DELETE FROM t_article WHERE cid=#{cid}")
	void deleteArticleAll(Integer cid);

	@Select("SELECT * FROM t_article WHERE aid=#{aid}")
	Article getStatus(Integer aid);

	@Update("UPDATE t_article SET `status`=2 WHERE aid=#{aid}")
	int updateArticleById(Integer aid);

	@Update("UPDATE t_article SET `status`=1 WHERE aid=#{aid}")
	int updateArticleById2(Integer aid);

}
