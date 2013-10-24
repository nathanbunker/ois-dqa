
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessageReceivedDetailsResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessageReceivedDetailsResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="issueFoundList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}issueFoundType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="messageReceivedDetails" type="{http://dqaws.openimmunizationsoftware.org/dqa/}messageReceivedDetailsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessageReceivedDetailsResultType", propOrder = {
    "issueFoundList",
    "messageReceivedDetails"
})
public class GetMessageReceivedDetailsResultType {

    @XmlElement(nillable = true)
    protected List<IssueFoundType> issueFoundList;
    protected MessageReceivedDetailsType messageReceivedDetails;

    /**
     * Gets the value of the issueFoundList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issueFoundList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssueFoundList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IssueFoundType }
     * 
     * 
     */
    public List<IssueFoundType> getIssueFoundList() {
        if (issueFoundList == null) {
            issueFoundList = new ArrayList<IssueFoundType>();
        }
        return this.issueFoundList;
    }

    /**
     * Gets the value of the messageReceivedDetails property.
     * 
     * @return
     *     possible object is
     *     {@link MessageReceivedDetailsType }
     *     
     */
    public MessageReceivedDetailsType getMessageReceivedDetails() {
        return messageReceivedDetails;
    }

    /**
     * Sets the value of the messageReceivedDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageReceivedDetailsType }
     *     
     */
    public void setMessageReceivedDetails(MessageReceivedDetailsType value) {
        this.messageReceivedDetails = value;
    }

}
