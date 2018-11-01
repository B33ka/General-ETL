
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExciseDeclData", propOrder = { "DataID", "SaidCode", "DeclarationPeriod", "DeclarationTypeID",
		"DeclarationType", "DeclarationDate", "ExciseTax" })
public class ExciseDeclData {

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
	@XmlElement(name = "ExciseTax", required = true, nillable = true)
	protected String ExciseTax;

	public void ETL() {
		DataID = Etl.ParseValidTextString(DataID);
		SaidCode = Etl.ParseValidTextString(SaidCode);
		DeclarationPeriod = Etl.ParseLong(DeclarationPeriod);
		DeclarationTypeID = Etl.ParseValidTextString(DeclarationTypeID);
		DeclarationType = Etl.ParseValidTextString(DeclarationType);
		DeclarationDate = Etl.ParseDate(DeclarationDate);
		ExciseTax = Etl.ParseDecimal(ExciseTax);
		ETL2();
	}

	private void ETL2() {
		if (DeclarationPeriod != null)
			DeclarationPeriod = DeclarationPeriod.substring(0, 4) + "-" + DeclarationPeriod.substring(4, 6);
	}

	public boolean RemoveObject() {
		boolean result = false;
		if (ExciseTax == String.valueOf(0)) {
			result = true;
		}
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

	public String getExciseTax() {
		return ExciseTax;
	}

	public void setExciseTax(String value) {
		this.ExciseTax = value;
	}

}
