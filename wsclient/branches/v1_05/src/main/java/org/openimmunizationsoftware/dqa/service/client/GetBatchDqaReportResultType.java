
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchDqaReportResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchDqaReportResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dqaReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchDqaReportResultType", propOrder = {
    "dqaReport"
})
public class GetBatchDqaReportResultType {

    protected String dqaReport;

    /**
     * Gets the value of the dqaReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDqaReport() {
        return dqaReport;
    }

    /**
     * Sets the value of the dqaReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDqaReport(String value) {
        this.dqaReport = value;
    }

}
