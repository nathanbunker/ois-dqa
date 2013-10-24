
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePotentialIssueStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePotentialIssueStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expectMax" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="expectMin" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="issueId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePotentialIssueStatusType", propOrder = {
    "actionCode",
    "expectMax",
    "expectMin",
    "issueId"
})
public class UpdatePotentialIssueStatusType {

    protected String actionCode;
    protected long expectMax;
    protected long expectMin;
    protected long issueId;

    /**
     * Gets the value of the actionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Sets the value of the actionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

    /**
     * Gets the value of the expectMax property.
     * 
     */
    public long getExpectMax() {
        return expectMax;
    }

    /**
     * Sets the value of the expectMax property.
     * 
     */
    public void setExpectMax(long value) {
        this.expectMax = value;
    }

    /**
     * Gets the value of the expectMin property.
     * 
     */
    public long getExpectMin() {
        return expectMin;
    }

    /**
     * Sets the value of the expectMin property.
     * 
     */
    public void setExpectMin(long value) {
        this.expectMin = value;
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

}
