
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for submitMessageResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="submitMessageResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="errorList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}issueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hashId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivedId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="responseStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responseText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warningList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}issueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitMessageResultType", propOrder = {
    "batchId",
    "errorList",
    "hashId",
    "messageResponse",
    "processReport",
    "receivedId",
    "responseStatus",
    "responseText",
    "warningList"
})
public class SubmitMessageResultType {

    protected long batchId;
    @XmlElement(nillable = true)
    protected List<IssueType> errorList;
    protected String hashId;
    protected String messageResponse;
    protected String processReport;
    protected long receivedId;
    protected String responseStatus;
    protected String responseText;
    @XmlElement(nillable = true)
    protected List<IssueType> warningList;

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
     * Gets the value of the errorList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IssueType }
     * 
     * 
     */
    public List<IssueType> getErrorList() {
        if (errorList == null) {
            errorList = new ArrayList<IssueType>();
        }
        return this.errorList;
    }

    /**
     * Gets the value of the hashId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashId() {
        return hashId;
    }

    /**
     * Sets the value of the hashId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashId(String value) {
        this.hashId = value;
    }

    /**
     * Gets the value of the messageResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageResponse() {
        return messageResponse;
    }

    /**
     * Sets the value of the messageResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageResponse(String value) {
        this.messageResponse = value;
    }

    /**
     * Gets the value of the processReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessReport() {
        return processReport;
    }

    /**
     * Sets the value of the processReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessReport(String value) {
        this.processReport = value;
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
     * Gets the value of the responseStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseStatus() {
        return responseStatus;
    }

    /**
     * Sets the value of the responseStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseStatus(String value) {
        this.responseStatus = value;
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
     * Gets the value of the warningList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the warningList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarningList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IssueType }
     * 
     * 
     */
    public List<IssueType> getWarningList() {
        if (warningList == null) {
            warningList = new ArrayList<IssueType>();
        }
        return this.warningList;
    }

}
