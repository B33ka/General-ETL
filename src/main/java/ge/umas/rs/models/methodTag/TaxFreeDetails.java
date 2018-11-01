package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import ge.umas.etl.Etl;

public class TaxFreeDetails {

	@XmlElement(name = "Name")
	private String Name;
	@XmlElement(name = "Amount")
	private String Amount;
	@XmlTransient
	private String NameEn;

	private void ETL2() {
		if (Name != null) {
			if (Etl.CodePageControl(Name)) {
				NameEn = Name.toString();
				Name = null;
			}
		}
	}

	public boolean RemoveObject() {
		boolean result = false;
		if (Amount == String.valueOf(0)) {
			result = true;
		}
		return result;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String Amount) {
		this.Amount = Amount;
	}

	public void ETL() {
		Name = Etl.ParseValidTextString(Name);
		Amount = Etl.ParseDecimal(Amount);
		ETL2();
	}

	@Override
	public String toString() {
		return "ClassPojo [Name = " + Name + ", Amount = " + Amount + "]";
	}
}
