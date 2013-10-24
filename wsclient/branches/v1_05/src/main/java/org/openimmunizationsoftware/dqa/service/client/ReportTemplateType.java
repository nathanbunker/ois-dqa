
package org.openimmunizationsoftware.dqa.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reportTemplateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reportTemplateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="applicationLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseProfileId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="baseProfileLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="reportTypeLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="templateId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="templateLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reportTemplateType", propOrder = {
    "applicationId",
    "applicationLabel",
    "baseProfileId",
    "baseProfileLabel",
    "reportTypeId",
    "reportTypeLabel",
    "templateId",
    "templateLabel"
})
public class ReportTemplateType {

    protected long applicationId;
    protected String applicationLabel;
    protected long baseProfileId;
    protected String baseProfileLabel;
    protected long reportTypeId;
    protected String reportTypeLabel;
    protected long templateId;
    protected String templateLabel;

    /**
     * Gets the value of the applicationId property.
     * 
     */
    public long getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the value of the applicationId property.
     * 
     */
    public void setApplicationId(long value) {
        this.applicationId = value;
    }

    /**
     * Gets the value of the applicationLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationLabel() {
        return applicationLabel;
    }

    /**
     * Sets the value of the applicationLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationLabel(String value) {
        this.applicationLabel = value;
    }

    /**
     * Gets the value of the baseProfileId property.
     * 
     */
    public long getBaseProfileId() {
        return baseProfileId;
    }

    /**
     * Sets the value of the baseProfileId property.
     * 
     */
    public void setBaseProfileId(long value) {
        this.baseProfileId = value;
    }

    /**
     * Gets the value of the baseProfileLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseProfileLabel() {
        return baseProfileLabel;
    }

    /**
     * Sets the value of the baseProfileLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseProfileLabel(String value) {
        this.baseProfileLabel = value;
    }

    /**
     * Gets the value of the reportTypeId property.
     * 
     */
    public long getReportTypeId() {
        return reportTypeId;
    }

    /**
     * Sets the value of the reportTypeId property.
     * 
     */
    public void setReportTypeId(long value) {
        this.reportTypeId = value;
    }

    /**
     * Gets the value of the reportTypeLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportTypeLabel() {
        return reportTypeLabel;
    }

    /**
     * Sets the value of the reportTypeLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportTypeLabel(String value) {
        this.reportTypeLabel = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     */
    public long getTemplateId() {
        return templateId;
    }

    /**
     * Sets the value of the templateId property.
     * 
     */
    public void setTemplateId(long value) {
        this.templateId = value;
    }

    /**
     * Gets the value of the templateLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateLabel() {
        return templateLabel;
    }

    /**
     * Sets the value of the templateLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateLabel(String value) {
        this.templateLabel = value;
    }

}
