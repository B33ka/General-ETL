
package ge.umas.rs.models.methodTag;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxLowData", propOrder = { "DataID", "SaidCode", "DeclarationPeriod", "DeclarationTypeID",
		"DeclarationType", "DeclarationDate", "ReceiverName", "ReceiverID", "Citizenship", "PaymentDate",
		"PaymentTypeID", "PaymentType", "GrossPaymentAmount", "TaxAmount", "PaymentRatioGeo", "PaymentRatioInt" })
public class TaxLowData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "SaidCode")
	protected String SaidCode;
	@XmlElement(name = "DeclarationPeriod")
	protected String DeclarationPeriod;
	@XmlElement(name = "DeclarationTypeID", required = true, nillable = true)
	protected String DeclarationTypeID;
	@XmlElement(name = "DeclarationType")
	protected String DeclarationType;
	@XmlElement(name = "DeclarationDate", required = true, nillable = true)
	protected String DeclarationDate;
	@XmlElement(name = "ReceiverName")
	protected String ReceiverName;
	@XmlElement(name = "ReceiverID")
	protected String ReceiverID;
	@XmlElement(name = "Citizenship")
	protected String Citizenship;
	@XmlElement(name = "PaymentDate", required = true, nillable = true)
	protected String PaymentDate;
	@XmlElement(name = "PaymentTypeID", required = true, nillable = true)
	protected String PaymentTypeID;
	@XmlElement(name = "PaymentType")
	protected String PaymentType;
	@XmlElement(name = "GrossPaymentAmount", required = true, nillable = true)
	protected String GrossPaymentAmount;
	@XmlElement(name = "TaxAmount", required = true, nillable = true)
	protected String TaxAmount;
	@XmlElement(name = "PaymentRatioGeo", required = true, nillable = true)
	protected String PaymentRatioGeo;
	@XmlElement(name = "PaymentRatioInt", required = true, nillable = true)
	protected String PaymentRatioInt;
	@XmlTransient
	protected String ReceiverNameEn;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		SaidCode = Etl.ParseValidTextString(SaidCode);
		DeclarationPeriod = Etl.ParseLong(DeclarationPeriod);
		DeclarationTypeID = Etl.ParseLong(DeclarationTypeID);
		DeclarationType = Etl.ParseValidTextString(DeclarationType);
		DeclarationDate = Etl.ParseDate(DeclarationDate);
		ReceiverName = Etl.ParseValidTextString(ReceiverName);
		ReceiverID = Etl.ParseValidTextString(ReceiverID);
		Citizenship = Etl.ParseValidTextString(Citizenship);
		PaymentDate = Etl.ParseDate(PaymentDate);
		PaymentTypeID = Etl.ParseLong(PaymentTypeID);
		PaymentType = Etl.ParseValidTextString(PaymentType);
		GrossPaymentAmount = Etl.ParseDecimal(GrossPaymentAmount);
		TaxAmount = Etl.ParseDecimal(TaxAmount);
		PaymentRatioGeo = Etl.ParseDecimal(PaymentRatioGeo);
		PaymentRatioInt = Etl.ParseDecimal(PaymentRatioInt);
		ETL2();
	}

	private void ETL2() {
		if (ReceiverName != null) {
			if (Etl.CodePageControl(ReceiverName)) {
				ReceiverNameEn = ReceiverName.toString();
				ReceiverName = null;
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

	public String getSaidCode() {
		return SaidCode;
	}

	public void setSaidCode(String value) {
		this.SaidCode = value;
	}

	public String getDeclarationPeriod() {
		return DeclarationPeriod;
	}

	public void setDeclarationPeriod(String value) {
		this.DeclarationPeriod = value;
	}

	public String getDeclarationTypeID() {
		return DeclarationTypeID;
	}

	public void setDeclarationTypeID(String value) {
		this.DeclarationTypeID = value;
	}

	public String getDeclarationType() {
		return DeclarationType;
	}

	public void setDeclarationType(String value) {
		this.DeclarationType = value;
	}

	public String getDeclarationDate() {
		return DeclarationDate;
	}

	public void setDeclarationDate(String value) {
		this.DeclarationDate = value;
	}

	public String getReceiverName() {
		return ReceiverName;
	}

	public void setReceiverName(String value) {
		this.ReceiverName = value;
	}

	public String getReceiverID() {
		return ReceiverID;
	}

	public void setReceiverID(String value) {
		this.ReceiverID = value;
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

	public String getPaymentTypeID() {
		return PaymentTypeID;
	}

	public void setPaymentTypeID(String value) {
		this.PaymentTypeID = value;
	}

	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String value) {
		this.PaymentType = value;
	}

	public String getGrossPaymentAmount() {
		return GrossPaymentAmount;
	}

	public void setGrossPaymentAmount(String value) {
		this.GrossPaymentAmount = value;
	}

	public String getTaxAmount() {
		return TaxAmount;
	}

	public void setTaxAmount(String value) {
		this.TaxAmount = value;
	}

	public String getPaymentRatioGeo() {
		return PaymentRatioGeo;
	}

	public void setPaymentRatioGeo(String value) {
		this.PaymentRatioGeo = value;
	}

	public String getPaymentRatioInt() {
		return PaymentRatioInt;
	}

	public void setPaymentRatioInt(String value) {
		this.PaymentRatioInt = value;
	}

}
