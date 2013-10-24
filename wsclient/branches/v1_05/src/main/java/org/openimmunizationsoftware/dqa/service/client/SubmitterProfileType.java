
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for submitterProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="submitterProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgLocalCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="orgLocalLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="profileLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templateId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="transferPriority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitterProfileType", propOrder = {
    "accessKey",
    "orgLocalCode",
    "orgLocalLabel",
    "profileCode",
    "profileId",
    "profileLabel",
    "profileStatus",
    "templateId",
    "transferPriority"
})
public class SubmitterProfileType {

    protected String accessKey;
    protected long orgLocalCode;
    protected String orgLocalLabel;
    protected String profileCode;
    protected Long profileId;
    protected String profileLabel;
    protected String profileStatus;
    protected Long templateId;
    protected String transferPriority;

    /**
     * Gets the value of the accessKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * Sets the value of the accessKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessKey(String value) {
        this.accessKey = value;
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
     * Gets the value of the orgLocalLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgLocalLabel() {
        return orgLocalLabel;
    }

    /**
     * Sets the value of the orgLocalLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgLocalLabel(String value) {
        this.orgLocalLabel = value;
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
     * Gets the value of the profileId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProfileId() {
        return profileId;
    }

    /**
     * Sets the value of the profileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProfileId(Long value) {
        this.profileId = value;
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

    /**
     * Gets the value of the profileStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileStatus() {
        return profileStatus;
    }

    /**
     * Sets the value of the profileStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileStatus(String value) {
        this.profileStatus = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * Sets the value of the templateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTemplateId(Long value) {
        this.templateId = value;
    }

    /**
     * Gets the value of the transferPriority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferPriority() {
        return transferPriority;
    }

    /**
     * Sets the value of the transferPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferPriority(String value) {
        this.transferPriority = value;
    }

}
