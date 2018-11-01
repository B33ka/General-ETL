package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.etl.Etl;

public class FinancialDetails {

	@XmlElement(name = "Amount")
	private String Amount;
	@XmlElement(name = "ResourceType")
	private String ResourceType;
	@XmlElement(name = "Currency")
	private String Currency;

	public void ETL() {
		Currency = Etl.ParseValidTextString(Currency);
		Amount = Etl.ParseDecimal(Amount);
		ResourceType = Etl.ParseValidTextString(ResourceType);
		ETL2();
	}

	private void ETL2() {

	}

	public boolean RemoveObject() {
		boolean result = false;
		if (Amount == String.valueOf(0)) {
			result = true;
		}
		return result;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String Amount) {
		this.Amount = Amount;
	}

	public String getResourceType() {
		return ResourceType;
	}

	public void setResourceType(String ResourceType) {
		this.ResourceType = ResourceType;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String Currency) {
		this.Currency = Currency;
	}

	@Override
	public String toString() {
		return "ClassPojo [Amount = " + Amount + ", ResourceType = " + ResourceType + ", Currency = " + Currency + "]";
	}

}