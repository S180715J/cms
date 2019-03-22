package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Channel;

public interface ChannelMapper {

	/**
	 * . 添加频道
	 * 
	 * @param channel
	 * @return
	 */
	@Select("INSERT INTO t_channel(cid,cname,calias,pid,cnamepath,cidpath,creattime,tsequence,lid) VALUES(#{cid},#{cname},#{calias},#{pid},#{cnamepath},#{cidpath},NOW(),#{tsequence},#{language.lid})")
	int saveChannel(Channel channel);

	/**
	 * .修改别名
	 */

	@Update("UPDATE t_channel SET cname=#{cname},cnamepath=#{cnamepath} WHERE cid=#{cid}")
	int updateChannel(Channel channel);

	/**
	 * .查询所有频道id路径带有指定名称的频道
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime,tsequence,lid AS 'language.lid' FROM t_channel where cidpath LIKE '%#{cidpath}%'")
	List<Channel> queryChannelss(Channel channel);

	/**
	 * .修改序列号
	 */
	@Update("UPDATE t_channel SET tsequence=#{tsequence} WHERE cid=#{cid}")
	int updateChannels(Channel channel);

	/**
	 * .根据id删除频道
	 */

	@Delete("DELETE FROM  t_channel WHERE cid=#{cid}")
	int deleteChannel(@Param("cid") Integer cid);

	/**
	 * .删除该频道下所有的子频道
	 */
	@Delete("DELETE FROM  t_channel WHERE pid=#{pid}")
	int deleteChannels(@Param("pid") Integer pid);

	/**
	 * .查询指定id的频道
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime,tsequence FROM t_channel WHERE cid=1 AND lid=0")
	Channel findChannelById(@Param("cid") Integer cid, @Param("lid") Integer lid);

	/**
	 * .获得所有的频道集合
	 * 
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime,tsequence,lid AS 'language.lid' FROM t_channel ")
	List<Channel> queryChannel();

	/**
	 * .得到pid为xx的频道集合
	 */
	@Select("SELECT cid,cname,calias,pid,cnamepath,cidpath,creattime,tsequence,lid AS 'language.lid' FROM t_channel where pid=#{pid}")
	List<Channel> queryChannels(@Param("cid") Integer cid);

	/**
	 * 根据Pid 获取子频道信息
	 * 
	 * @param pid
	 * @return
	 */
	@Select("SELECT * FROM t_channel WHERE pid =#{pid} ORDER BY pid")
	List<Channel> findChannelByPid(Integer pid);

	/**
	 * 获取所有频道信息
	 * 
	 * @return
	 */
	@Select("SELECT * FROM t_channel ")
	List<Channel> findALLChannel();

}
