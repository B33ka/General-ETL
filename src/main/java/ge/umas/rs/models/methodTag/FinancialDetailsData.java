package ge.umas.rs.models.methodTag;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class FinancialDetailsData {
	@XmlElement(name = "FinancialDetails")
	private List<FinancialDetails> FinancialDetails;

	public List<FinancialDetails> getFinancialDetails() {
		return FinancialDetails;
	}

	public void setFinancialDetails(List<FinancialDetails> FinancialDetails) {
		this.FinancialDetails = FinancialDetails;
	}

	@Override
	public String toString() {
		return "ClassPojo [FinancialDetails = " + FinancialDetails + "]";
	}
}
