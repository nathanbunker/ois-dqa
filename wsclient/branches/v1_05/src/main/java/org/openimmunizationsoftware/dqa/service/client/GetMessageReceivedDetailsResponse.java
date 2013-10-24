
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessageReceivedDetailsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessageReceivedDetailsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://dqaws.openimmunizationsoftware.org/dqa/}getMessageReceivedDetailsResultType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessageReceivedDetailsResponse", propOrder = {
    "_return"
})
public class GetMessageReceivedDetailsResponse {

    @XmlElement(name = "return")
    protected GetMessageReceivedDetailsResultType _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GetMessageReceivedDetailsResultType }
     *     
     */
    public GetMessageReceivedDetailsResultType getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMessageReceivedDetailsResultType }
     *     
     */
    public void setReturn(GetMessageReceivedDetailsResultType value) {
        this._return = value;
    }

}
