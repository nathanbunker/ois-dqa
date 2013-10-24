
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNextOfKinListResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNextOfKinListResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nextOfKinList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}nextOfKinType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNextOfKinListResultType", propOrder = {
    "nextOfKinList"
})
public class GetNextOfKinListResultType {

    @XmlElement(nillable = true)
    protected List<NextOfKinType> nextOfKinList;

    /**
     * Gets the value of the nextOfKinList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nextOfKinList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNextOfKinList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NextOfKinType }
     * 
     * 
     */
    public List<NextOfKinType> getNextOfKinList() {
        if (nextOfKinList == null) {
            nextOfKinList = new ArrayList<NextOfKinType>();
        }
        return this.nextOfKinList;
    }

}
