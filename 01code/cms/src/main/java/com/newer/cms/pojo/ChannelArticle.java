package com.newer.cms.pojo;

import java.io.Serializable;

/**
 *  .频道文章关系类
 * @author Administrator
 *
 */
public class ChannelArticle implements Serializable {

	private static final long serialVersionUID = 1L;

	private Channel channel;
	private Article article;
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	@Override
	public String toString() {
		return "ChannelArticle [channel=" + channel + ", article=" + article + "]";
	}
	
}
