package com.newer.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.pojo.Channel;
import com.newer.cms.service.ChannelService;

/**
 * 
 * 牛耳教育,180801J: 频道控制器 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月22日
 */
@RestController
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	/**
	 * 通过pid 得到子频道信息
	 * 
	 * @param pid
	 * @return
	 */
	@GetMapping("/findzTreeByPid")
	public ResponseEntity<?> findChannelByPid(
			@RequestParam(value = "pid", required = false, defaultValue = "0") Integer pid) {
		// 调用服务层的方法
		List<Channel> data = channelService.findChannelByPid(pid);

		// 判断data是否为空
		if (data == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Channel>>(data, HttpStatus.OK);
	}

	/**
	 * 获取所有频道信息
	 * 
	 * @return
	 */
	@GetMapping("/findAllzTree")
	public ResponseEntity<?> findAllChannel() {

		// 调用服务层
		List<Channel> data = channelService.findAllChannel();

		// 判断data是否为空
		if (data == null || data.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Channel>>(data, HttpStatus.OK);
	}
}
