package com.newer.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Site;
import com.newer.cms.pojo.SiteUser;

public interface SiteMapper {
	
	/**
	 *  .添加站点
	 * @param site
	 * @return
	 */
	@Insert("INSERT INTO t_site(tname,tintro) VALUES(#{tname},#{tintro})")
	int saveSite(Site site);
	
	/**
	 * .删除站点
	 */
	@Delete("DELETE FROM t_site WHERE tid=#{tid}")
	int deleteSite(@Param("tid")Integer tid);

	
	/**
	 * .修改站点
	 */
	@Update("UPDATE  t_site SET tname=#{tname},tintro=#{tintro} WHERE tid=#{tid}")
	int updateSite(Site site);
	
	/**
	 * .查询所有站点的总记录数
	 */
	Integer getTotalSite(Map param);
	
	/**
	 * .查询条件为map 的站点集合
	 */
	
	List<SiteUser> getPageSiteUser(Map param);

	
}
