package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.newer.cms.pojo.Language;

public interface LanguageMapper {
	/**
	 *  . 增加语种
	 * @param language
	 * @return
	 */
	@Insert("INSERT INTO `t_language`(lname,abbreviation,isactivation,tid) VALUES(#{lname},#{abbreviation},#{isactivation},#{site.tid})")
	int saveLanguage(Language language);
	
	/**
	 * . 修改语种
	 * @param language
	 * @return
	 */
	@Update("UPDATE `t_language` SET lname=#{lname},abbreviation=#{abbreviation},tid=#{site.tid} WHERE lid=#{lid};")
	int updateLanguage(Language language);
	
	/**
	 * 关联查询t_language     t_site
	 * 用来在修改时回显数据
	 * @return
	 */
	@Select("SELECT l.`lid`,l.`lname`,l.`abbreviation`,l.`isactivation`,l.`tid`,s.`tid` AS'site.tid', s.`tname` AS 'site.tname',s.`tintro` AS 'site.tintro' FROM `t_language` l\r\n" + 
			"LEFT JOIN `t_site` s\r\n" + 
			"ON l.`tid`=s.`tid`\r\n" + 
			"WHERE lid=#{lid}")
	Language queryLanguageS(@Param("lid") Integer lid);
	/**
	 * .删除语种
	 * @return
	 */
	@Delete("DELETE FROM t_language WHERE lid=#{lid}")
	int deleteLanguage(@Param("lid") Integer lid);
	
	/**
	 * .删除指定站点下的所有语种
	 * @return
	 */
	@Delete("DELETE FROM t_language WHERE tid=#{tid}")
	int deleteLanguages(@Param("tid") Integer tid);
	
	/**
	 * .根据id查询语种
	 * @param lid
	 * @return
	 */
	@Select("SELECT * FROM t_language WHERE lid=#{lid}")
	Language findLanguageById(@Param("lid") Integer lid);
	
	/**
	 * .获得该站点下所有语种的集合
	 * 
	 * @return
	 */
	@Select("SELECT a.lid ,a.lname,a.abbreviation,a.isactivation,b.uid AS 'user.uid',b.uname AS 'user.uname' FROM \r\n" + 
			"(SELECT l.`lid`,l.`lname`,l.`abbreviation`,l.`isactivation`,l.`tid`,t.uid FROM t_language l LEFT JOIN t_site_user t ON l.`tid`=t.`tid`) a\r\n" + 
			"LEFT JOIN t_user b ON a.tid=b.`uid` WHERE a.tid=#{tid}")
	List<Language> queryLanguage(@Param("tid") Integer tid);
	
	
	/**
	 * 查询语种信息数据总记录数
	 * 
	 * @return integer 用户数据总记录数
	 */
	@Select("SELECT COUNT(*) FROM `t_language` where tid=#{tid}")
	Integer getTotalLanguage(Integer tid);
	
	
	/**
	   * 获取从index开始，的pageSize条记录
	 * 
	 * @param index
	 * @param pageSize
	 * @return 用户数据集合
	 */
	@Select("SELECT l.`lid`,l.`lname`,l.`abbreviation`,l.`isactivation`,l.`tid`,s.`tid` AS'site.tid', s.`tname` AS 'site.tname',s.`tintro` AS 'site.tintro' FROM `t_language` l \r\n" + 
			"			LEFT JOIN `t_site` s \r\n" + 
			"			ON l.`tid`=s.`tid` WHERE s.tid=#{tid}  ORDER BY  l.lid DESC LIMIT #{index},#{pageSize}")
	List<Language> getPageByLanguage(@Param("index") Integer index, @Param("pageSize") Integer pageSize, @Param("tid") Integer tid);
	
	/**
	 * 更改激活状态
	 * @param language
	 * @return
	 */
	@Update("UPDATE `t_language` SET isactivation=#{isactivation} WHERE lid=#{lid}")
	Integer upStatus(Language language);
	
	/**
	 * 得到所有站点下的语种信息
	 * 
	 * @return 返回所有信息集合
	 */
	@Select("SELECT l.`lid`,l.`lname`,l.`abbreviation`,l.`isactivation`,s.`tid` AS 'site.tid',\r\n"
			+ "	s.`tname` AS 'site.tname',s.`tintro` AS 'site.tintro' FROM t_language l\r\n"
			+ "	 LEFT JOIN t_site s\r\n" + "	 ON	s.`tid`=l.`tid`")
	List<Language> findSiteAndLanguages();

}
