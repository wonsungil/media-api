package com.bbp.bbptest.google.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CustomerDto {
    private long id;
    private String resourceName;
    private boolean manager;
    private String timeZone;
    private String currencyCode;
    private String descriptiveName;
}
