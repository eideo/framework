package bea.com.pojo;

/**
 * Bument entity. @author MyEclipse Persistence Tools
 */

public class Bument implements java.io.Serializable {

	// Fields

	private Integer id;
	private String buMenName;

	// Constructors

	/** default constructor */
	public Bument() {
	}

	/** full constructor */
	public Bument(String buMenName) {
		this.buMenName = buMenName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuMenName() {
		return this.buMenName;
	}

	public void setBuMenName(String buMenName) {
		this.buMenName = buMenName;
	}

}