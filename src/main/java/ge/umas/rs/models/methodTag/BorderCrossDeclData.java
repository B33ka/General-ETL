
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BorderCrossDeclData", propOrder = { "DataID", "FirstName", "LastName", "PassportID", "Citizenship",
		"GenderID", "Gender", "BirthDate", "BorderCrossDate", "FromCountry", "ToCountry", "Custom",
		"FinancialDetailsData" })
public class BorderCrossDeclData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "FirstName")
	protected String FirstName;
	@XmlElement(name = "LastName")
	protected String LastName;
	@XmlElement(name = "PassportID")
	protected String PassportID;
	@XmlElement(name = "Citizenship")
	protected String Citizenship;
	@XmlElement(name = "GenderID")
	protected String GenderID;
	@XmlElement(name = "Gender")
	protected String Gender;
	@XmlElement(name = "BirthDate")
	protected String BirthDate;
	@XmlElement(name = "BorderCrossDate")
	protected String BorderCrossDate;
	@XmlElement(name = "FromCountry")
	protected String FromCountry;
	@XmlElement(name = "ToCountry")
	protected String ToCountry;
	@XmlElement(name = "Custom")
	protected String Custom;
	@XmlElement(name = "FinancialDetailsData")
	protected FinancialDetailsData FinancialDetailsData;
	@XmlTransient
	public String FirstNameEn;
	@XmlTransient
	public String LastNameEn;

	public void ETL() {
		
		DataID = Etl.ParseLong(DataID);
		FirstName = Etl.ParseValidNameString(FirstName);
		LastName = Etl.ParseValidNameString(LastName);
		PassportID = Etl.ParseValidTextString(PassportID);
		Citizenship = Etl.ParseValidTextString(Citizenship);
		GenderID = Etl.ParseLong(GenderID);
		Gender = Etl.ParseLong(Gender);
		BirthDate = Etl.ParseDate(BirthDate);
		BorderCrossDate = Etl.ParseDate(BorderCrossDate);
		FromCountry = Etl.ParseValidTextString(FromCountry);
		ToCountry = Etl.ParseValidTextString(ToCountry);
		Custom = Etl.ParseValidTextString(Custom);
		if (FinancialDetailsData.getFinancialDetails() != null) {
			int count = FinancialDetailsData.getFinancialDetails().size();
			for (int i = 0; i < count; i++) {
				FinancialDetailsData.getFinancialDetails().get(i).ETL();
				if (FinancialDetailsData.getFinancialDetails().get(i).RemoveObject()) {
					FinancialDetailsData.getFinancialDetails().remove(i);
				}
			}
		}
		ETL2();
	}

	private void ETL2() {
		if (LastName != null && FirstName != null) {
			if (Etl.CodePageControl(LastName) || Etl.CodePageControl(FirstName)) {
				LastNameEn = LastName.toString();
				FirstNameEn = FirstName.toString();
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

	public String getPassportID() {
		return PassportID;
	}

	public void setPassportID(String value) {
		this.PassportID = value;
	}

	public String getCitizenship() {
		return Citizenship;
	}

	public void setCitizenship(String value) {
		this.Citizenship = value;
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

	public String getBorderCrossDate() {
		return BorderCrossDate;
	}

	public void setBorderCrossDate(String value) {
		this.BorderCrossDate = value;
	}

	public String getFromCountry() {
		return FromCountry;
	}

	public void setFromCountry(String value) {
		this.FromCountry = value;
	}

	public String getToCountry() {
		return ToCountry;
	}

	public void setToCountry(String value) {
		this.ToCountry = value;
	}

	public String getCustom() {
		return Custom;
	}

	public void setCustom(String value) {
		this.Custom = value;
	}

	public FinancialDetailsData getFinancialDetailsData() {
		return FinancialDetailsData;
	}

	public void setFinancialDetailsData(FinancialDetailsData value) {
		this.FinancialDetailsData = value;
	}

}
