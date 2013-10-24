
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessageReceivedListResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessageReceivedListResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageReceivedList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}messageReceivedType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessageReceivedListResultType", propOrder = {
    "messageReceivedList"
})
public class GetMessageReceivedListResultType {

    @XmlElement(nillable = true)
    protected List<MessageReceivedType> messageReceivedList;

    /**
     * Gets the value of the messageReceivedList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageReceivedList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageReceivedList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageReceivedType }
     * 
     * 
     */
    public List<MessageReceivedType> getMessageReceivedList() {
        if (messageReceivedList == null) {
            messageReceivedList = new ArrayList<MessageReceivedType>();
        }
        return this.messageReceivedList;
    }

}
