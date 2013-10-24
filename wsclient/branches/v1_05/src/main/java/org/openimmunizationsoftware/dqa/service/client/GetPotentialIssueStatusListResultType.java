
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPotentialIssueStatusListResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPotentialIssueStatusListResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="potentialIssueStatusList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}potentialIssueStatusType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPotentialIssueStatusListResultType", propOrder = {
    "potentialIssueStatusList"
})
public class GetPotentialIssueStatusListResultType {

    @XmlElement(nillable = true)
    protected List<PotentialIssueStatusType> potentialIssueStatusList;

    /**
     * Gets the value of the potentialIssueStatusList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the potentialIssueStatusList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPotentialIssueStatusList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PotentialIssueStatusType }
     * 
     * 
     */
    public List<PotentialIssueStatusType> getPotentialIssueStatusList() {
        if (potentialIssueStatusList == null) {
            potentialIssueStatusList = new ArrayList<PotentialIssueStatusType>();
        }
        return this.potentialIssueStatusList;
    }

}
