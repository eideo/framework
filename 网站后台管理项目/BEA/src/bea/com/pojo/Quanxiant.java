package bea.com.pojo;

/**
 * Quanxiant entity. @author MyEclipse Persistence Tools
 */

public class Quanxiant implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleId;
	private String quanxianName;
	private String binary;

	// Constructors

	/** default constructor */
	public Quanxiant() {
	}

	/** full constructor */
	public Quanxiant(Integer roleId, String quanxianName, String binary) {
		this.roleId = roleId;
		this.quanxianName = quanxianName;
		this.binary = binary;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getQuanxianName() {
		return this.quanxianName;
	}

	public void setQuanxianName(String quanxianName) {
		this.quanxianName = quanxianName;
	}

	public String getBinary() {
		return this.binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}

}