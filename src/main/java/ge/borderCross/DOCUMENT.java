package ge.borderCross;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DOCUMENT {
	@SerializedName("DOCUMENT_TYPE_ID")
	@Expose
	public Integer DOCUMENTTYPEID;
	@SerializedName("DOCUMENT_NO")
	@Expose
	public String DOCUMENTNO;
	@SerializedName("DOCUMENT_COUNTRY_ID")
	@Expose
	public Integer DOCUMENTCOUNTRYID;
	@SerializedName("DOCUMENT_SUB_TYPE_ID")
	@Expose
	public Integer DOCUMENTSUBTYPEID;
	@SerializedName("IS_MAIN")
	@Expose
	public Integer ISMAIN;
}