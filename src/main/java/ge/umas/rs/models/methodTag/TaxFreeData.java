
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxFreeData", propOrder = { "DataID", "FirstName", "LastName", "PasportNo", "PasportSeries",
		"PersonID", "Citizenship", "PaymentDate", "VatToPay", "TaxFreeDetailsData" })
public class TaxFreeData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "FirstName")
	protected String FirstName;
	@XmlElement(name = "LastName")
	protected String LastName;
	@XmlElement(name = "PasportNo")
	protected String PasportNo;
	@XmlElement(name = "PasportSeries")
	protected String PasportSeries;
	@XmlElement(name = "PersonID")
	protected String PersonID;
	@XmlElement(name = "Citizenship")
	protected String Citizenship;
	@XmlElement(name = "PaymentDate", required = true, nillable = true)
	protected String PaymentDate;
	@XmlElement(name = "VatToPay", required = true, nillable = true)
	protected String VatToPay;
	@XmlElement(name = "TaxFreeDetailsData")
	protected TaxFreeDetailsData TaxFreeDetailsData;
	@XmlTransient
	protected String FisrtNameEn;
	@XmlTransient
	protected String LastNameEn;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		FirstName = Etl.ParseValidNameString(FirstName);
		LastName = Etl.ParseValidNameString(LastName);
		PasportNo = Etl.ParseValidTextString(PasportNo);
		PasportSeries = Etl.ParseValidTextString(PasportSeries);
		PersonID = Etl.ParseValidTextString(PersonID);
		Citizenship = Etl.ParseValidTextString(Citizenship);
		PaymentDate = Etl.ParseDate(PaymentDate);
		VatToPay = Etl.ParseDecimal(VatToPay);

		if (TaxFreeDetailsData.getTaxFreeDetails() != null) {
			int count = TaxFreeDetailsData.getTaxFreeDetails().size();
			for (int i = 0; i < count; i++) {
				TaxFreeDetailsData.getTaxFreeDetails().get(i).ETL();
				if (TaxFreeDetailsData.getTaxFreeDetails().get(i).RemoveObject()) {
					TaxFreeDetailsData.getTaxFreeDetails().remove(i);
				}
			}
		}
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

	public String getPasportNo() {
		return PasportNo;
	}

	public void setPasportNo(String value) {
		this.PasportNo = value;
	}

	public String getPasportSeries() {
		return PasportSeries;
	}

	public void setPasportSeries(String value) {
		this.PasportSeries = value;
	}

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String value) {
		this.PersonID = value;
	}

	public String getCitizenship() {
		return Citizenship;
	}

	public void setCitizenship(String value) {
		this.Citizenship = value;
	}

	public String getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(String value) {
		this.PaymentDate = value;
	}

	public String getVatToPay() {
		return VatToPay;
	}

	public void setVatToPay(String value) {
		this.VatToPay = value;
	}

	public TaxFreeDetailsData getTaxFreeDetailsData() {
		return TaxFreeDetailsData;
	}

	public void setTaxFreeDetailsData(TaxFreeDetailsData value) {
		this.TaxFreeDetailsData = value;
	}

}
