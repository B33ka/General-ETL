
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonBalanceData", propOrder = { "DataID", "SaidCode", "PersonId", "PersonName", "FirstRegDate",
		"CountryId", "LegalFormID", "LegalForm", "BalanceCondition", "LastName", "FisrtName", "Passport", "ActivityID",
		"Activity", "TaxMode", "SpecStatus" })
public class PersonBalanceData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "SaidCode")
	protected String SaidCode;
	@XmlElement(name = "PersonId")
	protected String PersonId;
	@XmlElement(name = "PersonName")
	protected String PersonName;
	@XmlElement(name = "FirstRegDate", required = true, nillable = true)
	protected String FirstRegDate;
	@XmlElement(name = "CountryId")
	protected String CountryId;
	@XmlElement(name = "LegalFormID")
	protected String LegalFormID;
	@XmlElement(name = "LegalForm")
	protected String LegalForm;
	@XmlElement(name = "BalanceCondition")
	protected String BalanceCondition;
	@XmlElement(name = "LastName")
	protected String LastName;
	@XmlElement(name = "FisrtName")
	protected String FisrtName;
	@XmlElement(name = "Passport")
	protected String Passport;
	@XmlElement(name = "ActivityID", required = true, nillable = true)
	protected String ActivityID;
	@XmlElement(name = "Activity")
	protected String Activity;
	@XmlElement(name = "TaxMode")
	protected String TaxMode;
	@XmlElement(name = "SpecStatus")
	protected String SpecStatus;
	@XmlTransient
	protected String PersonNameEn;
	@XmlTransient
	protected String LastNameEn;
	@XmlTransient
	protected String FisrtNameEn;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		SaidCode = Etl.ParseValidTextString(SaidCode);
		PersonId = Etl.ParseValidTextString(PersonId);
		PersonName = Etl.ParseValidNameString(PersonName);
		FirstRegDate = Etl.ParseDate(FirstRegDate);
		CountryId = Etl.ParseValidTextString(CountryId);
		LegalFormID = Etl.ParseLong(LegalFormID);
		LegalForm = Etl.ParseValidTextString(LegalForm);
		BalanceCondition = Etl.ParseValidTextString(BalanceCondition);
		LastName = Etl.ParseValidNameString(LastName);
		FisrtName = Etl.ParseValidNameString(FisrtName);
		Passport = Etl.ParseValidTextString(Passport);
		ActivityID = Etl.ParseLong(ActivityID);
		Activity = Etl.ParseValidTextString(Activity);
		TaxMode = Etl.ParseValidTextString(TaxMode);
		SpecStatus = Etl.ParseDate(SpecStatus);
		ETL2();
	}

	private void ETL2() {
		if (Etl.CodePageControl(PersonName)) {
			PersonNameEn = PersonName.toString();
			PersonName = null;
		}
		if (Etl.CodePageControl(LastName) || Etl.CodePageControl(FisrtName)) {
			LastNameEn = LastName.toString();
			FisrtNameEn = FisrtName.toString();
			LastName = null;
			FisrtName = null;
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

	public String getSaidCode() {
		return SaidCode;
	}

	public void setSaidCode(String value) {
		this.SaidCode = value;
	}

	public String getPersonId() {
		return PersonId;
	}

	public void setPersonId(String value) {
		this.PersonId = value;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String value) {
		this.PersonName = value;
	}

	public String getFirstRegDate() {
		return FirstRegDate;
	}

	public void setFirstRegDate(String value) {
		this.FirstRegDate = value;
	}

	public String getCountryId() {
		return CountryId;
	}

	public void setCountryId(String value) {
		this.CountryId = value;
	}

	public String getLegalFormID() {
		return LegalFormID;
	}

	public void setLegalFormID(String value) {
		this.LegalFormID = value;
	}

	public String getLegalForm() {
		return LegalForm;
	}

	public void setLegalForm(String value) {
		this.LegalForm = value;
	}

	public String getBalanceCondition() {
		return BalanceCondition;
	}

	public void setBalanceCondition(String value) {
		this.BalanceCondition = value;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String value) {
		this.LastName = value;
	}

	public String getFisrtName() {
		return FisrtName;
	}

	public void setFisrtName(String value) {
		this.FisrtName = value;
	}

	public String getPassport() {
		return Passport;
	}

	public void setPassport(String value) {
		this.Passport = value;
	}

	public String getActivityID() {
		return ActivityID;
	}

	public void setActivityID(String value) {
		this.ActivityID = value;
	}

	public String getActivity() {
		return Activity;
	}

	public void setActivity(String value) {
		this.Activity = value;
	}

	public String getTaxMode() {
		return TaxMode;
	}

	public void setTaxMode(String value) {
		this.TaxMode = value;
	}

	public String getSpecStatus() {
		return SpecStatus;
	}

	public void setSpecStatus(String value) {
		this.SpecStatus = value;
	}

}
