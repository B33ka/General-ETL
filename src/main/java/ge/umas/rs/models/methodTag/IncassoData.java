
package ge.umas.rs.models.methodTag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IncassoData", propOrder = { "DataID", "SaidCode", "MeasuresID", "DeclarationDate", "StatusCode" })
public class IncassoData {

	@XmlElement(name = "DataID")
	protected String DataID;
	@XmlElement(name = "SaidCode")
	protected String SaidCode;
	@XmlElement(name = "MeasuresID")
	protected String MeasuresID;
	@XmlElement(name = "DeclarationDate", required = true, nillable = true)
	protected String DeclarationDate;
	@XmlElement(name = "StatusCode")
	protected String StatusCode;

	public void ETL() {
		DataID = Etl.ParseLong(DataID);
		SaidCode = Etl.ParseValidTextString(SaidCode);
		MeasuresID = Etl.ParseValidTextString(MeasuresID);
		DeclarationDate = Etl.ParseDate(DeclarationDate);
		StatusCode = Etl.ParseValidTextString(StatusCode);
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

	public String getMeasuresID() {
		return MeasuresID;
	}

	public void setMeasuresID(String value) {
		this.MeasuresID = value;
	}

	public String getDeclarationDate() {
		return DeclarationDate;
	}

	public void setDeclarationDate(String value) {
		this.DeclarationDate = value;
	}

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String value) {
		this.StatusCode = value;
	}

}
