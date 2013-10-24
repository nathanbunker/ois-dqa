
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageBatch" type="{http://dqaws.openimmunizationsoftware.org/dqa/}messageBatchType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchResultType", propOrder = {
    "messageBatch"
})
public class GetBatchResultType {

    protected MessageBatchType messageBatch;

    /**
     * Gets the value of the messageBatch property.
     * 
     * @return
     *     possible object is
     *     {@link MessageBatchType }
     *     
     */
    public MessageBatchType getMessageBatch() {
        return messageBatch;
    }

    /**
     * Sets the value of the messageBatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageBatchType }
     *     
     */
    public void setMessageBatch(MessageBatchType value) {
        this.messageBatch = value;
    }

}
