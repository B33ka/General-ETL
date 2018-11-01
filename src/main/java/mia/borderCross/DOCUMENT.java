
package mia.borderCross;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DOCUMENT {

	@SerializedName("DOCUMENT_TYPE_ID")
	@Expose
	private String DOCUMENT_TYPE_ID;
	@SerializedName("DOCUMENT_NO")
	@Expose
	private String DOCUMENT_NO;
	@SerializedName("DOCUMENT_COUNTRY_ID")
	@Expose
	private String DOCUMENT_COUNTRY_ID;
	@SerializedName("DOCUMENT_SUB_TYPE_ID")
	@Expose
	private String DOCUMENT_SUB_TYPE_ID;
	@SerializedName("IS_MAIN")
	@Expose
	private String IS_MAIN;

	public String getDOCUMENTTYPEID() {
		return DOCUMENT_TYPE_ID;
	}

	public void setDOCUMENTTYPEID(String DOCUMENT_COUNTRY_ID) {
		this.DOCUMENT_COUNTRY_ID = DOCUMENT_COUNTRY_ID;
	}

	public String getDOCUMENTNO() {
		return DOCUMENT_NO;
	}

	public void setDOCUMENTNO(String DOCUMENT_NO) {
		this.DOCUMENT_NO = DOCUMENT_NO;
	}

	public String getDOCUMENTCOUNTRYID() {
		return DOCUMENT_COUNTRY_ID;
	}

	public void setDOCUMENTCOUNTRYID(String DOCUMENT_COUNTRY_ID) {
		this.DOCUMENT_COUNTRY_ID = DOCUMENT_COUNTRY_ID;
	}

	public String getDOCUMENTSUBTYPEID() {
		return DOCUMENT_SUB_TYPE_ID;
	}

	public void setDOCUMENTSUBTYPEID(String DOCUMENT_SUB_TYPE_ID) {
		this.DOCUMENT_SUB_TYPE_ID = DOCUMENT_SUB_TYPE_ID;
	}

	public String getISMAIN() {
		return IS_MAIN;
	}

	public void setISMAIN(String IS_MAIN) {
		this.IS_MAIN = IS_MAIN;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DOCUMENT) {
			DOCUMENT temp = (DOCUMENT) obj;
			if (this.DOCUMENT_NO != null && this.IS_MAIN != null)
				if (this.DOCUMENT_NO.equals(temp.DOCUMENT_NO) && this.IS_MAIN.equals(this.IS_MAIN))
					return true;
		}
		return false;

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		if (this.DOCUMENT_COUNTRY_ID != null)
			return (this.DOCUMENT_NO.hashCode());
		return 0;
	}

}
