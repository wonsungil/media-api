package com.bbp.bbptest.facebook.model;

import lombok.Getter;

@Getter
public class CampaignReq {
    private long id;
    private long adAccountId;
    private String name;
    private String objective;
    private String status;
}
