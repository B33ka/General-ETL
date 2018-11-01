package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.IncassoData;

public class RsIncassoData {
	@XmlElement(name = "IncassoData")
	private IncassoData[] IncassoData;

	public IncassoData[] getIncassoData() {
		return IncassoData;
	}

	public void setIncassoData(IncassoData[] IncassoData) {
		this.IncassoData = IncassoData;
	}

	@Override
	public String toString() {
		return "ClassPojo [IncassoData = " + IncassoData + "]";
	}
}