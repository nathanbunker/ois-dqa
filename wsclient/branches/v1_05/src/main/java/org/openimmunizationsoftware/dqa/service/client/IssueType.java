
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for issueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="issueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="issueId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="issueLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "issueType", propOrder = {
    "issueId",
    "issueLabel"
})
public class IssueType {

    protected long issueId;
    protected String issueLabel;

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
     * Gets the value of the issueLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueLabel() {
        return issueLabel;
    }

    /**
     * Sets the value of the issueLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueLabel(String value) {
        this.issueLabel = value;
    }

}
