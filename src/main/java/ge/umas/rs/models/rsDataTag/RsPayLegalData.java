package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.PayLegalData;

public class RsPayLegalData {
	@XmlElement(name = "PayLegalData")
	private PayLegalData[] PayLegalData;

	public PayLegalData[] getPayLegalData() {
		return PayLegalData;
	}

	public void setPayLegalData(PayLegalData[] PayLegalData) {
		this.PayLegalData = PayLegalData;
	}

	@Override
	public String toString() {
		return "ClassPojo [PayLegalData = " + PayLegalData + "]";
	}
}
