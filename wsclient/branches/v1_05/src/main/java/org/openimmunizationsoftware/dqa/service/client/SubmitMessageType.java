
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for submitMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="submitMessageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchInstruction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgLocalCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="processMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitMessageType", propOrder = {
    "batchInstruction",
    "messageRequest",
    "orgLocalCode",
    "processMode",
    "profileCode",
    "profileLabel"
})
public class SubmitMessageType {

    protected String batchInstruction;
    protected String messageRequest;
    protected long orgLocalCode;
    protected String processMode;
    protected String profileCode;
    protected String profileLabel;

    /**
     * Gets the value of the batchInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchInstruction() {
        return batchInstruction;
    }

    /**
     * Sets the value of the batchInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchInstruction(String value) {
        this.batchInstruction = value;
    }

    /**
     * Gets the value of the messageRequest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageRequest() {
        return messageRequest;
    }

    /**
     * Sets the value of the messageRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageRequest(String value) {
        this.messageRequest = value;
    }

    /**
     * Gets the value of the orgLocalCode property.
     * 
     */
    public long getOrgLocalCode() {
        return orgLocalCode;
    }

    /**
     * Sets the value of the orgLocalCode property.
     * 
     */
    public void setOrgLocalCode(long value) {
        this.orgLocalCode = value;
    }

    /**
     * Gets the value of the processMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessMode() {
        return processMode;
    }

    /**
     * Sets the value of the processMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessMode(String value) {
        this.processMode = value;
    }

    /**
     * Gets the value of the profileCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileCode() {
        return profileCode;
    }

    /**
     * Sets the value of the profileCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileCode(String value) {
        this.profileCode = value;
    }

    /**
     * Gets the value of the profileLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileLabel() {
        return profileLabel;
    }

    /**
     * Sets the value of the profileLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileLabel(String value) {
        this.profileLabel = value;
    }

}
