
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayLegalData", propOrder = { "DataID", "SaidCode", "DeclarationPeriod", "DeclarationTypeID",
		"DeclarationType", "DeclarationDate", "Income" })
public class PayLegalData {

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
	@XmlElement(name = "Income", required = true, nillable = true)
	protected String Income;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		SaidCode = Etl.ParseValidTextString(SaidCode);
		DeclarationPeriod = Etl.ParseLong(DeclarationPeriod);
		DeclarationTypeID = Etl.ParseLong(DeclarationTypeID);
		DeclarationType = Etl.ParseValidTextString(DeclarationType);
		DeclarationDate = Etl.ParseDate(DeclarationDate);
		Income = Etl.ParseDecimal(Income);
		ETL2();
	}

	private void ETL2() {

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

	public String getIncome() {
		return Income;
	}

	public void setIncome(String value) {
		this.Income = value;
	}

}
