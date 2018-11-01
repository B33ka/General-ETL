package ge.umas.models;

import java.io.Serializable;

public class NameModel implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	String nameEng;
	String nameGeo;
	String transDirection;
	String transMethod;
	String source;

	public NameModel() {
	}

	public NameModel(String nameEng, String nameGeo, String transDirection, String transMetod, String source) {
		super();
		this.nameEng = nameEng.toUpperCase();
		this.nameGeo = nameGeo;
		this.transDirection = transDirection;
		this.transMethod = transMetod;
		this.source = source;
	}
	public NameModel(String nameEng, String nameGeo, String transDirection, String transMetod) {
		super();
		this.nameEng = nameEng.toUpperCase();
		this.nameGeo = nameGeo;
		this.transDirection = transDirection;
		this.transMethod = transMetod;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng.toUpperCase();
	}

	public String getNameGeo() {
		return nameGeo;
	}

	public void setNameGeo(String nameGeo) {
		this.nameGeo = nameGeo;
	}

	public String getTransDirection() {
		return transDirection;
	}

	public void setTransDirection(String transDirection) {
		this.transDirection = transDirection.toUpperCase();
	}

	public String getTransMetod() {
		return transMethod;
	}

	public void setTransMethod(String transMetod) {
		this.transMethod = transMetod.toUpperCase();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public String toString() {
		return  nameEng.toUpperCase()
				+ "," + nameGeo + "," + transDirection + "," +  transMethod + "," + source + "\n";
	}

}
