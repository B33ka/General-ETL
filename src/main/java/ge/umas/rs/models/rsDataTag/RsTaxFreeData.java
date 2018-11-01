package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.TaxFreeData;

public class RsTaxFreeData {
	@XmlElement(name = "TaxFreeData")
	private TaxFreeData[] TaxFreeData;

	public TaxFreeData[] getTaxFreeData() {
		return TaxFreeData;
	}

	public void setTaxFreeData(TaxFreeData[] TaxFreeData) {
		this.TaxFreeData = TaxFreeData;
	}

	@Override
	public String toString() {
		return "ClassPojo [TaxFreeData = " + TaxFreeData + "]";
	}
}
