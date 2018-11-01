package ge.umas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BorderCrossModel {


	@SerializedName("FIRST_NAME_EN")
	@Expose
	private String firstNameEn;
	@SerializedName("LAST_NAME_EN")
	@Expose
	private String lastNameEn;


	public String getFIRSTNAMEEN() {
		return firstNameEn;
	}

	public void setFIRSTNAMEEN(String fIRSTNAMEEN) {
		this.firstNameEn = fIRSTNAMEEN;
	}

	public String getLASTNAMEEN() {
		return lastNameEn;
	}

	public void setLASTNAMEEN(String lASTNAMEEN) {
		this.lastNameEn = lASTNAMEEN;
	}

	

}