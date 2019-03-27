package com.newer.cms.service;
/**
 * 
 * 牛耳教育,180801J:
 * 		机构服务层           <br>
 * @author 朱璐(LuZhu)
 * 2019年3月18日
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.InstitutionMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Institution;

@Service
public class InstitutionService {

	@Autowired
	private InstitutionMapper institutionMapper;

	/**
	 * 根据父机构 code，查询所有部门
	 * 
	 * @param code
	 *            父部门
	 * @param type
	 * @return 查询到的父部门集合
	 */
	public List<Institution> findInstitution(String code, Integer type) {
		// 判断检索数据的类型
		Integer fid = null;
		try {
			fid = Integer.parseInt(code);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return null;
		}
		List<Institution> data = null;
		if (type == 0) {
			data = institutionMapper.findInstitutionByFid(0);
		}
		if (type == 1) {
			data = institutionMapper.findInstitutionByFid(fid);
		}
		if (type == 2) {
			data = institutionMapper.findInstitutionByFid(fid);
		}

		return data;
	}

	/**
	 * 得到所有机构。
	 * 
	 * @param id
	 * @return
	 */
	public List<Institution> findInstitutionTree() {
		// TODO Auto-generated method stub
		return institutionMapper.findInstitutions();
	}

	/**
	 * 根据分页查询所有机构信息
	 * 
	 * @param pageNoStr
	 * @param pageSize
	 * @return
	 */
	public Page<Institution> findPageInstitions(String pageNoStr, Integer pageSize) {
		// 得到机构信息总记录数
		Integer count = institutionMapper.findInstitutionCount();

		// 调用Page对象的构造函数
		Page<Institution> page = new Page<>(pageNoStr, count, pageSize);

		// 初始化开始查询信息的坐标
		Integer index = (page.getPageNo() - 1) * pageSize;

		// 查询机构信息数据集合
		List<Institution> data = institutionMapper.findAllInstitution();

		page.setData(data);

		return page;
	}

	/**
	 * 添加机构/部门
	 * 
	 * @param institution
	 * @return 成功返回ok,
	 */
	@Transactional
	public String saveInstitution(Institution institution) {

		// 判断数据库中是否存在，该机构
		Institution inst = institutionMapper.findInstitionByName(institution);
		if (inst != null) {
			return null;
		}
		// 添加机构
		int i = institutionMapper.saveInstitution(institution);
		// 如果成功，则查询机构id 并放入idpath中，
		Institution inst2 = null;
		if (i > 0) {
			inst2 = institutionMapper.findInstitionByName(institution);
		}
		int falg = 0;
		// 进行更新idpath
		if (inst2 != null) {
			inst2.setIdpath("/" + inst2.getId());
			falg = institutionMapper.updateInstitutionByIdpath(inst2);
		}

		return falg > 0 ? "ok" : null;
	}

	/**
	 * 添加子机构/部门 （进行事务处理）
	 * 
	 * @param institution
	 * @return 成功返回OK，否则返回null,
	 */
	@Transactional
	public String saveMechanism(Institution institution) {

		// 1.查询是否有重复的机构
		Institution inst = institutionMapper.findInstitionByName(institution);
		if (inst != null) {
			return null;
		}

		// 2.将机构信息添加至表
		int i = institutionMapper.saveInstitution(institution);
		Institution institutionById = null;
		Institution institionByName = null;
		if (i > 0) {
			// 3.如果添加成功，则查询父机构 与刚刚添加的机构
			institutionById = institutionMapper.findInstitutionById(institution.getFid());

			// 查询刚刚添加的机构
			institionByName = institutionMapper.findInstitionByName(institution);
			System.out.println(institionByName);
		}

		// 4.更新idpath与namepath
		institionByName.setIdpath(institutionById.getIdpath() + "/" + institionByName.getId());
		institionByName.setNamepath(institutionById.getNamepath() + "/" + institionByName.getIname());

		// 更新字段 idpaht 与namepath
		int j = institutionMapper.updateInstitutionByIdpath(institionByName);
		System.out.println(institution);
		return j > 0 ? "ok" : null;
	}

	/**
	 * 得到所有机构信息 用于构建 机构的Tree
	 * 
	 * @return 成功返回List 机构信息集合
	 */
	public List<Institution> getAllInstitution() {
		// TODO Auto-generated method stub
		return institutionMapper.findAllInstitution();
	}

	/**
	 * 根据id删除当前以及子孙机构
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public String deleteInstitution(Integer id) {

		// 1.通过id查询当前机构 得到其机构的ipath
		Institution institution = institutionMapper.findInstitutionById(id);

		// 得到当前对象的idpath
		String idpath = institution.getIdpath();

		// 2.先通过模糊条件匹配 删除当前频道以及子孙频道

		int i = institutionMapper.deleteInstitution(idpath);

		return i > 0 ? "ok" : null;
	}

	@Transactional
	public String updateInstitution(Institution institution) {
		// 1.通过id查询当前机构
		Institution inst = institutionMapper.findInstitutionById(institution.getId());

		// 2. 得到namepath包含为修改前的机构名的所有机构集合
		List<Institution> list = institutionMapper.findInstitutionByNamepath(inst.getIname());

		// 3循环将查询到的机构路径给替换掉
		int i = 0;
		for (Institution ins : list) {

			// 1>将编辑前的机构名路径中的机构名替换成修改后的机构名
			String str = ins.getNamepath().replaceAll(inst.getIname(), institution.getIname());

			// 2>将替换掉的机构路径进行更新
			ins.setNamepath(str);
			i += institutionMapper.updateInstitutionByIdpath(ins);

		}
		// 修改当前机构的机构名

		int j = institutionMapper.updateInstitutionByIname(institution.getIname(), institution.getId());

		// 判断是否修改成功
		boolean falg = j > 0;
		if (!falg) {
			return null;
		}
		return i > 0 ? "ok" : null;
	}

}
