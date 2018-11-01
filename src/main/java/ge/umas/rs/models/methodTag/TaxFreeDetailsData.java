package ge.umas.rs.models.methodTag;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class TaxFreeDetailsData {
	@XmlElement(name = "TaxFreeDetails")
	private List<TaxFreeDetails> TaxFreeDetails;

	public List<TaxFreeDetails> getTaxFreeDetails() {
		return TaxFreeDetails;
	}

	public void setTaxFreeDetails(List<TaxFreeDetails> TaxFreeDetails) {
		this.TaxFreeDetails = TaxFreeDetails;
	}

	@Override
	public String toString() {
		return "ClassPojo [TaxFreeDetails = " + TaxFreeDetails + "]";
	}
}