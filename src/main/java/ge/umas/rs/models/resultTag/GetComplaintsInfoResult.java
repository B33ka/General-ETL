package ge.umas.rs.models.resultTag;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ge.umas.rs.models.rsDataTag.RsComplaintsData;

@XmlRootElement(name = "GetComplaintsInfoResult")
public class GetComplaintsInfoResult {
	
	@XmlElement(name = "RsData")
	private RsComplaintsData RsData;
	private String ResponseStatus;
	private String xmlns;

	public RsComplaintsData getRsData() {
		return RsData;
	}

	public void setRsData(RsComplaintsData RsData) {
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
		return "ClassPojo [RsComplaintsData = " + RsData + ", ResponseStatus = " + ResponseStatus + ", xmlns = " + xmlns + "]";
	}
}