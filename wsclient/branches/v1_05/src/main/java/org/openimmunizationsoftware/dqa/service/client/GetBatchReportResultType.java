
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchReportResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchReportResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchReport" type="{http://dqaws.openimmunizationsoftware.org/dqa/}batchReportType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchReportResultType", propOrder = {
    "batchReport"
})
public class GetBatchReportResultType {

    protected BatchReportType batchReport;

    /**
     * Gets the value of the batchReport property.
     * 
     * @return
     *     possible object is
     *     {@link BatchReportType }
     *     
     */
    public BatchReportType getBatchReport() {
        return batchReport;
    }

    /**
     * Sets the value of the batchReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchReportType }
     *     
     */
    public void setBatchReport(BatchReportType value) {
        this.batchReport = value;
    }

}
