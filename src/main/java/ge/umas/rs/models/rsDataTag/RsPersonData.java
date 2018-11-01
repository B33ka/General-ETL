package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.PersonData;

public class RsPersonData {
	@XmlElement(name = "PersonData")
	private PersonData[] PersonData;

	public PersonData[] getPersonData() {
		return PersonData;
	}

	public void setPersonData(PersonData[] PersonData) {
		this.PersonData = PersonData;
	}

	@Override
	public String toString() {
		return "ClassPojo [PersonData = " + PersonData + "]";
	}

}
