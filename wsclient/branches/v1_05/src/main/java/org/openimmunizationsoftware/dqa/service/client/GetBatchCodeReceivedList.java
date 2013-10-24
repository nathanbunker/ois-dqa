
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchCodeReceivedList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchCodeReceivedList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://dqaws.openimmunizationsoftware.org/dqa/}getBatchCodeReceivedListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchCodeReceivedList", propOrder = {
    "arg0"
})
public class GetBatchCodeReceivedList {

    protected GetBatchCodeReceivedListType arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link GetBatchCodeReceivedListType }
     *     
     */
    public GetBatchCodeReceivedListType getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetBatchCodeReceivedListType }
     *     
     */
    public void setArg0(GetBatchCodeReceivedListType value) {
        this.arg0 = value;
    }

}
