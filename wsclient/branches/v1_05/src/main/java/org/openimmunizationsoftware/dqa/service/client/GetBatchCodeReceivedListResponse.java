
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchCodeReceivedListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchCodeReceivedListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://dqaws.openimmunizationsoftware.org/dqa/}getBatchCodeReceivedListResultType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchCodeReceivedListResponse", propOrder = {
    "_return"
})
public class GetBatchCodeReceivedListResponse {

    @XmlElement(name = "return")
    protected GetBatchCodeReceivedListResultType _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GetBatchCodeReceivedListResultType }
     *     
     */
    public GetBatchCodeReceivedListResultType getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetBatchCodeReceivedListResultType }
     *     
     */
    public void setReturn(GetBatchCodeReceivedListResultType value) {
        this._return = value;
    }

}
