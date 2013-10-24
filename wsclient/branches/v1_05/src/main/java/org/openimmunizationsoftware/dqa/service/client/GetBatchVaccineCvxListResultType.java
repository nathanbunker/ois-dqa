
package org.openimmunizationsoftware.dqa.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBatchVaccineCvxListResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBatchVaccineCvxListResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchVaccineCvxList" type="{http://dqaws.openimmunizationsoftware.org/dqa/}batchVaccineCvxType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBatchVaccineCvxListResultType", propOrder = {
    "batchVaccineCvxList"
})
public class GetBatchVaccineCvxListResultType {

    @XmlElement(nillable = true)
    protected List<BatchVaccineCvxType> batchVaccineCvxList;

    /**
     * Gets the value of the batchVaccineCvxList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchVaccineCvxList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchVaccineCvxList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchVaccineCvxType }
     * 
     * 
     */
    public List<BatchVaccineCvxType> getBatchVaccineCvxList() {
        if (batchVaccineCvxList == null) {
            batchVaccineCvxList = new ArrayList<BatchVaccineCvxType>();
        }
        return this.batchVaccineCvxList;
    }

}
