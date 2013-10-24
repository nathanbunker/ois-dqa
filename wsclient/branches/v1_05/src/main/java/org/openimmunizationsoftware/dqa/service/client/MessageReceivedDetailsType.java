
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for messageReceivedDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="messageReceivedDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="receivedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="requestText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responseText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messageReceivedDetailsType", propOrder = {
    "actionCode",
    "actionLabel",
    "profileId",
    "receivedDate",
    "receivedId",
    "requestText",
    "responseText",
    "submitCode",
    "submitLabel"
})
public class MessageReceivedDetailsType {

    protected String actionCode;
    protected String actionLabel;
    protected long profileId;
    protected String receivedDate;
    protected long receivedId;
    protected String requestText;
    protected String responseText;
    protected String submitCode;
    protected String submitLabel;

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

    /**
     * Gets the value of the receivedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedDate() {
        return receivedDate;
    }

    /**
     * Sets the value of the receivedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedDate(String value) {
        this.receivedDate = value;
    }

    /**
     * Gets the value of the receivedId property.
     * 
     */
    public long getReceivedId() {
        return receivedId;
    }

    /**
     * Sets the value of the receivedId property.
     * 
     */
    public void setReceivedId(long value) {
        this.receivedId = value;
    }

    /**
     * Gets the value of the requestText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestText() {
        return requestText;
    }

    /**
     * Sets the value of the requestText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestText(String value) {
        this.requestText = value;
    }

    /**
     * Gets the value of the responseText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseText() {
        return responseText;
    }

    /**
     * Sets the value of the responseText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseText(String value) {
        this.responseText = value;
    }

    /**
     * Gets the value of the submitCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitCode() {
        return submitCode;
    }

    /**
     * Sets the value of the submitCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitCode(String value) {
        this.submitCode = value;
    }

    /**
     * Gets the value of the submitLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitLabel() {
        return submitLabel;
    }

    /**
     * Sets the value of the submitLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitLabel(String value) {
        this.submitLabel = value;
    }

}
