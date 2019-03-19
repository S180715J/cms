package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Institution;

public interface InstitutionMapper {
	/**
	 * .增加机构
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_institution(iname,fid,idpath,namepath) VALUES(#{iname},#{fid},#{idpath},#{namepath})")
	int saveInstitution(Institution institution);

	/**
	 * .修改机构
	 * 
	 * @return
	 */
	@Update(" ")
	int updateInstitution();

	/**
	 * .删除机构
	 */
	@Delete("DELETE FROM t_institution WHERE id=#{id}")
	int deleteInstitution(@Param("id") Integer id);

	/**
	 * .根据id查询部门
	 */
	@Select("SELECT id,iname,fid,idpath,namepath FROM t_institution WHERE id=#{id}")
	List<Institution> findInstitutionById(@Param("id") Integer id);

	/**
	 * .得到所有的部门
	 */
	@Select("SELECT id,iname,fid,idpath,namepath FROM t_institution")
	List<Institution> queryInstitution();

	@Select("SELECT * FROM t_institution WHERE fid=#{fid}")
	List<Institution> findInstitutionByFid(Integer fid);
}
