package com.newer.cms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.model.Page;
import com.newer.cms.pojo.Site;
import com.newer.cms.pojo.SiteUser;
import com.newer.cms.service.SiteService;

@RestController
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@GetMapping("/getPageSiteUser")
	public ResponseEntity<?> getPageSiteUser(
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSizeStr", required = false, defaultValue = "10") Integer PageSizeStr,
			String name
		){
		System.out.println("PageSizeStr:"+PageSizeStr);
		Map<String,Object> param=new HashMap<>();
		param.put("pageNoStr", pageNoStr);
		param.put("pageSize", PageSizeStr);
		param.put("name", name);
		Page<SiteUser> page = siteService.getPageSiteUser(param);
		page.setStatus(HttpStatus.OK);
		System.out.println(page.toString());
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page>(page, HttpStatus.OK);
	}
	
	//删除站点
	@DeleteMapping("/site/{id}")
	public ResponseEntity<?> deleteSite(@PathVariable("id") Integer id){
				if (id == null) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				int i=siteService.deleteSite(id);
				return new ResponseEntity<>(i>0?"1":"0",HttpStatus.OK);
	}
	
	//添加站点
	@PostMapping("/sites")
	public ResponseEntity<?> addSite(@RequestBody Site site){
		System.out.println(site);
		int i=siteService.addSite(site);
		return new ResponseEntity<>(i>0?"1":"0",HttpStatus.OK);
	}

	//修改站点名
	@PostMapping("/putSite")
	public ResponseEntity<?> putSite(@RequestBody Site site){
		System.out.println(site);
		int i =siteService.putSite(site);
		return new ResponseEntity<>(i>0?"1":"0",HttpStatus.OK);
	}
}
