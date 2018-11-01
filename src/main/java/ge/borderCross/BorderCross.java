package ge.borderCross;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BorderCross {
	@SerializedName("CROSS_ID")
	@Expose
	public String cROSSID;
	@SerializedName("FIRST_NAME_EN")
	@Expose
	public String fIRSTNAMEEN;
	@SerializedName("FIRST_NAME_GE")
	@Expose
	public String fIRSTNAMEGE;
	@SerializedName("LAST_NAME_EN")
	@Expose
	public String lASTNAMEEN;
	@SerializedName("LAST_NAME_GE")
	@Expose
	public String lASTNAMEGE;
	@SerializedName("BIRTH_DATE")
	@Expose
	public String bIRTHDATE;
	@SerializedName("GENDER_ID")
	@Expose
	public Integer gENDERID;
	@SerializedName("PERSONAL_NO")
	@Expose
	public String pERSONALNO;
	@SerializedName("COUNTRY_ID")
	@Expose
	public Integer cOUNTRYID;
	@SerializedName("CROSS_DATE")
	@Expose
	public String cROSSDATE;
	@SerializedName("DIVISION_ID")
	@Expose
	public Integer dIVISIONID;
	@SerializedName("DIRECTION_ID")
	@Expose
	public Integer dIRECTIONID;
	@SerializedName("CROSS_STATE_ID")
	@Expose
	public Integer cROSSSTATEID;
	@SerializedName("ALLOWED_DAYS")
	@Expose
	public Integer aLLOWEDDAYS;
	@SerializedName("ALLOWED_DATE")
	@Expose
	public String aLLOWEDDATE;
	@SerializedName("AUTO_TYPE_ID")
	@Expose
	public Integer aUTOTYPEID;
	@SerializedName("TRANSPORT_TYPE_ID")
	@Expose
	public Integer tRANSPORTTYPEID;
	@SerializedName("DOCUMENTS")
	@Expose
	public List<DOCUMENT> dOCUMENTS = null;
	@SerializedName("VISA_TYPE_ID")
	@Expose
	public Object vISATYPEID;
	@SerializedName("VISA_SUB_TYPE_ID")
	@Expose
	public Object vISASUBTYPEID;
	@SerializedName("VISA_VALID_DATE")
	@Expose
	public Object vISAVALIDDATE;
	@SerializedName("VISA_NUMBER")
	@Expose
	public Object vISANUMBER;

}