package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.MonthPayData;

public class RsMonthPayData {
	@XmlElement(name = "MonthPayData")
    private MonthPayData[] MonthPayData;

    public MonthPayData[] getMonthPayData ()
    {
        return MonthPayData;
    }

    public void setMonthPayData (MonthPayData[] MonthPayData)
    {
        this.MonthPayData = MonthPayData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MonthPayData = "+MonthPayData+"]";
    }

}
