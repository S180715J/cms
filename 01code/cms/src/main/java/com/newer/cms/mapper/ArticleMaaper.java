package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Article;

public interface ArticleMaaper {

	/**
	 * . 根据id查询文章
	 * 
	 * @param aid
	 * @return
	 */
	@Select("SELECT a.`aid`,a.`title`,a.`subhead`,a.`accessoryname`,a.`accessory`,a.`creattime`,a.`status`,a.`stick`,a.`author`,a.`aboutimg`,b.`eid` AS 'articletype.eid',b.`ename` AS 'articletype.ename' FROM t_article a LEFT JOIN t_articletype b ON a.`eid`=b.`eid` where aid=#{aid}")
	Article findArticleById(@Param("aid") Integer aid);

	/**
	 * .查询某频道下的所有文章
	 */
	@Select("SELECT a.`aid`,a.`title`,a.`subhead`,a.`accessoryname`,a.`accessory`,a.`creattime`,a.`status`,a.`stick`,a.`author`,a.`aboutimg`,b.`eid` AS 'articletype.eid',b.`ename` AS 'articletype.ename' FROM t_article a LEFT JOIN t_articletype b ON a.`eid`=b.`eid` where cid=#{cid}")
	List<Article> queryArticle(@Param("cid") Integer cid);

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
	@Update("UPDATE t_article SET title=#{title},subhead=#{subhead},accessory=#{accessory},accessoryname=#{accessoryname},mainbody=#{mainbody},stick=#{stick},author=#{author},aboutimg=#{aboutimg},eid=#{articletype.eid} WHERE aid=#{aid} ")
	int updateArticle(Article article);

	/**
	 * .删除文章
	 * 
	 * @return
	 */
	@Delete("DELETE FROM t_article WHERE aid=#{aid}")
	int deleteArticle(@Param("aid") Integer aid);

	List<Article> getPageByArticle(Integer index, Integer pageSize);

	Integer getTotalArticle();

}
