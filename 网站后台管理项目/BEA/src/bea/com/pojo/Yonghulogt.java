package bea.com.pojo;

/**
 * Yonghulogt entity. @author MyEclipse Persistence Tools
 */

public class Yonghulogt implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer yongHuId;
	private String loginIp;
	private String loginTime;
	private String lgoutTime;
	private Boolean dengChuFou;

	// Constructors

	/** default constructor */
	public Yonghulogt() {
	}

	/** full constructor */
	public Yonghulogt(Integer yongHuId, String loginIp, String loginTime, String lgoutTime, Boolean dengChuFou) {
		this.yongHuId = yongHuId;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
		this.lgoutTime = lgoutTime;
		this.dengChuFou = dengChuFou;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYongHuId() {
		return this.yongHuId;
	}

	public void setYongHuId(Integer yongHuId) {
		this.yongHuId = yongHuId;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLgoutTime() {
		return this.lgoutTime;
	}

	public void setLgoutTime(String lgoutTime) {
		this.lgoutTime = lgoutTime;
	}

	public Boolean getDengChuFou() {
		return this.dengChuFou;
	}

	public void setDengChuFou(Boolean dengChuFou) {
		this.dengChuFou = dengChuFou;
	}

}