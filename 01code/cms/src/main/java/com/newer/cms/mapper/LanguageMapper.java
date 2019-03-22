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
	@Insert("INSERT INTO t_language(lname,abbreviation,isactivation,role,tid) VALUES(#{lname},#{abbreviation},#{isactivation},#{role.rid},#{site.tid})")
	int saveLanguage(Language language);
	
	/**
	 * . 修改语种
	 * @param language
	 * @return
	 */
	@Update("UPDATE t_language SET lname=#{lname},abbreviation=#{abbreviation},isactivation=#{isactivation} ,role=#{role.rid},tid=#{site.tid} WHERE lid=#{lid}")
	int updateLanguage(Language language);
	
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
	 * @return
	 */
	@Select("SELECT a.lid ,a.lname,a.abbreviation,a.isactivation,b.uid AS 'user.uid',b.uname AS 'user.uname' FROM \r\n" + 
			"(SELECT l.`lid`,l.`lname`,l.`abbreviation`,l.`isactivation`,l.`tid`,t.uid FROM t_language l LEFT JOIN t_site_user t ON l.`tid`=t.`tid`) a\r\n" + 
			"LEFT JOIN t_user b ON a.tid=b.`uid` WHERE a.tid=#{tid}")
	List<Language> queryLanguage(@Param("tid") Integer tid);

}
