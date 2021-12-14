package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdGroupReq {
    private String name;
    private String status;
    private String type;
    private long cpcBidMicros;
    private long customerId;
    private long campaignId;
}
