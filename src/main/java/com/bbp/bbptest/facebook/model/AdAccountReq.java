package com.bbp.bbptest.facebook.model;

import lombok.Getter;

@Getter
public class AdAccountReq {
    private long businessId;
    private String name;
    private long timeZoneId;
    private String currency;
    private String partner;
    private String endAdvertiser;
    private String mediaAgency;
    private boolean invoice;

    @Override
    public String toString() {
        return "AdAccountReq{" +
               "businessId=" + businessId +
               ", name='" + name + '\'' +
               ", timeZoneId=" + timeZoneId +
               ", currency='" + currency + '\'' +
               ", partner='" + partner + '\'' +
               ", endAdvertiser='" + endAdvertiser + '\'' +
               ", mediaAgency='" + mediaAgency + '\'' +
               ", invoice=" + invoice +
               '}';
    }
}
