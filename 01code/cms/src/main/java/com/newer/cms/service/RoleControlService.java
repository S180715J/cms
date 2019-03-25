package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.RoleControlMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Control;
import com.newer.cms.pojo.Role;

/**
 * 
 * 牛耳教育,180801J: 角色权限服务层 <br>
 * 对权限，角色的操作， <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月23日
 */
@Service
@Transactional
public class RoleControlService {

	@Autowired
	private RoleControlMapper rCMapper;

	/**
	 * 获得角色权限<br>
	 * 分页处理效果
	 * 
	 * @param pageNoStr
	 *            当前页 默认为1
	 * @param pageSizeStr
	 *            每页显示记录数 默认10
	 * @return page对象 ，已处理好的角色权限分页对象
	 */
	public Page<Role> findPageRoleControls(String pageNoStr, String pageSizeStr) {
		// 1.调用接口，得到总记录数
		int totalRoleControl = rCMapper.findRoleControlByCount();

		// 创建Page对象
		Page<Role> page = new Page<>(pageNoStr, totalRoleControl, Integer.parseInt(pageSizeStr));

		// 修正初始记录数
		int index = (page.getPageNo() - 1) * Integer.parseInt(pageSizeStr);

		// 调用数据接口
		List<Role> data = rCMapper.findPageRoleControls(index, Integer.parseInt(pageSizeStr));

		page.setData(data);

		return page;
	}

	/**
	 * 获取所有权限
	 * 
	 * @return 成功返回List集合，否则返回null
	 */
	public List<Control> findControls() {
		// 调用数据层接口
		return rCMapper.findControls();
	}

	/**
	 * 新增角色
	 * 
	 * @param role
	 *            角色对象 <br>
	 *            新增角色 同时新增角色权限
	 * @return
	 */
	@Transactional
	public String saveRole(Role role) {

		// 判断角色是否存在 通过角色名去查询角色是否存在
		Role findRoel = rCMapper.findRole(role);
		if (findRoel != null) {
			return null;
		}

		// 2.调用数据接口，新增角色
		int i = rCMapper.saveRole(role);
		Role findRoleByDuty = null;
		if (i > 0) {
			// 3.新增成功则查询该角色 ,通过角色名和描述得到角色信息
			findRoleByDuty = rCMapper.findRoleByDuty(role);

		}
		// 4. 遍历添加角色权限
		List<Control> controls = role.getControls();
		int j = 0; // 影响行数

		for (Control control : controls) {
			j += rCMapper.svaeRoleControl(findRoleByDuty.getRid(), control.getControlId());
		}
		return j == controls.size() ? "ok" : null;
	}

	/**
	 * 根据id删除角色
	 * 
	 * @param rid
	 *            角色id 删除角色 同时要删除角色权限对应的关系 <br>
	 * @return 成功返回ok 否则返回null
	 * 
	 */
	@Transactional
	public String deleteRole(Integer rid) {

		// 1.删除角色
		int i = rCMapper.deleteRole(rid);
		// 删除成功则删除对应的角色权限关系
		int j = 0;
		if (i > 0) {
			j = rCMapper.deleteRoleAndControl(rid);
		}
		return j > 0 ? "ok" : null;
	}

	/**
	 * 根据角色id 得到角色权限信息
	 * 
	 * @param rid
	 *            角色id
	 * @return 成功返回角色对象Role 否则返回null
	 */
	public Role findRoleControlByRid(Integer rid) {
		// TODO Auto-generated method stub
		return rCMapper.finRoleControlByRid(rid);
	}
}
