
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayData", propOrder = { "DataID", "DeclarationPeriod", "DeclarationTypeID", "DeclarationType",
		"DeclarationDate", "ReceiverCategory", "ReceiverName", "SalaryDate", "SalaryTypeID", "SalaryType",
		"GrossPaymentAmount", "TaxPrivilege", "TaxAmountRatio", "NetPaymentAmount" })
public class PayData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "DeclarationPeriod")
	protected String DeclarationPeriod;
	@XmlElement(name = "DeclarationTypeID", required = true, nillable = true)
	protected String DeclarationTypeID;
	@XmlElement(name = "DeclarationType")
	protected String DeclarationType;
	@XmlElement(name = "DeclarationDate", required = true, nillable = true)
	protected String DeclarationDate;
	@XmlElement(name = "ReceiverCategory")
	protected String ReceiverCategory;
	@XmlElement(name = "ReceiverName")
	protected String ReceiverName;
	@XmlElement(name = "SalaryDate", required = true, nillable = true)
	protected String SalaryDate;
	@XmlElement(name = "SalaryTypeID", required = true, nillable = true)
	protected String SalaryTypeID;
	@XmlElement(name = "SalaryType")
	protected String SalaryType;
	@XmlElement(name = "GrossPaymentAmount", required = true, nillable = true)
	protected String GrossPaymentAmount;
	@XmlElement(name = "TaxPrivilege", required = true, nillable = true)
	protected String TaxPrivilege;
	@XmlElement(name = "TaxAmountRatio", required = true, nillable = true)
	protected String TaxAmountRatio;
	@XmlElement(name = "NetPaymentAmount", required = true, nillable = true)
	protected String NetPaymentAmount;
	@XmlTransient
	protected String ReceiverNameEn;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		DeclarationPeriod = Etl.ParseLong(DeclarationPeriod);
		DeclarationTypeID = Etl.ParseLong(DeclarationTypeID);
		DeclarationType = Etl.ParseValidTextString(DeclarationType);
		DeclarationDate = Etl.ParseDate(DeclarationDate);
		ReceiverCategory = Etl.ParseValidTextString(ReceiverCategory);
		ReceiverName = Etl.ParseValidNameString(ReceiverName);
		SalaryDate = Etl.ParseDate(SalaryDate);
		SalaryTypeID = Etl.ParseLong(SalaryTypeID);
		SalaryType = Etl.ParseValidTextString(SalaryType);
		GrossPaymentAmount = Etl.ParseDecimal(GrossPaymentAmount);
		TaxPrivilege = Etl.ParseDecimal(TaxPrivilege);
		TaxAmountRatio = Etl.ParseDecimal(TaxAmountRatio);
		NetPaymentAmount = Etl.ParseDecimal(NetPaymentAmount);
		ETL2();
	}

	private void ETL2() {

		if (ReceiverName != null) {
			if (Etl.CodePageControl(ReceiverName)) {
				ReceiverNameEn = ReceiverName.toString();
				ReceiverName = null;
			}
			DeclarationPeriod = DeclarationPeriod.substring(0, 4) + "-" + DeclarationPeriod.substring(4, 6);
		} else {
			ReceiverName = "PersonX";
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

	public String getReceiverCategory() {
		return ReceiverCategory;
	}

	public void setReceiverCategory(String value) {
		this.ReceiverCategory = value;
	}

	public String getReceiverName() {
		return ReceiverName;
	}

	public void setReceiverName(String value) {
		this.ReceiverName = value;
	}

	public String getSalaryDate() {
		return SalaryDate;
	}

	public void setSalaryDate(String value) {
		this.SalaryDate = value;
	}

	public String getSalaryTypeID() {
		return SalaryTypeID;
	}

	public void setSalaryTypeID(String value) {
		this.SalaryTypeID = value;
	}

	public String getSalaryType() {
		return SalaryType;
	}

	public void setSalaryType(String value) {
		this.SalaryType = value;
	}

	public String getGrossPaymentAmount() {
		return GrossPaymentAmount;
	}

	public void setGrossPaymentAmount(String value) {
		this.GrossPaymentAmount = value;
	}

	public String getTaxPrivilege() {
		return TaxPrivilege;
	}

	public void setTaxPrivilege(String value) {
		this.TaxPrivilege = value;
	}

	public String getTaxAmountRatio() {
		return TaxAmountRatio;
	}

	public void setTaxAmountRatio(String value) {
		this.TaxAmountRatio = value;
	}

	public String getNetPaymentAmount() {
		return NetPaymentAmount;
	}

	public void setNetPaymentAmount(String value) {
		this.NetPaymentAmount = value;
	}

}
