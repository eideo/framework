package bea.com.pojo;

import java.util.Date;

/**
 * Wenzhangt entity. @author MyEclipse Persistence Tools
 */

public class Wenzhangt implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private String title;
	private String content;
	private Integer dianJiShu;
	private Date faBuDtae;
	private String picture;
	private String author;
	private Integer sort;
	private Boolean shou;
	private Boolean zuo;
	private Boolean tuiJian;
	private Boolean zhiDing;
	private Integer lanMuId;
	private Boolean shenHeFou;

	// Constructors

	/** default constructor */
	public Wenzhangt() {
	}

	/** full constructor */
	public Wenzhangt(String url, String title, String content, Integer dianJiShu, Date faBuDtae, String picture, String author, Integer sort, Boolean shou, Boolean zuo, Boolean tuiJian, Boolean zhiDing, Integer lanMuId, Boolean shenHeFou) {
		this.url = url;
		this.title = title;
		this.content = content;
		this.dianJiShu = dianJiShu;
		this.faBuDtae = faBuDtae;
		this.picture = picture;
		this.author = author;
		this.sort = sort;
		this.shou = shou;
		this.zuo = zuo;
		this.tuiJian = tuiJian;
		this.zhiDing = zhiDing;
		this.lanMuId = lanMuId;
		this.shenHeFou = shenHeFou;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDianJiShu() {
		return this.dianJiShu;
	}

	public void setDianJiShu(Integer dianJiShu) {
		this.dianJiShu = dianJiShu;
	}

	public Date getFaBuDtae() {
		return this.faBuDtae;
	}

	public void setFaBuDtae(Date faBuDtae) {
		this.faBuDtae = faBuDtae;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getShou() {
		return this.shou;
	}

	public void setShou(Boolean shou) {
		this.shou = shou;
	}

	public Boolean getZuo() {
		return this.zuo;
	}

	public void setZuo(Boolean zuo) {
		this.zuo = zuo;
	}

	public Boolean getTuiJian() {
		return this.tuiJian;
	}

	public void setTuiJian(Boolean tuiJian) {
		this.tuiJian = tuiJian;
	}

	public Boolean getZhiDing() {
		return this.zhiDing;
	}

	public void setZhiDing(Boolean zhiDing) {
		this.zhiDing = zhiDing;
	}

	public Integer getLanMuId() {
		return this.lanMuId;
	}

	public void setLanMuId(Integer lanMuId) {
		this.lanMuId = lanMuId;
	}

	public Boolean getShenHeFou() {
		return this.shenHeFou;
	}

	public void setShenHeFou(Boolean shenHeFou) {
		this.shenHeFou = shenHeFou;
	}

}