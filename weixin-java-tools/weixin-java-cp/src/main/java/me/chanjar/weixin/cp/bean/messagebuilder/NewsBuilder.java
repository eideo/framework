package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.WxCpMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * 
 * <pre>
 * 用法:
 * WxCustomMessage m = WxCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 * @author
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

	private List<WxCpMessage.WxArticle> articles = new ArrayList<WxCpMessage.WxArticle>();

	public NewsBuilder() {
		this.msgType = WxConsts.CUSTOM_MSG_NEWS;
	}

	public NewsBuilder addArticle(WxCpMessage.WxArticle article) {
		this.articles.add(article);
		return this;
	}

	public WxCpMessage build() {
		WxCpMessage m = super.build();
		m.setArticles(this.articles);
		return m;
	}
}
