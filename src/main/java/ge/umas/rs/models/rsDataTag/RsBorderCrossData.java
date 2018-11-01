package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.BorderCrossDeclData;

public class RsBorderCrossData {
	@XmlElement(name = "BorderCrossDeclData")
	private BorderCrossDeclData[] BorderCrossDeclData;

	public BorderCrossDeclData[] getBorderCrossDeclData() {
		return BorderCrossDeclData;
	}

	public void setBorderCrossDeclData(BorderCrossDeclData[] BorderCrossDeclData) {
		this.BorderCrossDeclData = BorderCrossDeclData;
	}

	@Override
	public String toString() {
		return "ClassPojo [BorderCrossDeclData = " + BorderCrossDeclData + "]";
	}
}
