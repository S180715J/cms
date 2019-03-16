package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.newer.cms.pojo.Articletype;

public interface ArticletypeMapper {
	
	/**
	 * .获得所有文章类型集合
	 */

	@Select("SELECT * FROM t_articletype;")
	List<Articletype> queryArticletype();
}
