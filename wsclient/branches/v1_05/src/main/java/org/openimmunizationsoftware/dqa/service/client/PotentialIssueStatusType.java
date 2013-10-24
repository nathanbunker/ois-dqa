
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for potentialIssueStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="potentialIssueStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changePriority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expectMax" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="expectMin" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="issueDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="issueLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "potentialIssueStatusType", propOrder = {
    "actionCode",
    "actionLabel",
    "changePriority",
    "expectMax",
    "expectMin",
    "issueDescription",
    "issueId",
    "issueLabel",
    "profileId"
})
public class PotentialIssueStatusType {

    protected String actionCode;
    protected String actionLabel;
    protected String changePriority;
    protected long expectMax;
    protected long expectMin;
    protected String issueDescription;
    protected long issueId;
    protected String issueLabel;
    protected long profileId;

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
     * Gets the value of the actionLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionLabel() {
        return actionLabel;
    }

    /**
     * Sets the value of the actionLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionLabel(String value) {
        this.actionLabel = value;
    }

    /**
     * Gets the value of the changePriority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangePriority() {
        return changePriority;
    }

    /**
     * Sets the value of the changePriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangePriority(String value) {
        this.changePriority = value;
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
     * Gets the value of the issueDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDescription() {
        return issueDescription;
    }

    /**
     * Sets the value of the issueDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDescription(String value) {
        this.issueDescription = value;
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

    /**
     * Gets the value of the profileId property.
     * 
     */
    public long getProfileId() {
        return profileId;
    }

    /**
     * Sets the value of the profileId property.
     * 
     */
    public void setProfileId(long value) {
        this.profileId = value;
    }

}
