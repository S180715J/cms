package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.ArticleMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Article;
import com.newer.cms.pojo.UserRole;

@Service
public class ArticleService {
   
	@Autowired
	private ArticleMapper articleMapper;
	
	
	/**
	 * 获取用户信息
	 * 
	 * @param pageNoStr
	 * @param pageSize
	 * @return 带分页效果的用户信息 Page对象
	 */
	public Page<Article> getPageByArticle(String pageNoStr, Integer pageSize) {
		// 得到用户信息总记录数
		Integer totalUser = articleMapper.getTotalArticle();
		// 实例化Page对象
		Page<Article> page = new Page<>(pageNoStr, totalUser, pageSize);
		// 修正分页初始记录数
		Integer index = (page.getPageNo() - 1) * pageSize;
		List<Article> data = articleMapper.getPageByArticle(index, pageSize);
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
		int i = articleMapper.deleteArticle(aid);

		return i > 0 ? "ok" : "error";
	}


	public Article findArticleById(Integer aid) {
		Article f = articleMapper.findArticleById(aid);
		return f;
	}

    @Transactional
	public String deleteArticleAll(Integer[] id) {
		int j=0;
		for (Integer i : id) {
			articleMapper.deleteArticle(i);
			j++;
		}
		return j==id.length?"1":"0";
	}


	public int getStatus(Integer aid) {
		Article status = articleMapper.getStatus(aid);
		return status.getStatus();
	}


	public int updateArticleById(Integer aid) {
		int i=articleMapper.updateArticleById(aid);
		return i;
	}


	public int updateArticleById2(Integer aid) {
		int i=articleMapper.updateArticleById2(aid);
		return i;
	}
}
