
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for batchIssueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="batchIssueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="batchIssueId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="issueCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="issueId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="issueText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchIssueType", propOrder = {
    "batchId",
    "batchIssueId",
    "issueCount",
    "issueId",
    "issueText"
})
public class BatchIssueType {

    protected long batchId;
    protected long batchIssueId;
    protected long issueCount;
    protected long issueId;
    protected String issueText;

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
     * Gets the value of the batchIssueId property.
     * 
     */
    public long getBatchIssueId() {
        return batchIssueId;
    }

    /**
     * Sets the value of the batchIssueId property.
     * 
     */
    public void setBatchIssueId(long value) {
        this.batchIssueId = value;
    }

    /**
     * Gets the value of the issueCount property.
     * 
     */
    public long getIssueCount() {
        return issueCount;
    }

    /**
     * Sets the value of the issueCount property.
     * 
     */
    public void setIssueCount(long value) {
        this.issueCount = value;
    }

    /**
     * Gets the value of the issueId property.
     * 
     */
    public long getIssueId() {
        return issueId;
    }

    /**
     * Sets the value of the issueId property.
     * 
     */
    public void setIssueId(long value) {
        this.issueId = value;
    }

    /**
     * Gets the value of the issueText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueText() {
        return issueText;
    }

    /**
     * Sets the value of the issueText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueText(String value) {
        this.issueText = value;
    }

}
