package ge.umas.rs.models.resultTag;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ge.umas.rs.models.rsDataTag.RsTaxLowData;

@XmlRootElement(name = "GetTaxLowInfoResult")
public class GetTaxLowInfoResult {
	@XmlElement(name = "RsData")
	private RsTaxLowData RsData;

	private String ResponseStatus;

	private String xmlns;

	public RsTaxLowData getRsData() {
		return RsData;
	}

	public void setRsData(RsTaxLowData RsData) {
		this.RsData = RsData;
	}

	public String getResponseStatus() {
		return ResponseStatus;
	}

	public void setResponseStatus(String ResponseStatus) {
		this.ResponseStatus = ResponseStatus;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	@Override
	public String toString() {
		return "ClassPojo [RsData = " + RsData + ", ResponseStatus = " + ResponseStatus + ", xmlns = " + xmlns + "]";
	}
}