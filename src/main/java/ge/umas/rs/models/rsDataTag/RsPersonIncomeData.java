package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.PersonIncomeData;

public class RsPersonIncomeData {
	@XmlElement(name = "PersonIncomeData")
	private PersonIncomeData[] PersonIncomeData;

	public PersonIncomeData[] getPersonIncomeData() {
		return PersonIncomeData;
	}

	public void setPersonIncomeData(PersonIncomeData[] PersonIncomeData) {
		this.PersonIncomeData = PersonIncomeData;
	}

	@Override
	public String toString() {
		return "ClassPojo [PersonIncomeData = " + PersonIncomeData + "]";
	}
}
