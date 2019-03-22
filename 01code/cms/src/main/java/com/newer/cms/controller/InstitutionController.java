package com.newer.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.model.Page;
import com.newer.cms.pojo.Institution;
import com.newer.cms.service.InstitutionService;

/**
 * 
 * 牛耳教育,180801J: 机构控制层 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月18日
 */
@RestController
public class InstitutionController {

	@Autowired
	private InstitutionService inService;

	/**
	 * 用户添加所用的机构下拉框
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	@GetMapping("/findInstitution")
	public ResponseEntity<?> findInstitution(String code, Integer type) {

		// 调用服务层的方法
		List<Institution> data = inService.findInstitution(code, type);

		if (data == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Institution>>(data, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/findTree")
	public ResponseEntity<?> getInstitutionTree() {

		List<Institution> list = inService.findInstitutionTree();
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Institution>>(list, HttpStatus.OK);

	}

	/**
	 * 得到当前页的机构信息
	 * 
	 * @param pageNoStr
	 * @param pageSizeStr
	 * @return
	 */
	@GetMapping("/findPageInstitution")
	public ResponseEntity<?> findPageInstitutions(
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSizeStr", required = false, defaultValue = "10") String pageSizeStr) {

		// 将每页显示记录数转换
		Integer pageSize = Integer.parseInt(pageSizeStr);

		Page<Institution> page = inService.findPageInstitions(pageNoStr, pageSize);

		if (page == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		page.setStatus(HttpStatus.OK);
		return new ResponseEntity<Page<Institution>>(page, HttpStatus.OK);
	}

	/**
	 * 得到所有机构信息
	 * 
	 * @return
	 */
	@GetMapping("/findPageInstitutionAll")
	public ResponseEntity<?> findPageInstitutionsAll() {
		Page<Institution> page = inService.findPageInstitions();
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<Page>(page, HttpStatus.OK);
	}

	/**
	 * 新增机构
	 * 
	 * @param institution
	 * @return
	 */
	@PostMapping("/institution")
	public ResponseEntity<?> saveInstitution(@RequestBody Institution institution) {

		// 调用服务层的方法
		String data = inService.saveInstitution(institution);
		if (data == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	/**
	 * 新增子机构/部门
	 * 
	 * @param institution
	 * @return 成功返回ok
	 */
	@PostMapping("/mechanism")
	public ResponseEntity<?> saveMechanism(@RequestBody Institution institution) {
		System.out.println(institution);
		// 调用服务层
		String data = inService.saveMechanism(institution);
		if (data == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	/**
	 * 根据id得到机构信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/mechanism/{id}")
	public ResponseEntity<?> findInstitution(@PathVariable("id") Integer id) {
		// 调用服务层
		Institution institution = inService.findInstitution(id);
		if (institution == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Institution>(institution, HttpStatus.OK);
	}
}
