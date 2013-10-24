
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateSubmitterProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateSubmitterProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="autoCreate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitterProfileList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}submitterProfileType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateSubmitterProfileType", propOrder = {
    "autoCreate",
    "submitterProfileList"
})
public class UpdateSubmitterProfileType {

    protected String autoCreate;
    protected SubmitterProfileType submitterProfileList;

    /**
     * Gets the value of the autoCreate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoCreate() {
        return autoCreate;
    }

    /**
     * Sets the value of the autoCreate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoCreate(String value) {
        this.autoCreate = value;
    }

    /**
     * Gets the value of the submitterProfileList property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitterProfileType }
     *     
     */
    public SubmitterProfileType getSubmitterProfileList() {
        return submitterProfileList;
    }

    /**
     * Sets the value of the submitterProfileList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitterProfileType }
     *     
     */
    public void setSubmitterProfileList(SubmitterProfileType value) {
        this.submitterProfileList = value;
    }

}
