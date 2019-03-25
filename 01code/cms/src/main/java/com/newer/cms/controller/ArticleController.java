package com.newer.cms.controller;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.mapper.ArticleMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Article;
import com.newer.cms.pojo.UserRole;
import com.newer.cms.service.ArticleService;

@RestController
public class ArticleController {
	
	@Autowired
	private ArticleMapper  articleMaaper;
	
	@Autowired
	private ArticleService  articleService;
	
	@GetMapping("/queryListArticle")
	public ResponseEntity<?> queryListArticle(){
		List<Article> queryListArticle = articleMaaper.queryListArticle();
		return new ResponseEntity<List<Article>>(queryListArticle,HttpStatus.OK);
	}

	
	/**
	 * 得到用户信息
	 * 
	 * @param pageNoStr
	 * @param PageSizeStr
	 * @return 分页效果的用户数据集合
	 */
	@SuppressWarnings("unused")
	@GetMapping("/pageArticle")
	public ResponseEntity<?> getPageByArticle(
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSizeStr", required = false, defaultValue = "8") String PageSizeStr) {
		Integer pageSize = Integer.parseInt(PageSizeStr);
		Page<Article> page = articleService.getPageByArticle(pageNoStr, pageSize);
		page.setStatus(HttpStatus.OK);
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Page<Article>>(page, HttpStatus.OK);
	}
	
	
	/**
	 * 根据id删Article
	 * @param aid
	 * @return
	 */
	@RequestMapping(value="/deleteA/{aid}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteArticle(@PathVariable("aid") Integer aid){
		if(aid==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		String deleteArticle = articleService.deleteArticle(aid);
		return new ResponseEntity<String>(deleteArticle,HttpStatus.OK);
	}
	
	
	/**
	 * 添加Article
	 * @param aid
	 * @return
	 */
	@RequestMapping(value="/inserArticle",method=RequestMethod.POST)
	public ResponseEntity<?> inserArticle(@RequestBody Article article){
		System.out.println("添加");
		article.setCreattime(new Date());
		System.out.println(article);
		Integer inserArticle = articleMaaper.inserArticle(article);
		System.out.println(inserArticle);
		return new ResponseEntity<>(inserArticle>0?"1":"0",HttpStatus.OK);
	}
	
	/**
	 * 修改Article
	 * @param id
	 * @param article
	 * @return
	 */
	@RequestMapping(value="/updateAr/{id}",method=RequestMethod.POST)
	public ResponseEntity<?> updateArti(@PathVariable("id") Integer id,@RequestBody Article article){
		
		article.setAid(id);
		article.setStatus(0);
		article.setCreattime(new Date());
		System.out.println(article);
		int updateArticle = articleMaaper.updateArticle(article);
		
		return new ResponseEntity<String>(updateArticle>0?"ok":"no",HttpStatus.OK);
	}
	
	/**
	 * 修改Article
	 * @param id
	 * @param article
	 * @return
	 */
	@RequestMapping(value="/updateStatus",method=RequestMethod.PUT)
	public ResponseEntity<?> updateStatus(@RequestBody Article article){
		System.out.println("aid:  "+article);
		
		String updateStatus = articleService.updateStatus(article);
		
		return new ResponseEntity<String>(updateStatus,HttpStatus.OK);
	}
}
