package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.PayData;

public class RsPayData {
	@XmlElement(name = "PayData")
	private PayData[] PayData;

	public PayData[] getPayData() {
		return PayData;
	}

	public void setPayData(PayData[] PayData) {
		this.PayData = PayData;
	}

	@Override
	public String toString() {
		return "ClassPojo [PayData = " + PayData + "]";
	}

}
