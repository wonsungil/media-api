package com.bbp.bbptest.facebook.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdAccountDto {
    private long id;
    private long accountId;
    private long accountStatus;
    private long timeZoneId;
    private String name;
    private String currency;
    private String partner;
    private String endAdvertiser;
    private String mediaAgency;
    private boolean invoice;
}
