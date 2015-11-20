package bea.com.pojo;

/**
 * Rolet entity. @author MyEclipse Persistence Tools
 */

public class Rolet implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String qian;
	private String describeD;

	// Constructors

	/** default constructor */
	public Rolet() {
	}

	/** full constructor */
	public Rolet(String name, String qian, String describeD) {
		this.name = name;
		this.qian = qian;
		this.describeD = describeD;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQian() {
		return this.qian;
	}

	public void setQian(String qian) {
		this.qian = qian;
	}

	public String getDescribeD() {
		return this.describeD;
	}

	public void setDescribeD(String describeD) {
		this.describeD = describeD;
	}

}