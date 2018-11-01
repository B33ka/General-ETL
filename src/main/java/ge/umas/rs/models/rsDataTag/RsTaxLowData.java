package ge.umas.rs.models.rsDataTag;

import javax.xml.bind.annotation.XmlElement;

import ge.umas.rs.models.methodTag.TaxLowData;

public class RsTaxLowData {
	@XmlElement(name = "TaxLowData")
    private TaxLowData[] TaxLowData;

    public TaxLowData[] getTaxLowData ()
    {
        return TaxLowData;
    }

    public void setTaxLowData (TaxLowData[] TaxLowData)
    {
        this.TaxLowData = TaxLowData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TaxLowData = "+TaxLowData+"]";
    }

}
