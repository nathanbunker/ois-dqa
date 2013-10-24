
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for codeReceivedSmallType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="codeReceivedSmallType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "codeReceivedSmallType", propOrder = {
    "codeId",
    "codeStatus",
    "codeValue",
    "receivedCount"
})
public class CodeReceivedSmallType {

    protected long codeId;
    protected String codeStatus;
    protected String codeValue;
    protected long receivedCount;

    /**
     * Gets the value of the codeId property.
     * 
     */
    public long getCodeId() {
        return codeId;
    }

    /**
     * Sets the value of the codeId property.
     * 
     */
    public void setCodeId(long value) {
        this.codeId = value;
    }

    /**
     * Gets the value of the codeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeStatus() {
        return codeStatus;
    }

    /**
     * Sets the value of the codeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeStatus(String value) {
        this.codeStatus = value;
    }

    /**
     * Gets the value of the codeValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * Sets the value of the codeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeValue(String value) {
        this.codeValue = value;
    }

    /**
     * Gets the value of the receivedCount property.
     * 
     */
    public long getReceivedCount() {
        return receivedCount;
    }

    /**
     * Sets the value of the receivedCount property.
     * 
     */
    public void setReceivedCount(long value) {
        this.receivedCount = value;
    }

}
