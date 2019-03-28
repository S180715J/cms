package com.newer.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Channel;
import com.newer.cms.pojo.ChannelArticle;

public interface ChannelMapper {

	/**
	 * . 添加频道
	 * 
	 * @param channel
	 * @return
	 */
	@Insert("INSERT INTO t_channel(cid,cname,calias,pid,cnamepath,cidpath,creattime as datetime,tsequence,lid) VALUES(#{cid},#{cname},#{calias},#{pid},#{cnamepath},#{cidpath},NOW(),#{tsequence},#{language.lid})")
	int saveChannel(Channel channel);

	/**
	 * .修改别名
	 */

	@Update("UPDATE t_channel SET cname=#{cname},cnamepath=#{cnamepath} WHERE cid=#{cid}")
	int updateChannel(Channel channel);

	/**
	 * .查询所有频道id路径带有指定名称的频道
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime as datetime,tsequence,lid AS 'language.lid' FROM t_channel where cidpath LIKE '%${cidpath}%'")
	List<Channel> queryChannelss(Channel channel);

	/**
	 * .修改序列号
	 */
	@Update("UPDATE t_channel SET tsequence=#{tsequence} WHERE cid=#{cid}")
	int updateChannels(Channel channel);

	/**
	 * 移动
	 * 
	 * @param channel
	 * @return
	 */
	@Update("UPDATE t_channel SET pid=#{pid},cnamepath=#{cnamepath},cidpath=#{cidpath} WHERE cid=#{cid}")
	int move(Channel channel);

	/**
	 * .根据id删除频道
	 */

	@Delete("DELETE FROM  t_channel WHERE cid=#{cid}")
	int deleteChannel(@Param("cid") Integer cid);

	/**
	 * .删除该频道下所有的子孙频道
	 */
	@Delete("DELETE FROM  t_channel WHERE  cidpath LIKE '%${cidpath}%'")
	int deleteChannels(@Param("cidpath") String cidpath);

	/**
	 * .查询指定id的频道
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime as datetime,tsequence FROM t_channel WHERE cid=#{cid} AND lid=#{lid}")
	Channel findChannelById(@Param("cid") Integer cid, @Param("lid") Integer lid);

	/**
	 * .获得所有的频道集合
	 * 
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime as datetime,tsequence,lid AS 'language.lid' FROM t_channel")
	List<Channel> queryChannel(Integer lid);

	/**
	 * .得到pid为xx的频道集合
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime as datetime,tsequence,lid AS 'language.lid' FROM t_channel where pid=#{cid}")
	List<Channel> queryChannels(@Param("cid") Integer cid);

	/**
	 * 得到cid的最大值
	 * 
	 * @return
	 */
	@Select("SELECT MAX(cid) FROM t_channel")
	int cidMax();

	/**
	 * 得到排序号的最大值
	 * 
	 * @return
	 */
	@Select("SELECT MAX(tsequence) FROM t_channel")
	int tsequenceMax();

	/**
	 * 查询总记录数
	 * 
	 * @param map
	 * @return
	 */
	int findCount(Map<String, Object> map);

	/**
	 * .分页查询指定语种id的频道
	 */
	List<Channel> findChannelAll(Map<String, Object> map);

	/**
	 * 根据频道id,新增文章id(频道文章关系表)
	 */
	@Insert("INSERT INTO t_channel_article(cid,aid) VALUES(#{channel.cid},#{article.aid})")
	int saveArticle(ChannelArticle channelArticle);

	/**
	 * 查询所有频道信息，用于构建树
	 * 
	 * @return
	 */
	@Select("SELECT cid,cname,calias,pid,xurl,icon_open AS 'iconOpen',lid AS 'language.lid',icon_close AS 'iconClose',icon FROM t_channel WHERE lid=#{id}")
	List<Channel> findAllChannel(Integer id);

	/**
	 * 将频道id添加到对应的语种下
	 * 
	 * @param channel
	 *            频道对象
	 * @param lid
	 *            语种id
	 * @return 返回影响行数
	 */
	@Insert("INSERT INTO t_channel (cname,calias,pid,lid,creattime) VALUES(#{cname},#{calias},#{pid},#{lid},#{datetime})")
	Integer svaeChannel(Channel channel);

	/**
	 * 根据 频道对象 与语种对象 查询对应的频道信息
	 * 
	 * @param channel
	 *            频道对象
	 * @param lid
	 *            语种id
	 * @return 查询到的频道对象
	 */
	@Select("SELECT cid,cname,calias,pid,cidpath, creattime AS 'datetime',tsequence FROM t_channel WHERE cname=#{cname} AND calias=#{calias} AND pid =#{pid} AND lid=#{lid}")
	Channel findChannelByName(Channel channel);

	/**
	 * 根据语种id删除所对应的频道
	 * 
	 * @param lid
	 *            语种id
	 */
	@Delete("DELETE FROM t_channel WHERE lid=#{lid}")
	void deleteChannelAll(Integer lid);

}
