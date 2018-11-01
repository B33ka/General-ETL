package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.ExciseDeclData;

public class RsExciseDeclData {
	@XmlElement(name = "ExciseDeclData")
	private ExciseDeclData[] ExciseDeclData;

	public ExciseDeclData[] getExciseDeclData() {
		return ExciseDeclData;
	}

	public void setExciseDeclData(ExciseDeclData[] ExciseDeclData) {
		this.ExciseDeclData = ExciseDeclData;
	}

	@Override
	public String toString() {
		return "ClassPojo [ExciseDeclData = " + ExciseDeclData + "]";
	}
}
