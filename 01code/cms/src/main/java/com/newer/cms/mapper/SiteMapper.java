package com.newer.cms.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Site;

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
}
