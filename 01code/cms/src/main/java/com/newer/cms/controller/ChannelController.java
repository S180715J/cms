package com.newer.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.model.Page;
import com.newer.cms.pojo.Channel;
import com.newer.cms.service.ChannelService;

@RestController
public class ChannelController {

	@Autowired
	private ChannelService channelService;

	/**
	 * 带条件查询所有
	 * 
	 * @param calias
	 * @param pageNoStr
	 * @param pageSize
	 * @param lid
	 * @return
	 */
	@GetMapping("/findChannelAll")
	public ResponseEntity<?> findChannelAll(String calias, Integer fid,
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "lid", required = false, defaultValue = "1") Integer lid) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNoStr", pageNoStr);
		map.put("pageSize", pageSize);
		map.put("lid", lid);
		map.put("calias", calias);
		map.put("fid", fid);
		Page<Channel> findChannelAll = channelService.findChannelAll(map);
		findChannelAll.setStatus(HttpStatus.OK);
		ResponseEntity<Page<Channel>> res = new ResponseEntity<Page<Channel>>(findChannelAll, HttpStatus.OK);
		return res;

	}

	/**
	 * 修改别名,先查询别名是否存在
	 * 
	 * @param channel
	 * @param lid
	 * @return
	 */
	@PutMapping("/updateChannel")
	public String updateChannel(Channel channel) {
		String calias = channel.getCalias();
		List<Channel> queryChannel = channelService.queryChannel(1);
		for (Channel channel2 : queryChannel) {
			if (channel2.getCalias() != calias) {
				channelService.updateChannel(channel);
				return "1";
			}
		}
		return "0";
	}

	/**
	 * 先调用查询序列号最大值，修改序列号
	 * 
	 * @param channel
	 * @return
	 */
	@PutMapping("/updateChannels")
	public ResponseEntity<?> updateChannels(Channel channel) {
		int i = channelService.updateChannels(channel);
		return new ResponseEntity<>(i > 0 ? "1" : "0", HttpStatus.OK);
	}

	/**
	 * 删除频道及子孙频道及文章
	 * 
	 * @param cid
	 * @return
	 */
	@DeleteMapping("/deleteChannel/{cid}")
	public ResponseEntity<?> deleteChannel(@PathVariable("cid") Integer cid) {

		if (cid != null) {
			System.out.println("cid:---" + cid);
			int i = channelService.deleteChannel(cid, 1);
			return new ResponseEntity<>(i > 0 ? "1" : "0", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 返回cid的最大值
	 * 
	 * @return
	 */
	@GetMapping("/cidMax")
	public ResponseEntity<?> cidMax() {
		int cidMax = channelService.cidMax();
		return new ResponseEntity<>(cidMax, HttpStatus.OK);
	}

	/**
	 * 返回排序号最大值
	 * 
	 * @return
	 */
	@GetMapping("/tsequenceMax")
	public ResponseEntity<?> tsequenceMax() {
		int tsequenceMax = channelService.tsequenceMax();
		return new ResponseEntity<>(tsequenceMax, HttpStatus.OK);
	}

	/**
	 * 验证频道别名是否相同，0相同，1不相同
	 * 
	 * @param calias
	 * @return
	 */
	@PostMapping("/queryCalias")
	public ResponseEntity<?> queryCalias(@PathVariable("calias") String calias) {
		List<Channel> list = channelService.queryChannel(1);
		for (Channel channel : list) {
			if (channel.getCalias() == calias) {
				return new ResponseEntity<>("0", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("1", HttpStatus.OK);

	}

	/**
	 * 添加频道
	 * 
	 * @param channel
	 * @return
	 */
	@PostMapping("/saveChannel")
	public ResponseEntity<?> saveChannel(Channel channel) {
		int i = channelService.saveChannel(channel, 1);
		return new ResponseEntity<>(i, HttpStatus.OK);
	}

	/**
	 * 移动
	 * 
	 * @param channel
	 * @return
	 */
	@PutMapping("/move")
	public ResponseEntity<?> move(Channel channel) {
		int i = channelService.move(channel, 1);
		return new ResponseEntity<>(i, HttpStatus.OK);
	}

	/**
	 * 复制
	 * 
	 * @param cid
	 * @param cid2
	 * @return
	 */
	@RequestMapping("/copy")
	public ResponseEntity<?> copy(@RequestParam("cid") Integer cid, @RequestParam("cid2") Integer cid2) {
		int i = channelService.copy(cid, cid2);
		return new ResponseEntity<>(i, HttpStatus.OK);

	}

	/**
	 * 获得所有频道信息
	 * 
	 * @return 成功返回所有频道信息集合，
	 */
	@GetMapping("/findAllChannel/{id}")
	public ResponseEntity<?> findAllChannel(@PathVariable("id") Integer id) {
		// 调用服务成
		List<Channel> data = channelService.findAllChannel(id);

		// 判断data是否为空
		if (data == null || data.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<List<Channel>>(data, HttpStatus.OK);
	}
	
	/**
	 * .获得所有频道
	 * @return
	 */
	@GetMapping("/getChannels")
	public ResponseEntity<?> getChannels(){
		List<Channel> list=channelService.getChannels();
		return new ResponseEntity<List<Channel>>(list,HttpStatus.OK);
	}
	
}
