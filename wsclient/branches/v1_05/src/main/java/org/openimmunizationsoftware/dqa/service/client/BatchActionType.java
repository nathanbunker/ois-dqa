
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for batchActionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="batchActionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="actionLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="batchActionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchActionType", propOrder = {
    "actionCode",
    "actionCount",
    "actionLabel",
    "batchActionId",
    "batchId"
})
public class BatchActionType {

    protected String actionCode;
    protected long actionCount;
    protected String actionLabel;
    protected long batchActionId;
    protected long batchId;

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
     * Gets the value of the actionCount property.
     * 
     */
    public long getActionCount() {
        return actionCount;
    }

    /**
     * Sets the value of the actionCount property.
     * 
     */
    public void setActionCount(long value) {
        this.actionCount = value;
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
     * Gets the value of the batchActionId property.
     * 
     */
    public long getBatchActionId() {
        return batchActionId;
    }

    /**
     * Sets the value of the batchActionId property.
     * 
     */
    public void setBatchActionId(long value) {
        this.batchActionId = value;
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

}
