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
	@Insert("INSERT INTO t_institution(iname,fid,namepath) VALUES(#{iname},#{fid},#{namepath})")
	int saveInstitution(Institution institution);

	/**
	 * .修改机构
	 * 
	 * @return
	 */
	@Update("UPDATE t_institution SET iname=#{iname},fid=#{fid},idpath=#{idpath},namepath=#{namepath} WHERE id=#{id}")
	int updateInstitutionByIdpath(Institution institution);

	/**
	 * .删除机构
	 */
	@Delete("DELETE FROM t_institution WHERE id=#{id}")
	int deleteInstitution(@Param("id") Integer id);

	/**
	 * .根据id查询部门
	 */
	@Select("SELECT id,iname,fid,idpath,namepath FROM t_institution  ")
	List<Institution> findInstitutions();

	/**
	 * .得到所有的部门
	 */
	@Select("SELECT id,iname,fid,idpath,namepath FROM t_institution")
	List<Institution> queryInstitution();

	/**
	 * 查询所有父部门
	 * 
	 * @param fid
	 * @return
	 */
	@Select("SELECT * FROM t_institution WHERE fid=#{fid}")
	List<Institution> findInstitutionByFid(Integer fid);

	/**
	 * 根据id或者fid查询机构
	 * 
	 * @param fid
	 * @return
	 */
	@Select("SELECT * FROM t_institution WHERE id=#{fid}")
	Institution findInstitutionById(Integer fid);

	/**
	 * 得到机构部门数据的总记录数
	 * 
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM t_institution")
	Integer findInstitutionCount();

	/**
	 * 得到从index开始 pageSize 条记录数
	 * 
	 * @param index
	 * @param pageSize
	 * @return institution pageSize条数据集合
	 */
	@Select("SELECT id,iname,fid,idpath,namepath FROM t_institution LIMIT #{index},#{pageSize}")
	List<Institution> findPageInstitution(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

	/**
	 * 通过 机构名，父机构的id，查询机构
	 * 
	 * @param institution
	 * @return
	 */
	@Select("SELECT id,iname,fid,idpath,namepath FROM t_institution WHERE iname=#{iname} AND fid=#{fid} ")
	Institution findInstitionByName(Institution institution);

	/**
	 * 得到所有机构信息
	 * 
	 * @return
	 */
	@Select("SELECT id,iname,fid,idpath,namepath FROM t_institution ")
	List<Institution> findPageInstitutionsAll();
}
