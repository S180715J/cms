package com.newer.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> getInstitutionTree(Integer id) {
		List<Institution> list = inService.findInstitutionTree(id);
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Institution>>(list, HttpStatus.OK);

	}
}
