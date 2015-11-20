package bea.com.pojo;

/**
 * Lanmut entity. @author MyEclipse Persistence Tools
 */

public class Lanmut implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private String lanMuName;
	private Integer sort;
	private Integer moXingId;

	// Constructors

	/** default constructor */
	public Lanmut() {
	}

	/** full constructor */
	public Lanmut(Integer pid, String lanMuName, Integer sort, Integer moXingId) {
		this.pid = pid;
		this.lanMuName = lanMuName;
		this.sort = sort;
		this.moXingId = moXingId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getLanMuName() {
		return this.lanMuName;
	}

	public void setLanMuName(String lanMuName) {
		this.lanMuName = lanMuName;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getMoXingId() {
		return this.moXingId;
	}

	public void setMoXingId(Integer moXingId) {
		this.moXingId = moXingId;
	}

}