package com.onehippo.cms7.jaxrs.services;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "sbicoderow")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SbiCodeRowResponseObject {
    private String sbi;
    private String description;
    private String zzpPackage;
    private String premieExTax;
    private String bik;
    private String price;
    private String premieIncTax;

    public SbiCodeRowResponseObject() {
    }

    public SbiCodeRowResponseObject(final String sbi, final String description,
                                    final String zzpPackage, final String premieExTax,
                                    final String bik, final String price, final String premieIncTax) {
        this.sbi = sbi;
        this.description = description;
        this.zzpPackage = zzpPackage;
        this.premieExTax = premieExTax;
        this.bik = bik;
        this.price = price;
        this.premieIncTax = premieIncTax;
    }

    public String getSbi() {
        return sbi;
    }

    public void setSbi(final String sbi) {
        this.sbi = sbi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getZzpPackage() {
        return zzpPackage;
    }

    public void setZzpPackage(final String zzpPackage) {
        this.zzpPackage = zzpPackage;
    }

    public String getPremieExTax() {
        return premieExTax;
    }

    public void setPremieExTax(final String premieExTax) {
        this.premieExTax = premieExTax;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(final String bik) {
        this.bik = bik;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getPremieIncTax() {
        return premieIncTax;
    }

    public void setPremieIncTax(final String premieIncTax) {
        this.premieIncTax = premieIncTax;
    }
}
