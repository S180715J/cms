package com.newer.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.LanguageMapper;
import com.newer.cms.mapper.SiteMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Language;
import com.newer.cms.pojo.Site;
import com.newer.cms.pojo.SiteUser;

@Service
public class SiteService {
	
	@Autowired
	private SiteMapper siteMapper;
	
	@Autowired
	private LanguageMapper languageMapper;
	/**
	 *  .获得分页对象
	 * @param param
	 * @return
	 */
	@Transactional
	public Page<SiteUser> getPageSiteUser(Map<String,Object> param){
		//得到站点的总计录数
		int totalSite=siteMapper.getTotalSite(param);
		System.out.println(totalSite);
		String pageNoStr= (String) param.get("pageNoStr");
		//
		int pageSize= (int) param.get("pageSize");
		//获得page对象
		Page<SiteUser> page=new Page<>(pageNoStr, totalSite, pageSize);
		// 修正分页初始记录数
		Integer index = (page.getPageNo() - 1) * pageSize;
		param.put("index", index);
		//得到数据集合
		List<SiteUser> pageSiteUser = siteMapper.getPageSiteUser(param);
		page.setData(pageSiteUser);
		
		return page;
		
	}
	
	@Transactional
	public int deleteSite(Integer id) {
		int i = siteMapper.deleteSite(id);
		if(i>0) {
			List<Language> q = languageMapper.queryLanguage(id);
			System.out.println(q);
			if(q!=null) {
				languageMapper.deleteLanguages(id);
			}
		}
		return i;
	}

	public int addSite(Site site) {
		int i = siteMapper.saveSite(site);
		return i;
	}

	public int putSite(Site site) {
		int updateSite = siteMapper.updateSite(site);
		return updateSite;
	}

	public List<Site> getloadSites() {
		List<Site> list=	siteMapper.getLoadSites();
		
		return list;
	}

}
