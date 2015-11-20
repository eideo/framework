package bea.com.pojo;

/**
 * Moxingt entity. @author MyEclipse Persistence Tools
 */

public class Moxingt implements java.io.Serializable {

	// Fields

	private Integer id;
	private String moXingName;

	// Constructors

	/** default constructor */
	public Moxingt() {
	}

	/** full constructor */
	public Moxingt(String moXingName) {
		this.moXingName = moXingName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoXingName() {
		return this.moXingName;
	}

	public void setMoXingName(String moXingName) {
		this.moXingName = moXingName;
	}

}