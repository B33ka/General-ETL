package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.PersonBalanceData;

public class RsPersonBalanceData {
	@XmlElement(name = "PersonBalanceData")
	private PersonBalanceData[] PersonBalanceData;

	public PersonBalanceData[] getPersonBalanceData() {
		return PersonBalanceData;
	}

	public void setPersonBalanceData(PersonBalanceData[] PersonBalanceData) {
		this.PersonBalanceData = PersonBalanceData;
	}

	@Override
	public String toString() {
		return "ClassPojo [PersonBalanceData = " + PersonBalanceData + "]";
	}

}
