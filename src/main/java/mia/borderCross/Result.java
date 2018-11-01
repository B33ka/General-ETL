
package mia.borderCross;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("CROSS_ID")
    @Expose
    private String CROSS_ID;
    @SerializedName("FIRST_NAME_EN")
    @Expose
    private String FIRST_NAME_EN;
    @SerializedName("FIRST_NAME_GE")
    @Expose
    private String FIRST_NAME_GE;
    @SerializedName("LAST_NAME_EN")
    @Expose
    private String LAST_NAME_EN;
    @SerializedName("LAST_NAME_GE")
    @Expose
    private String LAST_NAME_GE;
    @SerializedName("BIRTH_DATE")
    @Expose
    private String BIRTH_DATE;
    @SerializedName("GENDER_ID")
    @Expose
    private String GENDER_ID;
    @SerializedName("PERSONAL_NO")
    @Expose
    private String PERSONAL_NO;
    @SerializedName("COUNTRY_ID")
    @Expose
    private String COUNTRY_ID;
    @SerializedName("CROSS_DATE")
    @Expose
    private String CROSS_DATE;
    @SerializedName("DIVISION_ID")
    @Expose
    private String DIVISION_ID;
    @SerializedName("DIRECTION_ID")
    @Expose
    private String DIRECTION_ID;
    @SerializedName("CROSS_STATE_ID")
    @Expose
    private String CROSS_STATE_ID;
    @SerializedName("ALLOWED_DAYS")
    @Expose
    private String ALLOWED_DAYS;
    @SerializedName("ALLOWED_DATE")
    @Expose
    private String ALLOWED_DATE;
    @SerializedName("AUTO_TYPE_ID")
    @Expose
    private String AUTO_TYPE_ID;
    @SerializedName("TRANSPORT_TYPE_ID")
    @Expose
    private String TRANSPORT_TYPE_ID;
    @SerializedName("DOCUMENTS")
    @Expose
    private List<DOCUMENT> DOCUMENTS = null;
    @SerializedName("VISA_TYPE_ID")
    @Expose
    private String VISA_TYPE_ID;
    @SerializedName("VISA_SUB_TYPE_ID")
    @Expose
    private String VISA_SUB_TYPE_ID;
    @SerializedName("VISA_VALID_DATE")
    @Expose
    private String VISA_VALID_DATE;
    @SerializedName("VISA_NUMBER")
    @Expose
    private String VISA_NUMBER;

    public String getCROSS_ID() {
        return CROSS_ID;
    }

    public void setCROSS_ID(String CROSS_ID) {
        this.CROSS_ID = CROSS_ID;
    }

    public String getFIRST_NAME_EN() {
        return FIRST_NAME_EN;
    }

    public void setFIRST_NAME_EN(String FIRST_NAME_EN) {
        this.FIRST_NAME_EN = FIRST_NAME_EN;
    }

    public String getFIRST_NAME_GE() {
        return FIRST_NAME_GE;
    }

    public void setFIRST_NAME_GE(String FIRST_NAME_GE) {
        this.FIRST_NAME_GE = FIRST_NAME_GE;
    }

    public String getLAST_NAME_EN() {
        return LAST_NAME_EN;
    }

    public void setLAST_NAME_EN(String LAST_NAME_EN) {
        this.LAST_NAME_EN = LAST_NAME_EN;
    }

    public String getLAST_NAME_GE() {
        return LAST_NAME_GE;
    }

    public void setLAST_NAME_GE(String LAST_NAME_GE) {
        this.LAST_NAME_GE = LAST_NAME_GE;
    }

    public String getBIRTH_DATE() {
        return BIRTH_DATE;
    }

    public void setBIRTH_DATE(String BIRTH_DATE) {
        this.BIRTH_DATE = BIRTH_DATE;
    }

    public String getGENDER_ID() {
        return GENDER_ID;
    }

    public void setGENDER_ID(String GENDER_ID) {
        this.GENDER_ID = GENDER_ID;
    }

    public String getPERSONAL_NO() {
        return PERSONAL_NO;
    }

    public void setPERSONAL_NO(String PERSONAL_NO) {
        this.PERSONAL_NO = PERSONAL_NO;
    }

    public String getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(String COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

    public String getCROSS_DATE() {
        return CROSS_DATE;
    }

    public void setCROSS_DATE(String CROSS_DATE) {
        this.CROSS_DATE = CROSS_DATE;
    }

    public String getDIVISION_ID() {
        return DIVISION_ID;
    }

    public void setDIVISION_ID(String DIVISION_ID) {
        this.DIVISION_ID = DIVISION_ID;
    }

    public String getDIRECTION_ID() {
        return DIRECTION_ID;
    }

    public void setDIRECTION_ID(String DIRECTION_ID) {
        this.DIRECTION_ID = DIRECTION_ID;
    }

    public String getCROSS_STATE_ID() {
        return CROSS_STATE_ID;
    }

    public void setCROSS_STATE_ID(String CROSS_STATE_ID) {
        this.CROSS_STATE_ID = CROSS_STATE_ID;
    }

    public String getALLOWED_DAYS() {
        return ALLOWED_DAYS;
    }

    public void setALLOWED_DAYS(String ALLOWED_DAYS) {
        this.ALLOWED_DAYS = ALLOWED_DAYS;
    }

    public String getALLOWED_DATE() {
        return ALLOWED_DATE;
    }

    public void setALLOWED_DATE(String ALLOWED_DATE) {
        this.ALLOWED_DATE = ALLOWED_DATE;
    }

    public String getAUTO_TYPE_ID() {
        return AUTO_TYPE_ID;
    }

    public void setAUTO_TYPE_ID(String AUTO_TYPE_ID) {
        this.AUTO_TYPE_ID = AUTO_TYPE_ID;
    }

    public String getTRANSPORT_TYPE_ID() {
        return TRANSPORT_TYPE_ID;
    }

    public void setTRANSPORT_TYPE_ID(String TRANSPORT_TYPE_ID) {
        this.TRANSPORT_TYPE_ID = TRANSPORT_TYPE_ID;
    }

    public List<DOCUMENT> getDOCUMENTS() {
        return DOCUMENTS;
    }

    public void setDOCUMENTS(List<DOCUMENT> DOCUMENTS) {
        this.DOCUMENTS = DOCUMENTS;
    }

    public String getVISA_TYPE_ID() {
        return VISA_TYPE_ID;
    }

    public void setVISA_TYPE_ID(String VISA_TYPE_ID) {
        this.VISA_TYPE_ID = VISA_TYPE_ID;
    }

    public String getVISA_SUB_TYPE_ID() {
        return VISA_SUB_TYPE_ID;
    }

    public void setVISA_SUB_TYPE_ID(String VISA_SUB_TYPE_ID) {
        this.VISA_SUB_TYPE_ID = VISA_SUB_TYPE_ID;
    }

    public String getVISA_VALID_DATE() {
        return VISA_VALID_DATE;
    }

    public void setVISA_VALID_DATE(String VISA_VALID_DATE) {
        this.VISA_VALID_DATE = VISA_VALID_DATE;
    }

    public String getVISA_NUMBER() {
        return VISA_NUMBER;
    }

    public void setVISA_NUMBER(String VISA_NUMBER) {
        this.VISA_NUMBER = VISA_NUMBER;
    }
    
	public boolean isValid() {
		boolean res = true;
		if (getCROSS_ID() == null) {
			res = false;
			return res;
		}
		/*if (getFIRST_NAME_EN() == null && getLAST_NAME_EN() == null && getFIRST_NAME_GE() == null
				&& getLAST_NAME_GE() == null) {
			res = false;
			return res;
		}*/
		if (getFIRST_NAME_EN() == null && getLAST_NAME_EN() == null) {
			res = false;
			return res;
		}
		return res;
	}

}
