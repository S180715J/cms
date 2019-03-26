package com.newer.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.ArticleMaaper;
import com.newer.cms.mapper.ChannelMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.Article;
import com.newer.cms.pojo.Channel;
import com.newer.cms.pojo.ChannelArticle;
import com.newer.cms.pojo.Language;

@Service
public class ChannelService {

	@Autowired
	private ChannelMapper channelMapper;

	@Autowired
	private ArticleMaaper articleMaaper;

	/**
	 * 通过lid查询所有
	 * 
	 * @param lid
	 * @return
	 */
	public List<Channel> queryChannel(Integer lid) {
		List<Channel> list = channelMapper.queryChannel(lid);
		return list;
	}

	/**
	 * 添加频道 判断cid是否存在 判断排序号是否存在
	 * 
	 * @param channel
	 * @return
	 */
	public int saveChannel(Channel channel, Integer lid) {

		// 得到传入的别名
		String calias = channel.getCalias();
		// 将得到的别名传入频道名称中
		channel.setCname(calias);

		// 得到父路径名
		String cnamepath = channel.getCnamepath();
		// 拼接父路径名，传入对象中
		String cnamepaths = "/" + cnamepath;
		channel.setCnamepath(cnamepaths);

		// 得到传入的cid
		Integer cid = channel.getCid();
		// 得到传入的pid
		Integer pid = channel.getPid();
		// 通过id查询父路径id，在token中取得lid
		Channel byId = channelMapper.findChannelById(pid, lid);
		String cidpaths = byId.getCidpath();
		// 得到新的父路径id
		String cidpath = cidpaths + "/" + cid;
		channel.setCidpath(cidpath);

		int i = channelMapper.saveChannel(channel);
		return i;
	}

	/**
	 * 删除频道及其子孙频道及频道下的文章
	 * 
	 * @param cid
	 * @return
	 */
	@Transactional
	public int deleteChannel(Integer cid, Integer lid) {
		// 通过cid查询得到id全路劲，进行拼接，一遍删除子孙频道
		Channel channel = channelMapper.findChannelById(cid, lid);
		String cidpath = channel.getCidpath();
		String cidpaths = cidpath + "/";
		channel.setCidpath(cidpaths);
		// 删除父频道
		int i = channelMapper.deleteChannel(cid);

		if (i > 0) {
			// 删除父频道下的文章
			articleMaaper.deleteArticle(cid);

			// 通过id全路径模糊查询父频道下的所有子孙频道

			List<Channel> list = channelMapper.queryChannelss(channel);
			System.out.println(channel);
			if (list != null) {
				// 通过id全路径模糊删除所有子孙频道
				int j = channelMapper.deleteChannels(cidpath + "/");
				// 遍历所有子孙频道得到cid
				for (Channel c : list) {
					// 删除所有子孙频道下的文章
					articleMaaper.deleteArticle(c.getCid());
				}
				return i + j;
			}
		}
		return i;
	}

	/**
	 * 修改别名
	 * 
	 * @param channel
	 * @return
	 */
	@Transactional
	public int updateChannel(Channel channel) {
		// 通过id得到原有的对象
		Channel channel2 = channelMapper.findChannelById(channel.getCid(), channel.getLanguage().getLid());
		// 得到原数据库存入的全id路径
		String calias2 = channel2.getCidpath();
		// 拼接成新的路径，用户查询子频道
		String cidpath = calias2 + "/";
		channel2.setCidpath(cidpath);

		// 得到传入的别名
		String calias = channel.getCalias();
		// 用传入的别名替换频道全路径名称
		String replaceAll = channel.getCnamepath().replaceAll(calias2, "/" + calias);
		// 将新的频道全路径名称存入对象
		channel.setCnamepath(replaceAll);
		// 修改父频道别名
		int i = channelMapper.updateChannel(channel);
		if (i > 0) {
			// 通过全id路径查询所有子孙频道
			List<Channel> list = channelMapper.queryChannelss(channel2);
			if (list != null) {
				int j = 0;
				for (Channel c : list) {
					// 用传入的别名替换子孙频道全路径名称
					String replace = c.getCnamepath().replaceAll(channel2.getCalias(), calias);
					c.setCnamepath(replace);
					// 修改
					j = channelMapper.updateChannel(c);
					j++;
				}
				return i + j;
			}
		}
		return i;
	}

	/**
	 * 修改序列号 先查询是否存在
	 * 
	 * @return
	 */
	public int updateChannels(Channel channel) {
		Integer tsequence = channel.getTsequence();
		List<Channel> queryChannel = channelMapper.queryChannel(1);
		for (Channel channel2 : queryChannel) {
			if (channel2.getTsequence() != tsequence) {
				int i = channelMapper.updateChannels(channel);
				return i;
			}
		}
		return 0;

	}

	/**
	 * 移动 需要原路径的cid、别名 移动到的父路径的cid、cnamepath、cidpath 父路径的cid放入原路径的pid
	 * 
	 * @param channel
	 * @param lid
	 * @return
	 */
	@Transactional
	public int move(Channel channel, Integer lid) {
		// 得到传入的别名
		String calias = channel.getCalias();
		// 得到传入的父路径名
		String cnamepath = channel.getCnamepath();
		// 得到新的父路径名
		String cnamepathNew = cnamepath + "/" + calias;
		channel.setCnamepath(cnamepathNew);

		// 得到父路径单id
		Integer pid = channel.getCid();
		// 通过id查询
		Channel byId = channelMapper.findChannelById(pid, lid);
		// 得到父路径的cidpath
		String cidpaths = byId.getCidpath();
		// 得到传入的cid
		Integer cid = channel.getCid();
		// 拼接新的ciapath
		String cidpath = cidpaths + "/" + cid;
		channel.setCidpath(cidpath);

		// 移动
		int move = channelMapper.move(channel);
		return move;
	}

	/**
	 * 复制 cid被复制的频道 cid2复制到的频道
	 * 
	 * @param channel
	 * @return
	 */
	@Transactional
	public int copy(Integer cid, Integer cid2) {
		// 查询指定id的被复制的频道
		Channel channel = channelMapper.findChannelById(cid, 1);
		// 得到被复制的频道的名称
		String cname = channel.getCname();
		String calias = channel.getCalias();
		Integer tsequence = channel.getTsequence();

		// 查询指定id的复制到的频道
		Channel channel2 = channelMapper.findChannelById(cid2, 1);
		// 得到复制到的路径的cid
		Integer newcid = channel2.getCid();
		// 得到复制到的路径的cidpath
		String cidpath = channel2.getCidpath();
		// 得到复制到的路径的cnamepath
		String cnamepath2 = channel2.getCnamepath();

		// 得到新的cid
		int cidMax = channelMapper.cidMax();
		++cidMax;

		// 得到被复制的路径的cnamepath
		String cnamepath = channel.getCnamepath();
		String[] strings = cnamepath.split("/" + calias);
		String str = strings[1];
		// 得到新的cnamepath
		String newcnamepath = cnamepath2 + "/" + cidMax + str;

		// 得到被复制的路径的cidpath
		String cidpath2 = channel.getCidpath();
		String[] split = cidpath2.split("/" + cid);
		String str2 = split[1];
		// 得到新的cidpath
		String newcidpath = cidpath + "/" + calias + str2;

		// 得到新的语言
		Language language = new Language();
		language.setLid(1);
		// 创建新的对象
		Channel channel3 = new Channel(cidMax, cname, calias, newcid, newcnamepath, newcidpath, tsequence, language);

		// 复制成新的频道
		int saveChannel = channelMapper.saveChannel(channel3);

		// 给新复制的频道添加文章
		Channel channel4 = new Channel(cidMax);
		// 得到被移动频道下的所有文章
		List<Article> queryArticle = articleMaaper.queryArticle(cid);
		if (queryArticle != null) {
			for (Article article : queryArticle) {
				// 得到被移动频道下的所有文章id
				Integer aid = article.getAid();
				Article article2 = new Article();
				article2.setAid(aid);
				// 频道文章关系表
				ChannelArticle channelArticle = new ChannelArticle(channel4, article2);
				// 添加文章频道关系
				channelMapper.saveArticle(channelArticle);
			}
		}
		return saveChannel;
	}

	/**
	 * 带条件查询
	 * 
	 * @param map
	 * @param pageNoStr
	 * @return
	 */
	public Page<Channel> findChannelAll(Map<String, Object> map) {
		// 查询数据库中的总记录数
		int totalRecordNo = channelMapper.findCount(map);

		// pageSize常量
		int pageSize = (int) map.get("pageSize");

		// 得到当前页
		String pageNoStr = (String) map.get("pageNoStr");

		// 创建page对象
		Page<Channel> page = new Page<Channel>(pageNoStr, totalRecordNo, pageSize);

		// 先拿到修正后的pageNo
		int index = (page.getPageNo() - 1) * pageSize;
		map.put("index", index);
		// 查询数据库中的数据
		List<Channel> list = channelMapper.findChannelAll(map);

		// 将数据集合放入page中
		page.setData(list);

		return page;
	}

	/**
	 * cid最大值
	 * 
	 * @return
	 */
	public int cidMax() {
		int cidMax = channelMapper.cidMax();
		return cidMax;
	}

	/**
	 * 排序号最大值
	 * 
	 * @return
	 */
	public int tsequenceMax() {
		int tsequenceMax = channelMapper.tsequenceMax();
		return tsequenceMax;
	}

	/**
	 * 获得所有频道信息
	 * 
	 * @return 成功返回list 所有频道集合
	 */
	public List<Channel> findAllChannel(Integer id) {
		// TODO Auto-generated method stub
		return channelMapper.findAllChannel(id);
	}

}
