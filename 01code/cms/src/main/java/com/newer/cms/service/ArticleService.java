package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.cms.mapper.ArticleMaaper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Article;
import com.newer.cms.pojo.UserRole;

@Service
public class ArticleService {
   
	@Autowired
	private ArticleMaaper articleMaaper;
	
	
	/**
	 * 获取用户信息
	 * 
	 * @param pageNoStr
	 * @param pageSize
	 * @return 带分页效果的用户信息 Page对象
	 */
	public Page<Article> getPageByArticle(String pageNoStr, Integer pageSize) {
		// 得到用户信息总记录数
		Integer totalUser = articleMaaper.getTotalArticle();

		// 实例化Page对象
		Page<Article> page = new Page<>(pageNoStr, totalUser, pageSize);

		// 修正分页初始记录数
		Integer index = (page.getPageNo() - 1) * pageSize;
		System.out.println(totalUser + "......" + index + "...." + pageSize + "....." + page.getPageNo());
		List<Article> data = articleMaaper.getPageByArticle(index, pageSize);
		page.setData(data);
		return page;
	}
	
	
	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return String 成功返回“ok",否则返回"error“
	 */
	public String deleteArticle(Integer aid) {
		// 根据id删除用户数据 得到返回的影响函数
		int i = articleMaaper.deleteArticle(aid);

		return i > 0 ? "ok" : "error";
	}
}
