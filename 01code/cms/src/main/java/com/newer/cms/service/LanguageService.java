package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.ArticleMapper;
import com.newer.cms.mapper.ChannelMapper;
import com.newer.cms.mapper.LanguageMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Channel;
import com.newer.cms.pojo.Language;

@Service
public class LanguageService {
    @Autowired
	private LanguageMapper languageMapper;
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private ArticleMapper articleMapper;
	/**
	 * 获取用户信息
	 * 
	 * @param pageNoStr
	 * @param pageSize
	 * @return 带分页效果的用户信息 Page对象
	 */
   
	public Page<Language> getPageByArticle(String pageNoStr, Integer pageSize,Integer tid) {
		// 得到用户信息总记录数
		Integer totalLanguage = languageMapper.getTotalLanguage(tid);

		// 实例化Page对象
		Page<Language> page = new Page<>(pageNoStr, totalLanguage, pageSize);

		// 修正分页初始记录数
		Integer index = (page.getPageNo() - 1) * pageSize;
		System.out.println(totalLanguage + "......" + index + "...." + pageSize + "....." + page.getPageNo());
		List<Language> data = languageMapper.getPageByLanguage(index, pageSize,tid);
		page.setData(data);
		return page;
	}
	
	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return String 成功返回“ok",否则返回"error“
	 */
	public String deleteLanguage(Integer lid) {
		int deleteLanguage = languageMapper.deleteLanguage(lid);
		return deleteLanguage>0?"ok":"error";
	}
	
	
	/**
	 * 根据id修改用户
	 * 
	 * @param id
	 * @return String 成功返回“ok",否则返回"error“
	 */
	public String updateLanguage(Language language) {
		int updateLanguage = languageMapper.updateLanguage(language);
		return updateLanguage>0?"ok":"error";
	}
	
	/**
	 * 关联查询t_language     t_site
	 * 用来在修改时回显数据
	 * @return
	 */
	public Language queryLanguage(Integer lid){
		Language queryLanguage = languageMapper.queryLanguageS(lid);
		return queryLanguage;
	}
	
	/**
	 * 更改激活状态
	 * @param language
	 * @return
	 */
	public String upStatus(Language language) {
		Integer upStatus = languageMapper.upStatus(language);
		return upStatus>0?"ok":"no";
	}
	
	
	/**
	 * 添加
	 * @param language
	 * @return
	 */
	public String saveLanguage(Language language) {
		Integer upStatus = languageMapper.saveLanguage(language);
		return upStatus>0?"ok":"no";
	}
	
	@Transactional
	public String deleteLanguageAll(Integer[] id) {
		int i=0;
		for (Integer j : id) {
			//查询语种id为j的所有频道
			List<Channel> queryChannel = channelMapper.queryChannel(j);
			if(queryChannel!=null) {
				channelMapper.deleteChannelAll(j);
				for (Channel l : queryChannel) {
					articleMapper.deleteArticleAll(l.getCid());
				}
			}
			languageMapper.deleteLanguage(j);
			i++;
		}
		return i==id.length?"1":"0";
	}
	
	/**
	 * 获取所有站点下的语种信息
	 * 
	 * @return 成功返回List结合 所有语种结合 否则返回null
	 */
	public List<Language> findSiteAndLanguages() {
		// 调用数据接口得到数据
		return languageMapper.findSiteAndLanguages();
	}
	
}
