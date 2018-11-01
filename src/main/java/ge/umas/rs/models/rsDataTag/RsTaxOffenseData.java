package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.TaxOffenseData;

public class RsTaxOffenseData {
	@XmlElement(name = "TaxOffenseData")
	private TaxOffenseData[] TaxOffenseData;

	public TaxOffenseData[] getTaxOffenseData() {
		return TaxOffenseData;
	}

	public void setTaxOffenseData(TaxOffenseData[] TaxOffenseData) {
		this.TaxOffenseData = TaxOffenseData;
	}

	@Override
	public String toString() {
		return "ClassPojo [TaxOffenseData = " + TaxOffenseData + "]";
	}
}