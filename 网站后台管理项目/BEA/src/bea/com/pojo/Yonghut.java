package bea.com.pojo;

import java.util.Date;

/**
 * Yonghut entity. @author MyEclipse Persistence Tools
 */

public class Yonghut implements java.io.Serializable {

	// Fields

	private Integer id;
	private String yongHuMing;
	private String realName;
	private String pwd;
	private String teltPhone;
	private String email;
	private Date createTime;
	private Integer bumenId;
	private Integer roleId;
	private Integer deLuCount;
	private Boolean zhuTai;
	private Boolean duorendenglu;

	// Constructors

	/** default constructor */
	public Yonghut() {
	}

	/** full constructor */
	public Yonghut(String yongHuMing, String realName, String pwd, String teltPhone, String email, Date createTime, Integer bumenId, Integer roleId, Integer deLuCount, Boolean zhuTai, Boolean duorendenglu) {
		this.yongHuMing = yongHuMing;
		this.realName = realName;
		this.pwd = pwd;
		this.teltPhone = teltPhone;
		this.email = email;
		this.createTime = createTime;
		this.bumenId = bumenId;
		this.roleId = roleId;
		this.deLuCount = deLuCount;
		this.zhuTai = zhuTai;
		this.duorendenglu = duorendenglu;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYongHuMing() {
		return this.yongHuMing;
	}

	public void setYongHuMing(String yongHuMing) {
		this.yongHuMing = yongHuMing;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTeltPhone() {
		return this.teltPhone;
	}

	public void setTeltPhone(String teltPhone) {
		this.teltPhone = teltPhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getBumenId() {
		return this.bumenId;
	}

	public void setBumenId(Integer bumenId) {
		this.bumenId = bumenId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDeLuCount() {
		return this.deLuCount;
	}

	public void setDeLuCount(Integer deLuCount) {
		this.deLuCount = deLuCount;
	}

	public Boolean getZhuTai() {
		return this.zhuTai;
	}

	public void setZhuTai(Boolean zhuTai) {
		this.zhuTai = zhuTai;
	}

	public Boolean getDuorendenglu() {
		return this.duorendenglu;
	}

	public void setDuorendenglu(Boolean duorendenglu) {
		this.duorendenglu = duorendenglu;
	}

}