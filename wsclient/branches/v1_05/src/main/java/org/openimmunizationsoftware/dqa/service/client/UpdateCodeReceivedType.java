
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateCodeReceivedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateCodeReceivedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeReceivedList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}codeReceivedSmallType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateCodeReceivedType", propOrder = {
    "codeReceivedList"
})
public class UpdateCodeReceivedType {

    @XmlElement(nillable = true)
    protected List<CodeReceivedSmallType> codeReceivedList;

    /**
     * Gets the value of the codeReceivedList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codeReceivedList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodeReceivedList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeReceivedSmallType }
     * 
     * 
     */
    public List<CodeReceivedSmallType> getCodeReceivedList() {
        if (codeReceivedList == null) {
            codeReceivedList = new ArrayList<CodeReceivedSmallType>();
        }
        return this.codeReceivedList;
    }

}
