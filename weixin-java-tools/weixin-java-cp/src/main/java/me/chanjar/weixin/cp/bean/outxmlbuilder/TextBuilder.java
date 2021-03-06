package me.chanjar.weixin.cp.bean.outxmlbuilder;

import me.chanjar.weixin.cp.bean.WxCpXmlOutTextMessage;

/**
 * 文本消息builder
 * @author
 */
public final class TextBuilder extends BaseBuilder<TextBuilder, WxCpXmlOutTextMessage> {
	private String content;

	public TextBuilder content(String content) {
		this.content = content;
		return this;
	}

	public WxCpXmlOutTextMessage build() {
		WxCpXmlOutTextMessage m = new WxCpXmlOutTextMessage();
		setCommon(m);
		m.setContent(this.content);
		return m;
	}
}
