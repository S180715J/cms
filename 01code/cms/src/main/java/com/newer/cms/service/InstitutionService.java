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

import com.newer.cms.mapper.InstitutionMapper;
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

	public List<Institution> findInstitutionTree(Integer id) {
		// TODO Auto-generated method stub
		return institutionMapper.findInstitutionById(id);
	}

}
