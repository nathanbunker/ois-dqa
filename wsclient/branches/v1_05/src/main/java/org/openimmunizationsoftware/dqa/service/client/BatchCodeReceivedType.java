
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for batchCodeReceivedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="batchCodeReceivedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchCodeReceivedId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeLabel2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="receivedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tableId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tableLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchCodeReceivedType", propOrder = {
    "batchCodeReceivedId",
    "batchId",
    "codeId",
    "codeLabel",
    "codeLabel2",
    "codeStatus",
    "codeValue",
    "receivedCount",
    "receivedValue",
    "tableId",
    "tableLabel"
})
public class BatchCodeReceivedType {

    protected long batchCodeReceivedId;
    protected long batchId;
    protected String codeId;
    protected String codeLabel;
    protected String codeLabel2;
    protected String codeStatus;
    protected String codeValue;
    protected long receivedCount;
    protected String receivedValue;
    protected long tableId;
    protected String tableLabel;

    /**
     * Gets the value of the batchCodeReceivedId property.
     * 
     */
    public long getBatchCodeReceivedId() {
        return batchCodeReceivedId;
    }

    /**
     * Sets the value of the batchCodeReceivedId property.
     * 
     */
    public void setBatchCodeReceivedId(long value) {
        this.batchCodeReceivedId = value;
    }

    /**
     * Gets the value of the batchId property.
     * 
     */
    public long getBatchId() {
        return batchId;
    }

    /**
     * Sets the value of the batchId property.
     * 
     */
    public void setBatchId(long value) {
        this.batchId = value;
    }

    /**
     * Gets the value of the codeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeId() {
        return codeId;
    }

    /**
     * Sets the value of the codeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeId(String value) {
        this.codeId = value;
    }

    /**
     * Gets the value of the codeLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeLabel() {
        return codeLabel;
    }

    /**
     * Sets the value of the codeLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeLabel(String value) {
        this.codeLabel = value;
    }

    /**
     * Gets the value of the codeLabel2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeLabel2() {
        return codeLabel2;
    }

    /**
     * Sets the value of the codeLabel2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeLabel2(String value) {
        this.codeLabel2 = value;
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

    /**
     * Gets the value of the receivedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedValue() {
        return receivedValue;
    }

    /**
     * Sets the value of the receivedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedValue(String value) {
        this.receivedValue = value;
    }

    /**
     * Gets the value of the tableId property.
     * 
     */
    public long getTableId() {
        return tableId;
    }

    /**
     * Sets the value of the tableId property.
     * 
     */
    public void setTableId(long value) {
        this.tableId = value;
    }

    /**
     * Gets the value of the tableLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableLabel() {
        return tableLabel;
    }

    /**
     * Sets the value of the tableLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableLabel(String value) {
        this.tableLabel = value;
    }

}
