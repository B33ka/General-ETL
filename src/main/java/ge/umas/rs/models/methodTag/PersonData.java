
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonData", propOrder = { "DataID", "PersonID", "FirstName", "LastName", "PassportNo", "CountryID",
		"Country" })
public class PersonData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "PersonID")
	protected String PersonID;
	@XmlElement(name = "FirstName")
	protected String FirstName;
	@XmlElement(name = "LastName")
	protected String LastName;
	@XmlElement(name = "PassportNo")
	protected String PassportNo;
	@XmlElement(name = "CountryID", required = true, nillable = true)
	protected String CountryID;
	@XmlElement(name = "Country")
	protected String Country;
	@XmlTransient
	protected String FisrtNameEn;
	@XmlTransient
	protected String LastNameEn;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		PersonID = Etl.ParseValidTextString(PersonID);
		FirstName = Etl.ParseValidNameString(FirstName);
		LastName = Etl.ParseValidNameString(LastName);
		PassportNo = Etl.ParseValidTextString(PassportNo);
		CountryID = Etl.ParseLong(CountryID);
		Country = Etl.ParseValidTextString(Country);
		ETL2();
	}

	private void ETL2() {
		if (LastName != null && FirstName != null) {
			if (Etl.CodePageControl(LastName) || Etl.CodePageControl(FirstName)) {
				LastNameEn = LastName.toString();
				FisrtNameEn = FirstName.toString();
				LastName = null;
				FirstName = null;
			}
		}
	}

	public boolean RemoveObject() {
		boolean result = false;
		return result;
	}

	public String getDataID() {
		return DataID;
	}

	public void setDataID(String value) {
		this.DataID = value;
	}

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String value) {
		this.PersonID = value;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String value) {
		this.FirstName = value;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String value) {
		this.LastName = value;
	}

	public String getPassportNo() {
		return PassportNo;
	}

	public void setPassportNo(String value) {
		this.PassportNo = value;
	}

	public String getCountryID() {
		return CountryID;
	}

	public void setCountryID(String value) {
		this.CountryID = value;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String value) {
		this.Country = value;
	}

}
