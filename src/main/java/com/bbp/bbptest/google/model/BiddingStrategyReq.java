package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BiddingStrategyReq {
    private long customerId;
    private long biddingStrategyId;
    private long campaignId;
}
