package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.ComplaintsData;


public class RsComplaintsData {
	@XmlElement(name = "ComplaintsData")
	private ComplaintsData[] ComplaintsData;

	public ComplaintsData[] getComplaintsData() {
		return ComplaintsData;
	}

	public void setComplaintsData(ComplaintsData[] ComplaintsData) {
		this.ComplaintsData = ComplaintsData;
	}

	@Override
	public String toString() {
		return "ClassPojo [ComplaintsData = " + ComplaintsData + "]";
	}
}
