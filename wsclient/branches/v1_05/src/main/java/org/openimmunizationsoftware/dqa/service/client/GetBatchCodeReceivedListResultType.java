
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchCodeReceivedListResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchCodeReceivedListResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchCodeReceivedList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}batchCodeReceivedType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchCodeReceivedListResultType", propOrder = {
    "batchCodeReceivedList"
})
public class GetBatchCodeReceivedListResultType {

    @XmlElement(nillable = true)
    protected List<BatchCodeReceivedType> batchCodeReceivedList;

    /**
     * Gets the value of the batchCodeReceivedList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchCodeReceivedList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchCodeReceivedList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchCodeReceivedType }
     * 
     * 
     */
    public List<BatchCodeReceivedType> getBatchCodeReceivedList() {
        if (batchCodeReceivedList == null) {
            batchCodeReceivedList = new ArrayList<BatchCodeReceivedType>();
        }
        return this.batchCodeReceivedList;
    }

}
