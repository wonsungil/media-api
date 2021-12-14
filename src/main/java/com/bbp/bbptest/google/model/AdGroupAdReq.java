package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdGroupAdReq {
    private String status;
    private AdReq adReq;
    private long adGroupId;
    private long customerId;
}
