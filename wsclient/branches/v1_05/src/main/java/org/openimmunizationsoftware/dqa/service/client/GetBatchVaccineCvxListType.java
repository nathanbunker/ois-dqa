
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchVaccineCvxListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchVaccineCvxListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchVaccineCvxListType", propOrder = {
    "batchId"
})
public class GetBatchVaccineCvxListType {

    protected long batchId;

    /**
     * Gets the value of the batchId property.
     * 
     */
    public long getBatchId() {
        return batchId;
    }

    /**
     * Sets the value of the batchId property.
     * 
     */
    public void setBatchId(long value) {
        this.batchId = value;
    }

}