
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for batchVaccineCvxType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="batchVaccineCvxType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="batchVaccineCvxId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cvxCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cvxLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchVaccineCvxType", propOrder = {
    "batchId",
    "batchVaccineCvxId",
    "cvxCode",
    "cvxLabel",
    "receivedCount"
})
public class BatchVaccineCvxType {

    protected long batchId;
    protected long batchVaccineCvxId;
    protected String cvxCode;
    protected String cvxLabel;
    protected long receivedCount;

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

    /**
     * Gets the value of the batchVaccineCvxId property.
     * 
     */
    public long getBatchVaccineCvxId() {
        return batchVaccineCvxId;
    }

    /**
     * Sets the value of the batchVaccineCvxId property.
     * 
     */
    public void setBatchVaccineCvxId(long value) {
        this.batchVaccineCvxId = value;
    }

    /**
     * Gets the value of the cvxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvxCode() {
        return cvxCode;
    }

    /**
     * Sets the value of the cvxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvxCode(String value) {
        this.cvxCode = value;
    }

    /**
     * Gets the value of the cvxLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvxLabel() {
        return cvxLabel;
    }

    /**
     * Sets the value of the cvxLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvxLabel(String value) {
        this.cvxLabel = value;
    }

    /**
     * Gets the value of the receivedCount property.
     * 
     */
    public long getReceivedCount() {
        return receivedCount;
    }

    /**
     * Sets the value of the receivedCount property.
     * 
     */
    public void setReceivedCount(long value) {
        this.receivedCount = value;
    }

}
