package com.newer.cms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.model.Page;
import com.newer.cms.pojo.Language;
import com.newer.cms.service.LanguageService;

@RestController
public class LanguageController {
  @Autowired
	private LanguageService languageService;
  
	
  /**
	 * 得到语种信息
	 * 
	 * @param pageNoStr
	 * @param PageSizeStr
	 * @return 分页效果的用户数据集合
	 */
	@SuppressWarnings("unused")
	@GetMapping("/pageLanguage/{tid}")
	public ResponseEntity<?> getPageByArticle(@PathVariable("tid") Integer tid,
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSizeStr", required = false, defaultValue = "10") Integer PageSizeStr) {
		Page<Language> page = languageService.getPageByArticle(pageNoStr, PageSizeStr,tid);
		page.setStatus(HttpStatus.OK);
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Page<Language>>(page, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteL/{lid}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteLanguage(@PathVariable("lid") Integer lid ){
		String delLanguage = languageService.deleteLanguage(lid);
		
		return new ResponseEntity<String>(delLanguage,HttpStatus.OK);
	}
	
	/**
	 * 根据id修改语种
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/updateLanguage/{lid}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateLanguage(@PathVariable("lid") Integer lid,@RequestBody Language language ){
		language.setLid(lid);
		String updateLanguage = languageService.updateLanguage(language);
		
		return new ResponseEntity<String>(updateLanguage,HttpStatus.OK);
		
	}
	
	/**
	 * 根据id查询语种
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/queryLanguage/{lid}",method=RequestMethod.GET)
	public ResponseEntity<?> queryLanguage(@PathVariable("lid") Integer lid){
		
		Language queryLanguage = languageService.queryLanguage(lid);
		
		return new ResponseEntity<Language>(queryLanguage,HttpStatus.OK);
		
	}
	
	
	/**
	 * 更改激活状态
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/upStatus",method=RequestMethod.PUT)
	public ResponseEntity<?> upStatus(@RequestBody Language language){
		String upStatus = languageService.upStatus(language);
		return new ResponseEntity<String>(upStatus,HttpStatus.OK);
	}
	
	
	
	/**
	 * 添加
	 * @param language
	 * @return
	 */
	@RequestMapping(value="/saveLanguage",method=RequestMethod.POST)
	public ResponseEntity<?> saveLanguage(@RequestBody Language language){
		language.setIsactivation(0);
		String upStatus = languageService.saveLanguage(language);
		return new ResponseEntity<String>(upStatus,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteLanguages")
	public ResponseEntity<?> deleteLanguages(@RequestParam("id[]") Integer[] id){
		String deleteLanguageAll = languageService.deleteLanguageAll(id);
		return new ResponseEntity<String>(deleteLanguageAll,HttpStatus.OK);
	}
	
	/**
	 * 获取所有展现下的语种信息
	 * 
	 * @return 成功返回所有语种信息集合 否则返回500错误码
	 */
	@GetMapping("/findSiteAndLanguage")
	public ResponseEntity<?> findeSiteAndLanguages() {
		// 调用服务层
		List<Language> data = languageService.findSiteAndLanguages();

		if (data == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Language>>(data, HttpStatus.OK);

	}
	
	
}
