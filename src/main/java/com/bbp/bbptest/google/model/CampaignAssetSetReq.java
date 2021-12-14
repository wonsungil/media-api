package com.bbp.bbptest.google.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CampaignAssetSetReq {

    private long customerId;
    private long campaignId;
    private long assetSetId;
}
