package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.ChannelMapper;
import com.newer.cms.pojo.Channel;

/**
 * 
 * 牛耳教育,180801J: 频道服务层 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月22日
 */
@Transactional
@Service
public class ChannelService {

	@Autowired
	private ChannelMapper cMapper;

	/**
	 * 根据pid得到子频道的信息
	 * 
	 * @param pid
	 * @return 成功返回lsit集合，否则返回null
	 */
	public List<Channel> findChannelByPid(Integer pid) {

		return cMapper.findChannelByPid(pid);
	}

	/**
	 * 获取所有频道信息
	 * 
	 * @return 成功返回list集合
	 */
	public List<Channel> findAllChannel() {
		// TODO Auto-generated method stub
		return cMapper.findALLChannel();
	}
}
