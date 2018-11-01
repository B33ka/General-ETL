
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxOffenseData", propOrder = { "DataID", "PersonID", "PassportSer", "PassportNo", "LastName",
		"FirstName", "CountryID", "GenderID", "Gender", "BirthDate", "CreateDate", "Amount" })
public class TaxOffenseData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "PersonID")
	protected String PersonID;
	@XmlElement(name = "PassportSer")
	protected String PassportSer;
	@XmlElement(name = "PassportNo")
	protected String PassportNo;
	@XmlElement(name = "LastName")
	protected String LastName;
	@XmlElement(name = "FirstName")
	protected String FirstName;
	@XmlElement(name = "CountryID")
	protected String CountryID;
	@XmlElement(name = "GenderID", required = true, nillable = true)
	protected String GenderID;
	@XmlElement(name = "Gender")
	protected String Gender;
	@XmlElement(name = "BirthDate", required = true, nillable = true)
	protected String BirthDate;
	@XmlElement(name = "CreateDate", required = true, nillable = true)
	protected String CreateDate;
	@XmlElement(name = "Amount", required = true, nillable = true)
	protected String Amount;
	@XmlTransient
	public String FisrtNameEn;
	@XmlTransient
	public String LastNameEn;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		PersonID = Etl.ParseValidTextString(PersonID);
		PassportNo = Etl.ParseValidTextString(PassportNo);
		PassportSer = Etl.ParseValidTextString(PassportSer);
		FirstName = Etl.ParseValidNameString(FirstName);
		LastName = Etl.ParseValidNameString(LastName);
		CountryID = Etl.ParseLong(CountryID);
		GenderID = Etl.ParseLong(GenderID);
		Gender = Etl.ParseLong(Gender);
		BirthDate = Etl.ParseDate(BirthDate);
		CreateDate = Etl.ParseDate(CreateDate);
		Amount = Etl.ParseDecimal(Amount);
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

	public String getPassportSer() {
		return PassportSer;
	}

	public void setPassportSer(String value) {
		this.PassportSer = value;
	}

	public String getPassportNo() {
		return PassportNo;
	}

	public void setPassportNo(String value) {
		this.PassportNo = value;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String value) {
		this.LastName = value;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String value) {
		this.FirstName = value;
	}

	public String getCountryID() {
		return CountryID;
	}

	public void setCountryID(String value) {
		this.CountryID = value;
	}

	public String getGenderID() {
		return GenderID;
	}

	public void setGenderID(String value) {
		this.GenderID = value;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String value) {
		this.Gender = value;
	}

	public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String value) {
		this.BirthDate = value;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String value) {
		this.CreateDate = value;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String value) {
		this.Amount = value;
	}

}
