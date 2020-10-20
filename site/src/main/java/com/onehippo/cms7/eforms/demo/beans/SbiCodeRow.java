/*
 * Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.beans;

import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

import com.opencsv.bean.CsvBindByName;

@Node(jcrType = "hippoaddoneformsdemo:sbicoderow")
public class SbiCodeRow extends HippoCompound implements ContentNodeBinder {
    @CsvBindByName(column = "SBI")
    private String sbi;
    @CsvBindByName(column = "Omschrijving")
    private String description;
    @CsvBindByName(column = "ZZP Pakket")
    private String zzpPackage;
    @CsvBindByName(column = "Premie excl assurantie belasting")
    private String premieExTax;
    @CsvBindByName(column = "BIK")
    private String bik;
    @CsvBindByName(column = "Prijs")
    private String price;
    @CsvBindByName(column = "Premie incl assurantie belasting")
    private String premieIncTax;

    public String getPremieIncTax() {
        return premieIncTax;
    }

    public void setPremieIncTax(final String premieIncTax) {
        this.premieIncTax = premieIncTax;
    }

    public String getPrice() {
        return (price == null ? getSingleProperty("hippoaddoneformsdemo:price") : price);
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getBik() {
        return (bik == null ? getSingleProperty("hippoaddoneformsdemo:bik") : bik);
    }

    public void setBik(final String bik) {
        this.bik = bik;
    }

    public String getSbi() {
        return (sbi == null ? getSingleProperty("hippoaddoneformsdemo:sbi") : sbi);
    }

    public String getDescription() {
        return (description == null ? getSingleProperty("hippoaddoneformsdemo:description") : description);
    }

    public String getZzpPackage() {
        return (zzpPackage == null ? getSingleProperty("hippoaddoneformsdemo:zzppackage") : zzpPackage);
    }

    public String getPremieExTax() {
        return (premieExTax == null ? getSingleProperty("hippoaddoneformsdemo:premieextax") : premieExTax);
    }

    public void setSbi(final String sbi) {
        this.sbi = sbi;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setZzpPackage(final String zzpPackage) {
        this.zzpPackage = zzpPackage;
    }

    public void setPremieExTax(final String premieExTax) {
        this.premieExTax = premieExTax;
    }

    @Override
    public boolean bind(final Object content, final javax.jcr.Node node) throws ContentNodeBindingException {
        if (content instanceof SbiCodeRow) {
            SbiCodeRow sbiCodeRow = (SbiCodeRow) content;
            if (!StringUtils.isAllEmpty(sbiCodeRow.getSbi(),
                    sbiCodeRow.getDescription(),
                    sbiCodeRow.getZzpPackage(),
                    sbiCodeRow.getPremieExTax(),
                    sbiCodeRow.getBik(),
                    sbiCodeRow.getPrice(),
                    sbiCodeRow.getPremieIncTax())) {
                try {
                    node.setPrimaryType("hippoaddoneformsdemo:sbicoderow");
                    node.setProperty("hippoaddoneformsdemo:sbi", sbiCodeRow.getSbi());
                    node.setProperty("hippoaddoneformsdemo:description", sbiCodeRow.getDescription());
                    node.setProperty("hippoaddoneformsdemo:zzppackage", sbiCodeRow.getZzpPackage());
                    node.setProperty("hippoaddoneformsdemo:premieextax", sbiCodeRow.getPremieExTax());
                    node.setProperty("hippoaddoneformsdemo:bik", sbiCodeRow.getBik());
                    node.setProperty("hippoaddoneformsdemo:price", sbiCodeRow.getPrice());
                    node.setProperty("hippoaddoneformsdemo:premieinctax", sbiCodeRow.getPremieIncTax());
                    return true;
                } catch (RepositoryException e) {
                    throw new ContentNodeBindingException();
                }
            }
        }
        return false;
    }
}
