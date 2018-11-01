package ge.umas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SDA_Citizenship {

	// -- With all capital -- \\
	@SerializedName("LNAME")
	@Expose
	private String lNAME;

	@SerializedName("LNAMEEN")
	@Expose
	private String lNAMEEN;

	@SerializedName("FNAME")
	@Expose
	private String fNAME;

	@SerializedName("FNAMEEN")
	@Expose
	private String fNAMEEN;

	// -- With CamelCase -- \\
	@SerializedName("Lname")
	@Expose
	private String lname;

	@SerializedName("LnameEn")
	@Expose
	private String lnameEn;

	@SerializedName("Fname")
	@Expose
	private String fname;

	@SerializedName("FnameEn")
	@Expose
	private String fnameEn;

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLnameEn() {
		return lnameEn;
	}

	public void setLnameEn(String lnameEn) {
		this.lnameEn = lnameEn;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFnameEn() {
		return fnameEn;
	}

	public void setFnameEn(String fnameEn) {
		this.fnameEn = fnameEn;
	}

	//// ----------------  ////

	public String getLNAME() {
		return lNAME;
	}

	public void setLNAME(String lNAME) {
		this.lNAME = lNAME;
	}

	public String getLNAMEEN() {
		return lNAMEEN;
	}

	public void setLNAMEEN(String lNAMEEN) {
		this.lNAMEEN = lNAMEEN;
	}

	public String getFNAMEEN() {
		return fNAMEEN;
	}

	public void setFNAMEEN(String fNAMEEN) {
		this.fNAMEEN = fNAMEEN;
	}

	public String getFNAME() {
		return fNAME;
	}

	public void setFNAME(String fNAME) {
		this.fNAME = fNAME;
	}

}