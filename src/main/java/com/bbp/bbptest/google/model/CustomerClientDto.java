package com.bbp.bbptest.google.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustomerClientDto {
    private long id;
    private String resourceName;
    private String clientCustomer;
    private long level;
    private String timeZone;
    private boolean testAccount;
    private boolean manager;
    private String descriptiveName;
    private String currencyCode;
    private boolean hidden;
}
