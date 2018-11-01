
package ge.umas.rs.models.methodTag;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ge.umas.etl.Etl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplaintsData", propOrder = {
    "DataID",
    "SaidCode",
    "RegDate",
    "ModuleName",
    "ComplaintStatus",
    "DecisionDate"
})
public class ComplaintsData {

    @XmlElement(name = "DataID")
    protected String DataID;
    @XmlElement(name = "SaidCode")
    protected String SaidCode;
    @XmlElement(name = "RegDate", required = true, nillable = true)
    protected String RegDate;
    @XmlElement(name = "ModuleName")
    protected String ModuleName;
    @XmlElement(name = "ComplaintStatus")
    protected String ComplaintStatus;
    @XmlElement(name = "DecisionDate", required = true, nillable = true)
    protected String DecisionDate;
    
 
    public void ETL() {
        DataID = Etl.ParseLong(DataID);
        SaidCode = Etl.ParseValidTextString(SaidCode);
        RegDate = Etl.ParseDate(RegDate);
        ModuleName = Etl.ParseValidTextString(ModuleName);
        ComplaintStatus = Etl.ParseValidTextString(ComplaintStatus);
        DecisionDate = Etl.ParseDate(DecisionDate);
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

    public String getRegDate() {
        return RegDate;
    }

    public void setRegDate(String value) {
        this.RegDate = value;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String value) {
        this.ModuleName = value;
    }

    public String getComplaintStatus() {
        return ComplaintStatus;
    }

    public void setComplaintStatus(String value) {
        this.ComplaintStatus = value;
    }


    public String getDecisionDate() {
        return DecisionDate;
    }

    public void setDecisionDate(String value) {
        this.DecisionDate = value;
    }

}
