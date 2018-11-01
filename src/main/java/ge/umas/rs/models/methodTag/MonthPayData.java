
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonthPayData", propOrder = { "DataID", "SaidCode", "DeclarationPeriod", "DeclarationTypeID",
		"DeclarationType", "DeclarationDate", "ReciverName", "ReciverID", "CitizenShip", "SalaryTypeID", "SalaryType",
		"RevenueAmount", "TaxyAmount" })
public class MonthPayData {

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
	@XmlElement(name = "ReciverName")
	protected String ReciverName;
	@XmlElement(name = "ReciverID")
	protected String ReciverID;
	@XmlElement(name = "CitizenShip")
	protected String CitizenShip;
	@XmlElement(name = "SalaryTypeID", required = true, nillable = true)
	protected String SalaryTypeID;
	@XmlElement(name = "SalaryType")
	protected String SalaryType;
	@XmlElement(name = "RevenueAmount", required = true, nillable = true)
	protected String RevenueAmount;
	@XmlElement(name = "TaxyAmount", required = true, nillable = true)
	protected String TaxyAmount;
	@XmlTransient
	protected String ReciverNameEn;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		SaidCode = Etl.ParseValidTextString(SaidCode);
		DeclarationPeriod = Etl.ParseLong(DeclarationPeriod);
		DeclarationTypeID = Etl.ParseLong(DeclarationTypeID);
		DeclarationType = Etl.ParseValidTextString(DeclarationType);
		DeclarationDate = Etl.ParseDate(DeclarationDate);
		ReciverName = Etl.ParseValidTextString(ReciverName);
		ReciverID = Etl.ParseValidTextString(ReciverID);
		CitizenShip = Etl.ParseValidTextString(CitizenShip);
		SalaryTypeID = Etl.ParseLong(SalaryTypeID);
		SalaryType = Etl.ParseValidTextString(SalaryType);
		RevenueAmount = Etl.ParseDecimal(RevenueAmount);
		TaxyAmount = Etl.ParseDecimal(TaxyAmount);
		ETL2();
	}

	private void ETL2() {
		if (ReciverName != null) {
			if (Etl.CodePageControl(ReciverName)) {
				ReciverNameEn = ReciverName.toString();
				ReciverName = null;
			}
			if (DeclarationPeriod != null)
				DeclarationPeriod = DeclarationPeriod.substring(0, 4) + "-" + DeclarationPeriod.substring(4, 6);
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

	public String getReciverName() {
		return ReciverName;
	}

	public void setReciverName(String value) {
		this.ReciverName = value;
	}

	public String getReciverID() {
		return ReciverID;
	}

	public void setReciverID(String value) {
		this.ReciverID = value;
	}

	public String getCitizenShip() {
		return CitizenShip;
	}

	public void setCitizenShip(String value) {
		this.CitizenShip = value;
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

	public String getRevenueAmount() {
		return RevenueAmount;
	}

	public void setRevenueAmount(String value) {
		this.RevenueAmount = value;
	}

	public String getTaxyAmount() {
		return TaxyAmount;
	}

	public void setTaxyAmount(String value) {
		this.TaxyAmount = value;
	}

}
