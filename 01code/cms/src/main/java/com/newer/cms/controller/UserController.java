package com.newer.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.model.Page;
import com.newer.cms.pojo.User;
import com.newer.cms.pojo.UserRole;
import com.newer.cms.service.UserService;
import com.newer.cms.utils.JwtTokenUtil;

import io.jsonwebtoken.Claims;

/**
 * 
 * 牛耳教育,180801J: 用户控制器 <br>
 * 
 * @author Administrator 2019年3月16日
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Value("${jwt.header}")
	private String header;

	/**
	 * .测试能否得到数据（可用）
	 * 
	 * @return
	 */
	@GetMapping("/users")
	public String getUsers() {
		List<UserRole> queryUserRole = userService.queryUserRole();
		for (UserRole u : queryUserRole) {
			System.out.println(u.getUser().getUname());
			System.out.println("====================");
			System.out.println(u.getUser().getInstitution().getNamepath());
			System.out.println("====================");
			System.out.println(u.getRole());
		}
		return "ok";
	}

	/**
	 * 得到用户信息
	 * 
	 * @param pageNoStr
	 * @param PageSizeStr
	 * @return 分页效果的用户数据集合
	 */
	@SuppressWarnings("unused")
	@GetMapping("/pageUsers")
	public ResponseEntity<?> getPageByUser(
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSizeStr", required = false, defaultValue = "6") String PageSizeStr) {
		Integer pageSize = Integer.parseInt(PageSizeStr);
		System.out.println(pageSize);
		Page<UserRole> page = userService.getPageByUser(pageNoStr, pageSize);
		page.setStatus(HttpStatus.OK);
		System.out.println(page.toString());
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Page>(page, HttpStatus.OK);
	}

	/**
	 * 用户登录 ，并讲用户的信息放入token
	 * 
	 * @param userNmae
	 * @param password
	 * @return 成功返回token 否则返回状态码没有内容
	 */
	@GetMapping("/login")
	public ResponseEntity<?> login(String userName, String password) {

		// 通过用户名和密码查询用户
		UserRole userRole = userService.getUserRole(userName, password);

		if (userRole == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		// 用户存在则生成Token令牌
		String token = new JwtTokenUtil().creatJwt(userRole.getUser().getUname(), userRole.getUser().getUid(),
				userRole.getRole().getRid());
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	/**
	 * 通过request请求头获取token中的数据
	 * 
	 * @param request
	 * @return 返回userRole对象
	 */
	@GetMapping("/findToken")
	public ResponseEntity<?> findToken(HttpServletRequest request) {
		// 通过request获取请求头
		String token = request.getHeader(header);

		// 解析token
		Claims claims;
		try {
			claims = new JwtTokenUtil().parseJWT(token);
			String uname = (String) claims.get("uname");
			if (uname == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<String>(uname, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return 成功返回"ok",否则返回"error"
	 */
	@PostMapping("/User")
	public String saveUser(User user) {
		String str = userService.saveUser(user);
		return str;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return 成功返回"ok",否则返回"error"
	 */
	@PutMapping("/User")
	public String updateUser(@RequestBody User user) {
		System.out.println(user);
		String str = userService.updateUser(user);
		return str;
	}

	/**
	 * 根据id删除用户信息
	 * 
	 * @param id
	 * @return 成功返回"ok",否则返回"error"
	 */
	@DeleteMapping("/User/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {

		// 给id做判空处理
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		// 获取服务层返回的String
		String str = userService.deleteUser(id);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
}
