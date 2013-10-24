
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSubmitterProfileResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSubmitterProfileResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="submitterProfile" type="{http://dqaws.openimmunizationsoftware.org/dqa/}submitterProfileType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSubmitterProfileResultType", propOrder = {
    "submitterProfile"
})
public class GetSubmitterProfileResultType {

    protected SubmitterProfileType submitterProfile;

    /**
     * Gets the value of the submitterProfile property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitterProfileType }
     *     
     */
    public SubmitterProfileType getSubmitterProfile() {
        return submitterProfile;
    }

    /**
     * Sets the value of the submitterProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitterProfileType }
     *     
     */
    public void setSubmitterProfile(SubmitterProfileType value) {
        this.submitterProfile = value;
    }

}
